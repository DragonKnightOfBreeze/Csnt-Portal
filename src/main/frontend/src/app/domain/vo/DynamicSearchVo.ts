import {DynamicCategory} from "../../enums/DynamicCategory";

export interface DynamicSearchVo {
  subject:string;
  categorySet:DynamicCategory[];
  sponsorUsername:string;
}
