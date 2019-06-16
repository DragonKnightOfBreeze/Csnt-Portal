import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {DevelopmentColumn} from "../../domain/entity/DevelopmentColumn";
import {Observable} from "rxjs";
import {apiUrl} from "../../../environments/environment.prod";
import {catchError} from "rxjs/operators";
import {Page} from "../../domain/interface/Page";
import {handleError} from "../handler/error-handler";

/**
 * 专业发展专栏的服务类。
 */
@Injectable({providedIn: "root"})
export class DevelopmentColumnService {
  constructor(private http: HttpClient) {
  }


  create(column: DevelopmentColumn): Observable<DevelopmentColumn> {
    const url = `${apiUrl}/development-column/create`;
    return this.http.post<DevelopmentColumn>(url, column).pipe(
      catchError(handleError("create", new DevelopmentColumn()))
    );
  }

  delete(id: number) {
    const url = `${apiUrl}/development-column/${id}`;
    return this.http.delete(url).pipe(
      catchError(handleError("delete", null))
    );
  }

  update(column: DevelopmentColumn): Observable<DevelopmentColumn> {
    const url = `${apiUrl}/development-column/${column.id}`;
    return this.http.put<DevelopmentColumn>(url, column).pipe(
      catchError(handleError("update", column))
    );
  }

  get(id: number): Observable<DevelopmentColumn> {
    const url = `${apiUrl}/development-column/${id}`;
    return this.http.get<DevelopmentColumn>(url).pipe(
      catchError(handleError("get", null))
    );
  }

  list(page: number, size: number): Observable<Page<DevelopmentColumn>> {
    const url = `${apiUrl}/development-column/list`;
    const params = {page: page + "", size: size + ""};
    return this.http.get<Page<DevelopmentColumn>>(url, {params: params}).pipe(
      catchError(handleError("list", null))
    );
  }

  searchByTitle(title: string, page: number, size: number): Observable<Page<DevelopmentColumn>> {
    const url = `${apiUrl}/development-column/search`;
    const params = {title: title, page: page + "", size: size + ""};
    return this.http.get<Page<DevelopmentColumn>>(url, {params: params}).pipe(
      catchError(handleError("searchByTitle", null))
    );
  }
}
