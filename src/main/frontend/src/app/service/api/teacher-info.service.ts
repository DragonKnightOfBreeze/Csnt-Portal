import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {TeacherInfo} from "../../domain/entity/TeacherInfo";
import {Observable, of} from "rxjs";
import {apiUrl} from "../../../environments/environment.prod";
import {catchError} from "rxjs/operators";
import {TeacherTeam} from "../../domain/entity/TeacherTeam";

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
        catchError(this.handleError("create", new TeacherInfo()))
    );
  }

  delete(id: number) {
    const url = `${apiUrl}/teacher-info/${id}`;
    return this.http.delete(url).pipe(
        catchError(this.handleError("delete", null))
    );
  }

  update(teacherInfo: TeacherInfo): Observable<TeacherInfo> {
    const url = `${apiUrl}/teacher-info/${teacherInfo.id}`;
    return this.http.put<TeacherInfo>(url, teacherInfo).pipe(
        catchError(this.handleError("update", teacherInfo))
    );
  }

  get(id: number): Observable<TeacherInfo> {
    const url = `${apiUrl}/teacher-info/${id}`;
    return this.http.get<TeacherInfo>(url).pipe(
        catchError(this.handleError("get", null))
    );
  }

  getTeacherTeam(id: number): Observable<TeacherTeam> {
    const url = `${apiUrl}/teacher-info/${id}/teacher-team`;
    return this.http.get<TeacherTeam>(url,).pipe(
        catchError(this.handleError("getTeacherTeam", null))
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
