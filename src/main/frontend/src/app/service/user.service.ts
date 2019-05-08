import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {User} from "../domain/entity/User";
import {Observable, of} from "rxjs";
import {apiUrl} from "../../environments/environment.prod";
import {catchError, tap} from "rxjs/operators";
import {Page} from "../domain/interface/Page";
import {UserLoginVo} from "../domain/vo/UserLoginVo";
import {JwtResponseVo} from "../security/JwtResponseVo";
import {UserResetPasswordVo} from "../domain/vo/UserResetPasswordVo";

/**
 * 用户的服务类。
 */
@Injectable()
export class UserService {
  constructor(private http: HttpClient) {
  }


  login(vo: UserLoginVo): Observable<JwtResponseVo> {
    const url = `${apiUrl}/login`;
    return this.http.post<JwtResponseVo>(url, vo).pipe(
        tap(_ => console.log("登录成功！")),
        catchError(error => {
          console.log("登录失败！");
          console.log(error);
          return of(null);
        })
    );
  }

  resetPassword(vo: UserResetPasswordVo) {
    const url = `${apiUrl}/reset-password`;
    return this.http.put(url, vo).pipe(
        tap(_ => console.log("重置密码成功！")),
        catchError(error => {
          console.log("重置密码失败！");
          console.log(error);
          return of(null);
        })
    );
  }

  register(user: User): Observable<User> {
    const url = `${apiUrl}/register`;
    return this.http.put<User>(url, user).pipe(
        tap(_ => console.log("注册成功！")),
        catchError(error => {
          console.log("注册成功！");
          console.log(error);
          return of(null);
        })
    );
  }

  updateAccountInfo(column: User): Observable<User> {
    const url = `${apiUrl}/account`;
    return this.http.put<User>(url, column).pipe(
        catchError(this.handleError("updateAccountInfo", column))
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
