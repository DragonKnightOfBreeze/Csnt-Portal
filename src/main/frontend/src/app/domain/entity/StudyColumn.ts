import {Profession} from "../../enums/Profession";
import {DifficultyLevel} from "../../enums/DifficultyLevel";

export class StudyColumn {
  id: number;
  title: string;
  content: string;
  author: string;
  profession: Profession;
  difficultyLevel: DifficultyLevel;
  publishTime: string;
  updateTime: string;
}
