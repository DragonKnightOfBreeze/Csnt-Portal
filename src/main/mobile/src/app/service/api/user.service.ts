import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {User} from "../../domain/entity/User";
import {BehaviorSubject, Observable} from "rxjs";
import {apiUrl} from "../../../environments/environment.prod";
import {tap} from "rxjs/operators";
import {Page} from "../../domain/interface/Page";
import {UserLoginVo} from "../../domain/vo/UserLoginVo";
import {UserResetPasswordVo} from "../../domain/vo/UserResetPasswordVo";
import {JwtUserResponseVo} from "../../domain/vo/JwtUserResponseVo";
import {Dynamic} from "../../domain/entity/Dynamic";
import {Storage} from "@ionic/storage";
import {Events} from "@ionic/angular";

//使用ionic自身的storage来存储用户信息，也可以直接当做前端数据库使用。
//storage的get、set、remove方法返回Promise
//当用户登录和注册时，可以使用this.events.push来推送特殊事件。

/**
 * 用户的服务类。
 */
@Injectable({providedIn: "root"})
export class UserService {
  /**当前用户信息的行为主题对象。*/
  public currentUser$ = new BehaviorSubject<JwtUserResponseVo>(null);

  constructor(private http: HttpClient,
              private storage: Storage,
              private events: Events) {
    //这个方法一定要写到构造函数中，否则报错undefined
    this.initCurrentUser();
  }


  private initCurrentUser() {
    this.storage.get("currentUser").then((value: JwtUserResponseVo) => {
      this.currentUser$.next(value);
    });
  }

  getCurrentUser() {
    return this.currentUser$.value;
  }

  hasLogin() {
    return this.currentUser$.value != null;
  }

  isAdmin() {
    return this.currentUser$.value && this.currentUser$.value.role == "ADMIN";
  }


  login(vo: UserLoginVo): Observable<JwtUserResponseVo> {
    const url = `${apiUrl}/login`;
    return this.http.post<JwtUserResponseVo>(url, vo).pipe(
      tap(currentUser => {
        //如果用户和用户令牌存在，则进行后续操作
        if (currentUser && currentUser.token) {
          //首先清空当前用户数据
          this.storage.remove("currentUser");
          //如果记住登录，则存储到storage中
          if (vo.rememberMe) {
            this.storage.set("currentUser", currentUser)
          }
          //将当前用户信息推送到对应的可观察流对象中
          this.currentUser$.next(currentUser);
          this.events.publish("user:login");
        }
      })
    );
  }

  logout() {
    //从storage中移除当前用户信息
    this.storage.remove("currentUser");
    //清空当前用户信息可观察流对象的值
    this.currentUser$.next(null);
    this.events.publish("user:logout");
  }

  resetPassword(vo: UserResetPasswordVo) {
    const url = `${apiUrl}/reset-password`;
    return this.http.put(url, vo);
  }

  register(user: User): Observable<User> {
    const url = `${apiUrl}/register`;
    return this.http.post<User>(url, user);
  }

  updateAccountInfo(user: User): Observable<User> {
    const url = `${apiUrl}/account/${user.username}`;
    return this.http.put<User>(url, user);
  }

  getAccountInfo(username: string): Observable<User> {
    const url = `${apiUrl}/account/${username}`;
    return this.http.get<User>(url);
  }


  get(id: number): Observable<User> {
    const url = `${apiUrl}/user/${id}`;
    return this.http.get<User>(url);
  }

  getDynamicList(id: number): Observable<Dynamic[]> {
    const url = `${apiUrl}/user/${id}/dynamic-list`;
    return this.http.get<Dynamic[]>(url);
  }

  list(page: number, size: number): Observable<Page<User>> {
    const url = `${apiUrl}/user/list`;
    const params = {page: page + "", size: size + ""};
    return this.http.get<Page<User>>(url, {params: params});
  }

  searchByNickname(nickname: string, page: number, size: number): Observable<Page<User>> {
    const url = `${apiUrl}/user/search`;
    const params = {nickname: nickname, page: page + "", size: size + ""};
    return this.http.get<Page<User>>(url, {params: params});
  }

  exists(user: User): Observable<boolean> {
    const url = `${apiUrl}/exists-user`;
    const params = {username: user.username, email: user.email, phoneNum: user.phoneNum};
    return this.http.get<boolean>(url, {params: params});
  }
}
