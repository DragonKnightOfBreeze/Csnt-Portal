import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {EnumConstsPipe} from "./pipe/enum-consts.pipe";
import {EnumTextPipe} from "./pipe/enum-text.pipe";
import {LimitTextPipe} from "./pipe/limit-text.pipe";
import {LocaleDatePipe} from "./pipe/locale-date.pipe";

@NgModule({
  declarations: [
    EnumConstsPipe,
    EnumTextPipe,
    LimitTextPipe,
    LocaleDatePipe
  ],
  imports: [
    CommonModule
  ],
  exports: [
    EnumConstsPipe,
    EnumTextPipe,
    LimitTextPipe,
    LocaleDatePipe
  ],
})
export class SharedModule {
}
