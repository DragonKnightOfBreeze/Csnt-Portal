import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Dynamic} from "../../domain/entity/Dynamic";
import {Observable} from "rxjs";
import {apiUrl} from "../../../environments/environment.prod";
import {DynamicSearchVo} from "../../domain/vo/DynamicSearchVo";
import {catchError} from "rxjs/operators";
import {Page} from "../../domain/interface/Page";
import {DynamicCategory} from "../../enum/DynamicCategory";
import {handleError} from "../handler/error-handler.service";

/**
 * 实时动态的服务类。
 */
@Injectable({providedIn: "root"})
export class DynamicService {
  constructor(private http: HttpClient) {
  }


  //NOTE 错误处理规则：统一调用私有的handleError()方法。

  create(dynamic: Dynamic): Observable<Dynamic> {
    const url = `${apiUrl}/dynamic/create`;
    return this.http.post<Dynamic>(url, dynamic).pipe(
      catchError(handleError("create", new Dynamic()))
    );
  }

  delete(id: number) {
    const url = `${apiUrl}/dynamic/${id}`;
    return this.http.delete(url).pipe(
      catchError(handleError("delete", null))
    );
  }

  get(id: number): Observable<Dynamic> {
    const url = `${apiUrl}/dynamic/${id}`;
    return this.http.get<Dynamic>(url).pipe(
      catchError(handleError("get", null))
    );
  }

  //NOTE 返回的不是Dynamic[]，而是Page<Dynamic>，其中Page是匹配的接口

  list(page: number, size: number): Observable<Page<Dynamic>> {
    const url = `${apiUrl}/dynamic/list`;
    const params = {page: page + "", size: size + ""};
    return this.http.get<Page<Dynamic>>(url, {params: params}).pipe(
      catchError(handleError("list", null))
    );
  }

  searchBySubject(subject: string, page: number, size: number): Observable<Page<Dynamic>> {
    const url = `${apiUrl}/dynamic/search`;
    const params = {subject: subject, page: page + "", size: size + ""};
    return this.http.get<Page<Dynamic>>(url, {params: params}).pipe(
      catchError(handleError("searchBySubject", null))
    );
  }

  searchBySponsorUsername(sponsorUsername: string, page: number, size: number): Observable<Page<Dynamic>> {
    const url = `${apiUrl}/dynamic/search`;
    const params = {sponsorUsername: sponsorUsername, page: page + "", size: size + ""};
    return this.http.get<Page<Dynamic>>(url, {params: params}).pipe(
      catchError(handleError("searchBySponsorUsername", null))
    );
  }

  searchByCategory(categorySet: DynamicCategory[], page: number, size: number): Observable<Page<Dynamic>> {
    const url = `${apiUrl}/dynamic/search`;
    const params = {categorySet: categorySet, page: page + "", size: size + ""};
    return this.http.get<Page<Dynamic>>(url, {params: params}).pipe(
      catchError(handleError("searchByCategory", null))
    );
  }

  advanceSearch(vo: DynamicSearchVo, page: number, size: number): Observable<Page<Dynamic>> {
    const url = `${apiUrl}/dynamic/advanceSearch`;
    const params = {page: page + "", size: size + ""};
    return this.http.post<Page<Dynamic>>(url, vo, {params: params}).pipe(
      catchError(handleError("advanceSearch", null))
    );
  }
}
