import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {TeacherInfo} from "../../domain/entity/TeacherInfo";
import {Observable} from "rxjs";
import {apiUrl} from "../../../environments/environment.prod";

/**
 * 教师详情的服务类。
 */
@Injectable({providedIn: "root"})
export class TeacherInfoService {
  constructor(private http: HttpClient) {
  }

  create(teacherInfo: TeacherInfo): Observable<TeacherInfo> {
    const url = `${apiUrl}/teacher-info/create`;
    return this.http.post<TeacherInfo>(url, teacherInfo);
  }

  delete(id: number) {
    const url = `${apiUrl}/teacher-info/${id}`;
    return this.http.delete(url);
  }

  update(teacherInfo: TeacherInfo): Observable<TeacherInfo> {
    const url = `${apiUrl}/teacher-info/${teacherInfo.id}`;
    return this.http.put<TeacherInfo>(url, teacherInfo);
  }

  get(id: number): Observable<TeacherInfo> {
    const url = `${apiUrl}/teacher-info/${id}`;
    return this.http.get<TeacherInfo>(url);
  }
}
