import {ProfessionLevel} from "../../enums/ProfessionLevel";

export class TeacherTeamSearchVo {
  name: string = "";
  levelSet: ProfessionLevel[] = [];
  min: number = 0;
  max: number = 100;
}
