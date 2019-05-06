import {Gender} from "../../enums/Gender";
import {Profession} from "../../enums/Profession";

export class TeacherInfo {
  id: number;
  name: string;
  gender: Gender;
  profession: Profession;
  introduce: string;
}
