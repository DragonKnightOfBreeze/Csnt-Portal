import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Dynamic} from "../../domain/entity/Dynamic";
import {Observable} from "rxjs";
import {apiUrl} from "../../../environments/environment.prod";
import {DynamicQueryVo} from "../../domain/vo/DynamicQueryVo";
import {Page} from "../../domain/interface/Page";
import {DynamicCategory} from "../../domain/enum/DynamicCategory";

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
    return this.http.post<Dynamic>(url, dynamic);
  }

  delete(id: number) {
    const url = `${apiUrl}/dynamic/${id}`;
    return this.http.delete(url);
  }

  get(id: number): Observable<Dynamic> {
    const url = `${apiUrl}/dynamic/${id}`;
    return this.http.get<Dynamic>(url);
  }

  //NOTE 返回的不是Dynamic[]，而是Page<Dynamic>，其中Page是匹配的接口

  list(page: number, size: number): Observable<Page<Dynamic>> {
    const url = `${apiUrl}/dynamic/list`;
    const params = {page: page + "", size: size + ""};
    return this.http.get<Page<Dynamic>>(url, {params: params});
  }

  searchBySubject(subject: string, page: number, size: number): Observable<Page<Dynamic>> {
    const url = `${apiUrl}/dynamic/search`;
    const params = {subject: subject, page: page + "", size: size + ""};
    return this.http.get<Page<Dynamic>>(url, {params: params});
  }

  searchBySponsorUsername(sponsorUsername: string, page: number, size: number): Observable<Page<Dynamic>> {
    const url = `${apiUrl}/dynamic/search`;
    const params = {sponsorUsername: sponsorUsername, page: page + "", size: size + ""};
    return this.http.get<Page<Dynamic>>(url, {params: params});
  }

  searchByCategory(categorySet: DynamicCategory[], page: number, size: number): Observable<Page<Dynamic>> {
    const url = `${apiUrl}/dynamic/search`;
    const params = {categorySet: categorySet, page: page + "", size: size + ""};
    return this.http.get<Page<Dynamic>>(url, {params: params});
  }

  advanceSearch(vo: DynamicQueryVo, page: number, size: number): Observable<Page<Dynamic>> {
    const url = `${apiUrl}/dynamic/advanceSearch`;
    const params = {page: page + "", size: size + ""};
    return this.http.post<Page<Dynamic>>(url, vo, {params: params});
  }
}
