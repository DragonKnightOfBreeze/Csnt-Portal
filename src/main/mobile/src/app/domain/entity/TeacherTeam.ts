import {ProfessionLevel} from "../enum/ProfessionLevel";
import {TeacherInfo} from "./TeacherInfo";

export class TeacherTeam {
  id: number;
  name: string;
  professionLevel: ProfessionLevel;
  introduce: string;
  createTime: string;
  /**NOTE 需要手动调用特定服务获取数据。*/
  teacherInfoList: TeacherInfo[];
}
