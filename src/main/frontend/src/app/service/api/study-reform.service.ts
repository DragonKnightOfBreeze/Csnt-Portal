import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {StudyColumn} from "../../domain/entity/StudyColumn";
import {Observable} from "rxjs";
import {apiUrl} from "../../../environments/environment.prod";
import {catchError} from "rxjs/operators";
import {Page} from "../../domain/interface/Page";
import {handleError} from "../handler/error-handler";

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
        catchError(handleError("create", new StudyColumn()))
    );
  }

  delete(id: number) {
    const url = `${apiUrl}/study-column/${id}`;
    return this.http.delete(url).pipe(
        catchError(handleError("delete", null))
    );
  }

  update(column: StudyColumn): Observable<StudyColumn> {
    const url = `${apiUrl}/study-column/${column.id}`;
    return this.http.put<StudyColumn>(url, column).pipe(
        catchError(handleError("update", column))
    );
  }

  get(id: number): Observable<StudyColumn> {
    const url = `${apiUrl}/study-column/${id}`;
    return this.http.get<StudyColumn>(url).pipe(
        catchError(handleError("get", null))
    );
  }

  list(page: number, size: number): Observable<Page<StudyColumn>> {
    const url = `${apiUrl}/study-column/list`;
    const params = {page: page + "", size: size + ""};
    return this.http.get<Page<StudyColumn>>(url, {params: params}).pipe(
        catchError(handleError("list", null))
    );
  }

  searchByTitle(title: string, page: number, size: number): Observable<Page<StudyColumn>> {
    const url = `${apiUrl}/study-column/search`;
    const params = {title: title, page: page + "", size: size + ""};
    return this.http.get<Page<StudyColumn>>(url, {params: params}).pipe(
        catchError(handleError("searchByTitle", null))
    );
  }
}
