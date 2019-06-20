import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {DevelopmentColumn} from "../../domain/entity/DevelopmentColumn";
import {Observable} from "rxjs";
import {apiUrl} from "../../../environments/environment.prod";
import {Page} from "../../domain/interface/Page";

/**
 * 专业发展专栏的服务类。
 */
@Injectable({providedIn: "root"})
export class DevelopmentColumnService {
  constructor(private http: HttpClient) {
  }


  create(column: DevelopmentColumn): Observable<DevelopmentColumn> {
    const url = `${apiUrl}/development-column/create`;
    return this.http.post<DevelopmentColumn>(url, column);
  }

  delete(id: number) {
    const url = `${apiUrl}/development-column/${id}`;
    return this.http.delete(url);
  }

  update(column: DevelopmentColumn): Observable<DevelopmentColumn> {
    const url = `${apiUrl}/development-column/${column.id}`;
    return this.http.put<DevelopmentColumn>(url, column);
  }

  get(id: number): Observable<DevelopmentColumn> {
    const url = `${apiUrl}/development-column/${id}`;
    return this.http.get<DevelopmentColumn>(url);
  }

  list(page: number, size: number): Observable<Page<DevelopmentColumn>> {
    const url = `${apiUrl}/development-column/list`;
    const params = {page: page + "", size: size + ""};
    return this.http.get<Page<DevelopmentColumn>>(url, {params: params});
  }

  searchByTitle(title: string, page: number, size: number): Observable<Page<DevelopmentColumn>> {
    const url = `${apiUrl}/development-column/search`;
    const params = {title: title, page: page + "", size: size + ""};
    return this.http.get<Page<DevelopmentColumn>>(url, {params: params});
  }
}
