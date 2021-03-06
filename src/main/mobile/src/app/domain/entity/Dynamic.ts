import {DynamicCategory} from "../enum/DynamicCategory";
import {User} from "./User";

export class Dynamic {
  id: number;
  subject: string;
  category: DynamicCategory;
  content: string;
  sponsorUser: User | null;
  sponsorTime: string;
}
