import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Dynamic} from "../domain/entity/Dynamic";
import {Observable, of} from "rxjs";
import {apiUrl} from "../../environments/environment.prod";
import {User} from "../domain/entity/User";
import {DynamicSearchVo} from "../domain/vo/DynamicSearchVo";
import {catchError} from "rxjs/operators";
import {Page} from "../domain/interface/Page";
import {DynamicCategory} from "../enums/DynamicCategory";

/**
 * 实时动态的服务类。
 */
@Injectable()
export class DynamicService {
  constructor(private http: HttpClient) {
  }


  //NOTE 错误处理规则：统一调用私有的handleError()方法。

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
    const url = `${apiUrl}/dynamic/${id}/sponsor-user`;
    return this.http.get<User>(url,).pipe(
        catchError(this.handleError("getSponsorUser", null))
    );
  }

  //NOTE 返回的不是Dynamic[]，而是Page<Dynamic>，其中Page是匹配的接口

  list(page = 1, size = 10): Observable<Page<Dynamic>> {
    const url = `${apiUrl}/dynamic/list`;
    const params = {page: page + "", size: size + ""};
    return this.http.get<Page<Dynamic>>(url, {params: params}).pipe(
        catchError(this.handleError("list", null))
    );
  }

  searchBySubject(subject: string, page = 1, size = 10): Observable<Page<Dynamic>> {
    const url = `${apiUrl}/dynamic/search`;
    const params = {method: "subject", subject: subject, page: page + "", size: size + ""};
    return this.http.get<Page<Dynamic>>(url, {params: params}).pipe(
        catchError(this.handleError("searchBySubject", null))
    );
  }

  searchBySponsorUsername(sponsorUsername: string, page = 1, size = 10): Observable<Page<Dynamic>> {
    const url = `${apiUrl}/dynamic/search`;
    const params = {method: "sponsorUsername", sponsorUsername: sponsorUsername, page: page + "", size: size + ""};
    return this.http.get<Page<Dynamic>>(url, {params: params}).pipe(
        catchError(this.handleError("searchBySponsorUsername", null))
    );
  }

  searchByCategory(category: DynamicCategory[], page = 1, size = 10): Observable<Page<Dynamic>> {
    const url = `${apiUrl}/dynamic/search`;
    const params = {method: "category", category: category, page: page + "", size: size + ""};
    return this.http.get<Page<Dynamic>>(url, {params: params}).pipe(
        catchError(this.handleError("searchByCategory", null))
    );
  }

  advanceSearch(vo: DynamicSearchVo, page = 1, size = 10): Observable<Page<Dynamic>> {
    const url = `${apiUrl}/dynamic/advanceSearch`;
    const params = {page: page + "", size: size + ""};
    return this.http.post<Page<Dynamic>>(url, vo, {params: params}).pipe(
        catchError(this.handleError("advanceSearch", null))
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
