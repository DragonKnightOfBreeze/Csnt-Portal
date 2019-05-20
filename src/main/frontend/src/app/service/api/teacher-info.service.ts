import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {TeacherInfo} from "../../domain/entity/TeacherInfo";
import {Observable} from "rxjs";
import {apiUrl} from "../../../environments/environment.prod";
import {catchError} from "rxjs/operators";
import {handleError} from "../handler/error-handler";

/**
 * 教师详情的服务类。
 */
@Injectable({providedIn: "root"})
export class TeacherInfoService {
  constructor(private http: HttpClient) {
  }

  create(teacherInfo: TeacherInfo): Observable<TeacherInfo> {
    const url = `${apiUrl}/teacher-info/create`;
    return this.http.post<TeacherInfo>(url, teacherInfo).pipe(
        catchError(handleError("create", new TeacherInfo()))
    );
  }

  delete(id: number) {
    const url = `${apiUrl}/teacher-info/${id}`;
    return this.http.delete(url).pipe(
        catchError(handleError("delete", null))
    );
  }

  update(teacherInfo: TeacherInfo): Observable<TeacherInfo> {
    const url = `${apiUrl}/teacher-info/${teacherInfo.id}`;
    return this.http.put<TeacherInfo>(url, teacherInfo).pipe(
        catchError(handleError("update", teacherInfo))
    );
  }

  get(id: number): Observable<TeacherInfo> {
    const url = `${apiUrl}/teacher-info/${id}`;
    return this.http.get<TeacherInfo>(url).pipe(
        catchError(handleError("get", null))
    );
  }
}
