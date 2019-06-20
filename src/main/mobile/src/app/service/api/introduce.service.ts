import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Introduce} from "../../domain/entity/Introduce";
import {Observable} from "rxjs";
import {apiUrl} from "../../../environments/environment.prod";

/**
 * 专业特色介绍的服务类。
 */
@Injectable({providedIn: "root"})
export class IntroduceService {
  constructor(private http: HttpClient) {
  }


  create(introduce: Introduce): Observable<Introduce> {
    const url = `${apiUrl}/introduce/create`;
    return this.http.post<Introduce>(url, introduce);
  }

  delete(id: number) {
    const url = `${apiUrl}/introduce/${id}`;
    return this.http.delete(url);
  }

  update(introduce: Introduce): Observable<Introduce> {
    const url = `${apiUrl}/introduce/${introduce.id}`;
    return this.http.put<Introduce>(url, introduce);
  }

  get(id: number): Observable<Introduce> {
    const url = `${apiUrl}/introduce/${id}`;
    return this.http.get<Introduce>(url);
  }

  //NOTE 返回的是数组/列表，不是Page泛型对象

  list(): Observable<Introduce[]> {
    const url = `${apiUrl}/introduce/list`;
    return this.http.get<Introduce[]>(url);
  }
}
