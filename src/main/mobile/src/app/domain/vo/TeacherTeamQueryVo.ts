import {ProfessionLevel} from "../enum/ProfessionLevel";

export class TeacherTeamQueryVo {
  name: string = "";
  levelSet: ProfessionLevel[] = [];
  min: number = 0;
  max: number = 100;
}
