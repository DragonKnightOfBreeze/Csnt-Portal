/**
 * 绑定结果的接口。<br>
 * 另见：{@code org.springframework.validation.BindingResult}
 */
export interface BindingResult extends Errors {
  target: object;
  model: Map<string, object>;
  propertyEditorRegistry: object;
  suppressedFields: string[];
}

interface Errors {
  objectName: string;
  nestedPath: string;
  errorCount: number;
  allErrors: ObjectError[];
  globalErrorCount: number;
  globalErrors: ObjectError[];
  globalError: ObjectError | null;
  fieldErrorCount: number;
  fieldErrors: FieldError[];
  fieldError: FieldError | null;
}


export interface FieldError extends ObjectError {
  field: string;
  rejectedValue: object | null;
  bindingFailure: boolean;
}

export interface ObjectError extends DefaultMessageSourceResolvable {
  objectName: string;
}

interface DefaultMessageSourceResolvable extends MessageSourceResolvable {
  code: string | null;
}

interface MessageSourceResolvable {
  codes: string[] | null;
  arguments: object[] | null;
  defaultMessage: string | null;
}
