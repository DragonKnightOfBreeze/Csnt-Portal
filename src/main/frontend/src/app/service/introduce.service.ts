import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Introduce} from "../domain/entity/Introduce";
import {Observable, of} from "rxjs";
import {apiUrl} from "../../environments/environment.prod";
import {catchError} from "rxjs/operators";

/**
 * 专业特色介绍的服务类。
 */
@Injectable()
export class IntroduceService {
  constructor(private http: HttpClient) {
  }


  create(introduce: Introduce): Observable<Introduce> {
    const url = `${apiUrl}/introduce/create`;
    return this.http.post<Introduce>(url, introduce).pipe(
        catchError(this.handleError("create", new Introduce()))
    );
  }

  delete(id: number) {
    const url = `${apiUrl}/introduce/${id}`;
    return this.http.delete(url).pipe(
        catchError(this.handleError("delete", null))
    );
  }

  update(column: Introduce): Observable<Introduce> {
    const url = `${apiUrl}/introduce/update`;
    return this.http.put<Introduce>(url, column).pipe(
        catchError(this.handleError("update", column))
    );
  }

  get(id: number): Observable<Introduce> {
    const url = `${apiUrl}/introduce/${id}`;
    return this.http.get<Introduce>(url).pipe(
        catchError(this.handleError("get", null))
    );
  }

  //NOTE 返回的是数组/列表，不是Page泛型对象

  list(): Observable<Introduce[]> {
    const url = `${apiUrl}/introduce/list`;
    return this.http.get<Introduce[]>(url).pipe(
        catchError(this.handleError("list", []))
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
