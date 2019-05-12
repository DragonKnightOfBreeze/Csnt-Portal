import {Role} from "../../enums/Role";

export class JwtResponseVo {
  token: string = "";
  type: string = "";
  username: string = "";
  role: string = Role.Visitor;
}
