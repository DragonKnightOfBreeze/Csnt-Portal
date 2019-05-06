import {ProfessionLevel} from "../../enums/ProfessionLevel";
import {TeacherInfo} from "./TeacherInfo";

export class TeacherTeam {
  id: number;
  name: string;
  professionLevel: ProfessionLevel;
  introduce: string;
  createTime: string;
  teacherInfoList: TeacherInfo[];

}
