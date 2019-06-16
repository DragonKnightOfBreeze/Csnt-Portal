/**
 * 查询参数的封装类。
 */
export class QueryParams<T> {
  /**查询类型。*/
  type: string = "all";
  /**查询域。*/
  field: T;
  /**当前页数。*/
  page: number = 1;
  /**每页条目数。*/
  size: number = 10;
}
