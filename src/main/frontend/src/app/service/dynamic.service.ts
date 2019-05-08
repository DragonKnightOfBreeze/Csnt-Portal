import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Dynamic} from "../domain/entity/Dynamic";
import {Observable, of} from "rxjs";
import {apiUrl} from "../../environments/environment.prod";
import {User} from "../domain/entity/User";
import {DynamicSearchVo} from "../domain/vo/DynamicSearchVo";
import {StringUtils} from "../utils/StringUtils";
import {catchError} from "rxjs/operators";

/**
 * 动态的服务。
 */
@Injectable()
export class DynamicService {
  constructor(private http: HttpClient) {
  }

  //NOTE
  //错误处理规则：统一调用私有的handleError()方法。
  //create方法返回新的对象，update方法返回原本的对象，delete方法返回null，get方法返回null，list方法返回[]。

  create(dynamic: Dynamic): Observable<Dynamic> {
    const url = `${apiUrl}/dynamic/create`;
    return this.http.post<Dynamic>(url, dynamic).pipe(
        catchError(this.handleError("create", new Dynamic()))
    );
  }

  delete(id: number) {
    const url = `${apiUrl}/dynamic/${id}`;
    return this.http.delete(url).pipe(
        catchError(this.handleError("delete", null))
    );
  }

  get(id: number): Observable<Dynamic> {
    const url = `${apiUrl}/dynamic/${id}`;
    return this.http.get<Dynamic>(url).pipe(
        catchError(this.handleError("get", null))
    );
  }

  getSponsorUser(id: number): Observable<User> {
    const url = `${apiUrl}/dynamic/${id}`;
    return this.http.get<User>(url).pipe(
        catchError(this.handleError("getSponsorUser", null))
    );
  }

  list(page?: number, size?: number): Observable<Dynamic[]> {
    const params = StringUtils.toUrlParams(["page", page], ["size", size]);
    const url = `${apiUrl}/dynamic/list?${params}}`;
    return this.http.get<Dynamic[]>(url).pipe(
        catchError(this.handleError("list", []))
    );
  }

  searchBySubject(subject: string, page = 1, size = 10): Observable<Dynamic[]> {
    const params = StringUtils.toUrlParams(["subject", subject], ["page", page], ["size", size]);
    const url = `${apiUrl}/dynamic/list?${params}`;
    return this.http.get<Dynamic[]>(url).pipe(
        catchError(this.handleError("searchBySubject", []))
    );
  }

  searchBySponsorUsername(sponsorUsername: string, page = 1, size = 10): Observable<Dynamic[]> {
    const params = StringUtils.toUrlParams(["sponsorUsername", sponsorUsername], ["page", page], ["size", size]);
    const url = `${apiUrl}/dynamic/list?${params}`;
    return this.http.get<Dynamic[]>(url).pipe(
        catchError(this.handleError("searchBySponsorUsername", []))
    );
  }

  searchByCategory(category: string[], page = 1, size = 10): Observable<Dynamic[]> {
    const params = StringUtils.toUrlParams(["category", category], ["page", page], ["size", size]);
    const url = `${apiUrl}/dynamic/list?${params}`;
    return this.http.get<Dynamic[]>(url).pipe(
        catchError(this.handleError("searchByCategory", []))
    );
  }

  advanceSearch(vo: DynamicSearchVo, page = 1, size = 10): Observable<Dynamic[]> {
    const params = StringUtils.toUrlParams(["page", page], ["size", size]);
    const url = `${apiUrl}/dynamic/advanceSearch?${params}`;
    return this.http.post<Dynamic[]>(url, vo).pipe(
        catchError(this.handleError("advanceSearch", []))
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
