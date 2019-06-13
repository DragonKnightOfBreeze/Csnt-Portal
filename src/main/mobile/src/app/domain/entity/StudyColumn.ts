import {Profession} from "../../enum/Profession";
import {DifficultyLevel} from "../../enum/DifficultyLevel";

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
