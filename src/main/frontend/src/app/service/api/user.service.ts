import {Injectable, OnInit} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {User} from "../../domain/entity/User";
import {BehaviorSubject, Observable, of, Subject} from "rxjs";
import {apiUrl} from "../../../environments/environment.prod";
import {catchError, tap} from "rxjs/operators";
import {CookieService} from 'ngx-cookie-service';
import {Page} from "../../domain/interface/Page";
import {UserLoginVo} from "../../domain/vo/UserLoginVo";
import {UserResetPasswordVo} from "../../domain/vo/UserResetPasswordVo";
import {JwtResponseVo} from "../../domain/vo/JwtResponseVo";

/**
 * 用户的服务类。
 */
//带有{provideIn: "root"}属性的服务，不需要在所属模块的provider属性中声明
//http拦截器之类仍然需要进行特殊声明
@Injectable({providedIn: "root"})
export class UserService implements OnInit {
  public nameSubject = new Subject<String>();
  public name$ = this.nameSubject.asObservable();
  public currentUserSubject: BehaviorSubject<JwtResponseVo>;
  public currentUser$ = this.currentUserSubject.asObservable();


  constructor(private http: HttpClient,
              private cookieService: CookieService) {
  }


  ngOnInit(): void {
    //尝试从localStorage中得到当前用户
    const memo = localStorage.getItem("currentUser");
    //创建当前用户的BehaviorSubject对象以及对应的可观察对象
    this.currentUserSubject = new BehaviorSubject<JwtResponseVo>(JSON.parse(memo));
    //将得到的当前用户存储到cookie中去
    this.cookieService.set("currentUser", memo);
  }

  login(vo: UserLoginVo): Observable<JwtResponseVo> {
    const url = `${apiUrl}/login`;
    return this.http.post<JwtResponseVo>(url, vo).pipe(
        tap(currentUser => {
          console.log("登录成功！");
          //如果用户和用户令牌存在，则存储到cookie中
          if (currentUser && currentUser.token) {
            this.cookieService.set("currentUser", JSON.stringify(currentUser));
            //如果记住登录为true，则还存储到localStorage中
            if (vo.rememberMe) {
              localStorage.setItem("currentUser", JSON.stringify(currentUser));
            }
            //NOTE 这里有什么作用？
            this.currentUserSubject.next(currentUser);
            this.nameSubject.next(currentUser.username);
          }
        }),
        catchError(this.handleError("login", null))
    );
  }

  logout() {
    //NOTE 这里有什么作用？
    this.nameSubject.next(null);
    this.currentUserSubject.next(null);
    console.log("登出成功！");
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
    const url = `${apiUrl}/account`;
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

  list(page = 1, size = 10): Observable<Page<User>> {
    const url = `${apiUrl}/user/list`;
    const params = {page: page + "", size: size + ""};
    return this.http.get<Page<User>>(url, {params: params}).pipe(
        catchError(this.handleError("list", null))
    );
  }

  searchByNickname(nickname: string, page = 1, size = 10): Observable<Page<User>> {
    const url = `${apiUrl}/user/search`;
    const params = {method: "nickname", nickname: nickname, page: page + "", size: size + ""};
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
