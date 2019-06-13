import {Gender} from "../../enum/Gender";
import {Profession} from "../../enum/Profession";
import {TeacherTeam} from "./TeacherTeam";

export class TeacherInfo {
  id: number;
  name: string;
  gender: Gender;
  profession: Profession;
  introduce: string;
  teacherTeam: TeacherTeam | null;
}
