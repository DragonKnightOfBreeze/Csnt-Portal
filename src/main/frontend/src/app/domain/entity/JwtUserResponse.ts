import {Role} from "../../enums/Role";

export class JwtUserResponse {
  username: string = "";
  role: string = Role.Visitor;
  token: string = "";
  type: string = "Bearer";
}
