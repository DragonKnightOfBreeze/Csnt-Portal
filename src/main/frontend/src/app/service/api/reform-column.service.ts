import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {ReformColumn} from "../../domain/entity/ReformColumn";
import {Observable} from "rxjs";
import {apiUrl} from "../../../environments/environment.prod";
import {catchError} from "rxjs/operators";
import {Page} from "../../domain/interface/Page";
import {handleError} from "../handler/error-handler";

/**
 * 教学改革专栏的服务类。
 */
@Injectable({providedIn: "root"})
export class ReformColumnService {
  constructor(private http: HttpClient) {
  }


  create(column: ReformColumn): Observable<ReformColumn> {
    const url = `${apiUrl}/reform-column/create`;
    return this.http.post<ReformColumn>(url, column).pipe(
        catchError(handleError("create", new ReformColumn()))
    );
  }

  delete(id: number) {
    const url = `${apiUrl}/reform-column/${id}`;
    return this.http.delete(url).pipe(
        catchError(handleError("delete", null))
    );
  }

  update(column: ReformColumn): Observable<ReformColumn> {
    const url = `${apiUrl}/reform-column/${column.id}`;
    return this.http.put<ReformColumn>(url, column).pipe(
        catchError(handleError("update", column))
    );
  }

  get(id: number): Observable<ReformColumn> {
    const url = `${apiUrl}/reform-column/${id}`;
    return this.http.get<ReformColumn>(url).pipe(
        catchError(handleError("get", null))
    );
  }

  list(page: number, size: number): Observable<Page<ReformColumn>> {
    const url = `${apiUrl}/reform-column/list`;
    const params = {page: page + "", size: size + ""};
    return this.http.get<Page<ReformColumn>>(url, {params: params}).pipe(
        catchError(handleError("list", null))
    );
  }

  searchByTitle(title: string, page: number, size: number): Observable<Page<ReformColumn>> {
    const url = `${apiUrl}/reform-column/search`;
    const params = {title: title, page: page + "", size: size + ""};
    return this.http.get<Page<ReformColumn>>(url, {params: params}).pipe(
        catchError(handleError("searchByTitle", null))
    );
  }
}
