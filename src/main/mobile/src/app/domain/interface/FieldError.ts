import {ObjectError} from "./ObjectError";

/**
 * 字段错误的接口。
 * 另见：{@code org.springframework.validation.FieldError}
 */
export interface FieldError extends ObjectError {
  field: string;
  rejectedValue: any | null;
  bindingFailure: boolean;
  class: any;
}

