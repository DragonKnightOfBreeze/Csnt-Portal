/**
 * 查询参数的封装对象。
 */
export class SearchParams<T> {
  type: string;

  field:T;

  page: number;

  size: number;
}
