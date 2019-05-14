import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {TeacherTeam} from "../../domain/entity/TeacherTeam";
import {Observable, of} from "rxjs";
import {apiUrl} from "../../../environments/environment.prod";
import {catchError} from "rxjs/operators";
import {Page} from "../../domain/interface/Page";
import {ProfessionLevel} from "../../enums/ProfessionLevel";
import {TeacherTeamSearchVo} from "../../domain/vo/TeacherTeamSearchVo";

/**
 * 教师队伍的服务类。
 */
@Injectable({providedIn: "root"})
export class TeacherTeamService {
  constructor(private http: HttpClient) {
  }


  create(teacherTeam: TeacherTeam): Observable<TeacherTeam> {
    const url = `${apiUrl}/teacher-team/create`;
    return this.http.post<TeacherTeam>(url, teacherTeam).pipe(
        catchError(this.handleError("create", new TeacherTeam()))
    );
  }

  delete(id: number) {
    const url = `${apiUrl}/teacher-team/${id}`;
    return this.http.delete(url).pipe(
        catchError(this.handleError("delete", null))
    );
  }

  update(teacherTeam: TeacherTeam): Observable<TeacherTeam> {
    const url = `${apiUrl}/teacher-team/${teacherTeam.id}`;
    return this.http.put<TeacherTeam>(url, teacherTeam).pipe(
        catchError(this.handleError("update", teacherTeam))
    );
  }

  get(id: number): Observable<TeacherTeam> {
    const url = `${apiUrl}/teacher-team/${id}`;
    return this.http.get<TeacherTeam>(url).pipe(
        catchError(this.handleError("get", null))
    );
  }

  list(page = 1, size = 10): Observable<Page<TeacherTeam>> {
    const url = `${apiUrl}/teacher-team/list`;
    const params = {page: page + "", size: size + ""};
    return this.http.get<Page<TeacherTeam>>(url, {params: params}).pipe(
        catchError(this.handleError("list", null))
    );
  }

  searchByName(name: string, page = 1, size = 10): Observable<Page<TeacherTeam>> {
    const url = `${apiUrl}/teacher-team/search`;
    const params = {method: "name", name: name, page: page + "", size: size + ""};
    return this.http.get<Page<TeacherTeam>>(url, {params: params}).pipe(
        catchError(this.handleError("searchByName", null))
    );
  }

  searchByProfessionLevel(professionLevel: ProfessionLevel, page = 1, size = 10): Observable<Page<TeacherTeam>> {
    const url = `${apiUrl}/teacher-team/search`;
    const params = {method: "professionLevel", professionLevel: professionLevel, page: page + "", size: size + ""};
    return this.http.get<Page<TeacherTeam>>(url, {params: params}).pipe(
        catchError(this.handleError("searchByProfessionLevel", null))
    );
  }

  searchByTeacherCount(min: number, max: number, page = 1, size = 10): Observable<Page<TeacherTeam>> {
    const url = `${apiUrl}/teacher-team/search`;
    const params = {method: "teacherCount", min: min + "", max: max + "", page: page + "", size: size + ""};
    return this.http.get<Page<TeacherTeam>>(url, {params: params}).pipe(
        catchError(this.handleError("searchByTeacherCount", null))
    );
  }

  advanceSearch(vo: TeacherTeamSearchVo, page = 1, size = 10): Observable<Page<TeacherTeam>> {
    const url = `${apiUrl}/dynamic/advanceSearch`;
    const params = {page: page + "", size: size + ""};
    return this.http.post<Page<TeacherTeam>>(url, vo, {params: params}).pipe(
        catchError(this.handleError("advanceSearch", null))
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
