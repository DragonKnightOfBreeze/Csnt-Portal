import {AbstractControl, AsyncValidator, NG_VALIDATORS, ValidationErrors} from "@angular/forms";
import {Directive} from "@angular/core";
import {Observable} from "rxjs";
import {UserService} from "../service/api/user.service";
import {map} from "rxjs/operators";

/**
 * 独特用户的验证器。
 */
@Directive({
  selector: "[appUniqueUser]",
  providers: [{provide: NG_VALIDATORS, useExisting: UniqueUserValidator}]
})
export class UniqueUserValidator implements AsyncValidator {
  constructor(private userService: UserService) {
  }


  validate(control: AbstractControl): Observable<ValidationErrors | null> {
    return this.userService.exists(control.value).pipe(
      map(result => result ? {uniqueUser: true} : null)
    );
  }
}
