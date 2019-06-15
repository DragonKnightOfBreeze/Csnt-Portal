import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {TeacherTeam} from "../../domain/entity/TeacherTeam";
import {Observable} from "rxjs";
import {apiUrl} from "../../../environments/environment.prod";
import {catchError} from "rxjs/operators";
import {Page} from "../../domain/interface/Page";
import {ProfessionLevel} from "../../domain/enum/ProfessionLevel";
import {TeacherTeamQueryVo} from "../../domain/vo/TeacherTeamQueryVo";
import {TeacherInfo} from "../../domain/entity/TeacherInfo";
import {handleError} from "../handler/error-handler.service";

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
      catchError(handleError("create", new TeacherTeam()))
    );
  }

  delete(id: number) {
    const url = `${apiUrl}/teacher-team/${id}`;
    return this.http.delete(url).pipe(
      catchError(handleError("delete", null))
    );
  }

  update(teacherTeam: TeacherTeam): Observable<TeacherTeam> {
    const url = `${apiUrl}/teacher-team/${teacherTeam.id}`;
    return this.http.put<TeacherTeam>(url, teacherTeam).pipe(
      catchError(handleError("update", teacherTeam))
    );
  }

  get(id: number): Observable<TeacherTeam> {
    const url = `${apiUrl}/teacher-team/${id}`;
    return this.http.get<TeacherTeam>(url).pipe(
      catchError(handleError("get", null))
    );
  }

  getTeacherInfoList(id: number): Observable<TeacherInfo[]> {
    const url = `${apiUrl}/teacher-team/${id}/teacher-info-list`;
    return this.http.get<TeacherInfo[]>(url).pipe(
      catchError(handleError("getTeacherInfoList", []))
    );
  }

  list(page: number, size: number): Observable<Page<TeacherTeam>> {
    const url = `${apiUrl}/teacher-team/list`;
    const params = {page: page + "", size: size + ""};
    return this.http.get<Page<TeacherTeam>>(url, {params: params}).pipe(
      catchError(handleError("list", null))
    );
  }

  searchByName(name: string, page: number, size: number): Observable<Page<TeacherTeam>> {
    const url = `${apiUrl}/teacher-team/search`;
    const params = {name: name, page: page + "", size: size + ""};
    return this.http.get<Page<TeacherTeam>>(url, {params: params}).pipe(
      catchError(handleError("searchByName", null))
    );
  }

  searchByProfessionLevel(levelSet: ProfessionLevel[], page: number, size: number): Observable<Page<TeacherTeam>> {
    const url = `${apiUrl}/teacher-team/search`;
    const params = {levelSet: levelSet, page: page + "", size: size + ""};
    return this.http.get<Page<TeacherTeam>>(url, {params: params}).pipe(
      catchError(handleError("searchByProfessionLevel", null))
    );
  }

  searchByTeacherCount(min: number, max: number, page: number, size: number): Observable<Page<TeacherTeam>> {
    const url = `${apiUrl}/teacher-team/search`;
    const params = {min: min + "", max: max + "", page: page + "", size: size + ""};
    return this.http.get<Page<TeacherTeam>>(url, {params: params}).pipe(
      catchError(handleError("searchByTeacherCount", null))
    );
  }

  advanceSearch(vo: TeacherTeamQueryVo, page: number, size: number): Observable<Page<TeacherTeam>> {
    const url = `${apiUrl}/dynamic/advanceSearch`;
    const params = {page: page + "", size: size + ""};
    return this.http.post<Page<TeacherTeam>>(url, vo, {params: params}).pipe(
      catchError(handleError("advanceSearch", null))
    );
  }
}
