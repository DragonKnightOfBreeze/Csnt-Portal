import {ProfessionLevel} from "../../enums/ProfessionLevel";

export class TeacherTeamSearchVo {
  name: string = "";
  levelSet: ProfessionLevel[] = [];
  min: number;
  max: number;
}
