/**
 * 查询参数的封装接口。
 */
export interface QueryParams<T> {
  /**查询类型。*/
  type: string;
  /**查询域。*/
  field: T;
  /**当前页数。*/
  page: number;
  /**每页条目数。*/
  size: number;
}
