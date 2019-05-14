import {Injectable, OnInit} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {User} from "../../domain/entity/User";
import {BehaviorSubject, Observable, of} from "rxjs";
import {apiUrl} from "../../../environments/environment.prod";
import {catchError, tap} from "rxjs/operators";
import {CookieService} from 'ngx-cookie-service';
import {Page} from "../../domain/interface/Page";
import {UserLoginVo} from "../../domain/vo/UserLoginVo";
import {UserResetPasswordVo} from "../../domain/vo/UserResetPasswordVo";
import {JwtUserResponse} from "../../domain/entity/JwtUserResponse";

/**
 * 用户的服务类。
 */
//带有{provideIn: "root"}属性的服务，不需要在所属模块的provider属性中声明
//http拦截器之类仍然需要进行特殊声明
@Injectable({providedIn: "root"})
export class UserService implements OnInit {
  /** 当前用户信息的可观察流对象。当前用户是不固定的，因此需要使用可观察流对象。 */
  public currentUserSubject: BehaviorSubject<JwtUserResponse>;
  /**当前用户信息的可观察对象。*/
  public currentUser$: Observable<JwtUserResponse>;


  constructor(private http: HttpClient,
              private cookieService: CookieService) {
  }


  ngOnInit(): void {
    //尝试从localStorage中得到当前用户
    const memo = localStorage.getItem("currentUser");
    //如果存在，则得到当前用户的可观察对象
    if (memo) {
      //创建当前用户的BehaviorSubject对象以及对应的可观察对象
      this.currentUserSubject = new BehaviorSubject<JwtUserResponse>(JSON.parse(memo));
      this.currentUser$ = this.currentUserSubject.asObservable();
      //将得到的当前用户存储到cookie中去
      this.cookieService.set("currentUser", memo);
    }
  }

  login(vo: UserLoginVo): Observable<JwtUserResponse> {
    const url = `${apiUrl}/login`;
    return this.http.post<JwtUserResponse>(url, vo).pipe(
        tap(currentUser => {
          console.log("登录成功！");
          //如果用户和用户令牌存在，则存储到cookie中
          if (currentUser && currentUser.token) {
            this.cookieService.set("currentUser", JSON.stringify(currentUser));
            //如果记住登录为true，则还存储到localStorage中
            if (vo.rememberMe) {
              localStorage.setItem("currentUser", JSON.stringify(currentUser));
            }
            //将当前用户信息推送到对应的可观察流对象中
            this.currentUserSubject.next(currentUser);
          }
        }),
        catchError(this.handleError("login", null))
    );
  }

  logout() {
    console.log("登出成功！");
    //清空当前用户信息可观察流对象的值
    this.currentUserSubject.next(null);
    //从cookie和本地存储中删除当前用户
    this.cookieService.delete("currentUser");
    localStorage.removeItem("currentUser");
  }

  resetPassword(vo: UserResetPasswordVo) {
    const url = `${apiUrl}/reset-password`;
    return this.http.put(url, vo).pipe(
        tap(_ => console.log("重置密码成功！")),
        catchError(this.handleError("resetPassword", null))
    );
  }

  register(user: User): Observable<User> {
    const url = `${apiUrl}/register`;
    return this.http.put<User>(url, user).pipe(
        tap(_ => console.log("注册成功！")),
        catchError(this.handleError("register", null))
    );
  }

  updateAccountInfo(user: User): Observable<User> {
    const url = `${apiUrl}/account${user.username}`;
    return this.http.put<User>(url, user).pipe(
        catchError(this.handleError("updateAccountInfo", user))
    );
  }

  getAccountInfo(username: string): Observable<User> {
    const url = `${apiUrl}/account/${username}`;
    return this.http.get<User>(url).pipe(
        catchError(this.handleError("getAccountInfo", null))
    );
  }

  get(id: number): Observable<User> {
    const url = `${apiUrl}/user/${id}`;
    return this.http.get<User>(url).pipe(
        catchError(this.handleError("get", null))
    );
  }

  list(page: number, size: number): Observable<Page<User>> {
    const url = `${apiUrl}/user/list`;
    const params = {page: page + "", size: size + ""};
    return this.http.get<Page<User>>(url, {params: params}).pipe(
        catchError(this.handleError("list", null))
    );
  }

  searchByNickname(nickname: string, page: number, size: number): Observable<Page<User>> {
    const url = `${apiUrl}/user/search`;
    const params = {nickname: nickname, page: page + "", size: size + ""};
    return this.http.get<Page<User>>(url, {params: params}).pipe(
        catchError(this.handleError("searchByNickname", null))
    );
  }


  /**
   * 处理Http操作错误，让程序继续运行。
   * @param operation 失败的操作的名字
   * @param result 需要返回的可观察对象结果
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      //打印错误信息
      console.log(error);
      //通过返回一个空结果让程序得以继续运行
      return of(result as T);
    };
  }
}
