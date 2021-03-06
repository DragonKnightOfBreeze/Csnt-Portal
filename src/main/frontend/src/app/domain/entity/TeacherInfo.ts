import {Gender} from "../../enums/Gender";
import {Profession} from "../../enums/Profession";
import {TeacherTeam} from "./TeacherTeam";

export class TeacherInfo {
  id: number;
  name: string;
  gender: Gender;
  profession: Profession;
  introduce: string;
  teacherTeam: TeacherTeam | null;
}
