import {Gender} from "../../enums/Gender";
import {Role} from "../../enums/Role";
import {Profession} from "../../enums/Profession";
import {Dynamic} from "./Dynamic";

export class User {
  id: number;
  username: string;
  password: string;
  phoneNum: string;
  email: string;
  nickname: string;
  gender: Gender;
  role: Role;
  profession: Profession;
  registerTime: string;
  dynamicList: Dynamic[];
}
