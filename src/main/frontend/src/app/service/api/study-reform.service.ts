import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {StudyColumn} from "../../domain/entity/StudyColumn";
import {Observable, of} from "rxjs";
import {apiUrl} from "../../../environments/environment.prod";
import {catchError} from "rxjs/operators";
import {Page} from "../../domain/interface/Page";

/**
 * 学习专栏的服务类。
 */
@Injectable({providedIn: "root"})
export class StudyColumnService {
  constructor(private http: HttpClient) {
  }


  create(column: StudyColumn): Observable<StudyColumn> {
    const url = `${apiUrl}/study-column/create`;
    return this.http.post<StudyColumn>(url, column).pipe(
        catchError(this.handleError("create", new StudyColumn()))
    );
  }

  delete(id: number) {
    const url = `${apiUrl}/study-column/${id}`;
    return this.http.delete(url).pipe(
        catchError(this.handleError("delete", null))
    );
  }

  update(column: StudyColumn): Observable<StudyColumn> {
    const url = `${apiUrl}/study-column/${column.id}`;
    return this.http.put<StudyColumn>(url, column).pipe(
        catchError(this.handleError("update", column))
    );
  }

  get(id: number): Observable<StudyColumn> {
    const url = `${apiUrl}/study-column/${id}`;
    return this.http.get<StudyColumn>(url).pipe(
        catchError(this.handleError("get", null))
    );
  }

  list(page: number, size: number): Observable<Page<StudyColumn>> {
    const url = `${apiUrl}/study-column/list`;
    const params = {page: page + "", size: size + ""};
    return this.http.get<Page<StudyColumn>>(url, {params: params}).pipe(
        catchError(this.handleError("list", null))
    );
  }

  searchByTitle(title: string, page: number, size: number): Observable<Page<StudyColumn>> {
    const url = `${apiUrl}/study-column/search`;
    const params = {title: title, page: page + "", size: size + ""};
    return this.http.get<Page<StudyColumn>>(url, {params: params}).pipe(
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
