import {ProfessionLevel} from "../../enums/ProfessionLevel";

export interface TeacherTeamSearchVo {
  name:string;
  levelSet:ProfessionLevel[];
  min:number;
  max:number;
}
