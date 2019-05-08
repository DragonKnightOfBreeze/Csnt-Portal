import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {DevelopmentColumn} from "../domain/entity/DevelopmentColumn";
import {Observable, of} from "rxjs";
import {apiUrl} from "../../environments/environment.prod";
import {catchError} from "rxjs/operators";
import {Page} from "../domain/interface/Page";

/**
 * 专业发展专栏的服务类。
 */
@Injectable()
export class DevelopmentColumnService {
  constructor(private http: HttpClient) {
  }


  create(column: DevelopmentColumn): Observable<DevelopmentColumn> {
    const url = `${apiUrl}/development-column/create`;
    return this.http.post<DevelopmentColumn>(url, column).pipe(
        catchError(this.handleError("create", new DevelopmentColumn()))
    );
  }

  delete(id: number) {
    const url = `${apiUrl}/development-column/${id}`;
    return this.http.delete(url).pipe(
        catchError(this.handleError("delete", null))
    );
  }

  update(column: DevelopmentColumn): Observable<DevelopmentColumn> {
    const url = `${apiUrl}/development-column/update`;
    return this.http.put<DevelopmentColumn>(url, column).pipe(
        catchError(this.handleError("update", column))
    );
  }

  get(id: number): Observable<DevelopmentColumn> {
    const url = `${apiUrl}/development-column/${id}`;
    return this.http.get<DevelopmentColumn>(url).pipe(
        catchError(this.handleError("get", null))
    );
  }

  list(page = 1, size = 10): Observable<Page<DevelopmentColumn>> {
    const url = `${apiUrl}/development-column/list`;
    const params = {page: page + "", size: size + ""};
    return this.http.get<Page<DevelopmentColumn>>(url, {params: params}).pipe(
        catchError(this.handleError("list", null))
    );
  }

  searchByTitle(title: string, page = 1, size = 10): Observable<Page<DevelopmentColumn>> {
    const url = `${apiUrl}/development-column/search`;
    const params = {method: "title", title: title, page: page + "", size: size + ""};
    return this.http.get<Page<DevelopmentColumn>>(url, {params: params}).pipe(
        catchError(this.handleError("searchByTitle", null))
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
