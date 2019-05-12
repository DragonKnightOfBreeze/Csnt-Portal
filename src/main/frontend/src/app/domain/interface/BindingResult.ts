/**
 * 绑定结果的接口。<br>
 * 另见：{@code org.springframework.validation.BindingResult}
 */
export interface BindingResult {
  target: object;
  model: Map<string, object>;
  propertyEditorRegistry: object;
  suppressedFields: string[];
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

/**
 * 字段错误的接口。<br>
 * 另见：{@code org.springframework.validation.FieldError}
 */
export interface FieldError extends ObjectError {
  field: string;
  rejectedValue: object | null;
  bindingFailure: boolean;
}

/**
 * 对象错误的接口。<br>
 * 另见：{@code org.springframework.validation.ObjectError}
 */
export interface ObjectError {
  objectName: string;
  code: string | null;
  codes: string[] | null;
  arguments: object[] | null;
  defaultMessage: string | null;
}
