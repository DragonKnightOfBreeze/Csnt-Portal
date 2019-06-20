/**
 * 参数的服务类。
 */
import {Injectable} from "@angular/core";
import {ObjectError} from "../../domain/interface/ObjectError";
import {BehaviorSubject} from "rxjs";

@Injectable({providedIn: "root"})
export class ValidationService {
  public validationErrors$ = new BehaviorSubject<ObjectError[]>([]);

  constructor() {
  }


  setValidationErrors(validationErrors: ObjectError[]) {
    this.validationErrors$.next(validationErrors);
  }
}
