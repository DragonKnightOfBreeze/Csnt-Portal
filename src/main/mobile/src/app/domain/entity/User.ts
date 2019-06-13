import {Gender} from "../../enum/Gender";
import {Role} from "../../enum/Role";
import {Profession} from "../../enum/Profession";
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
  /**NOTE 需要手动调用特定服务获取数据。*/
  dynamicList: Dynamic[];
}
