/**
 * 分页的接口。
 * 另见：{@code org.springframework.data.domain.Page}
 */

export interface Page<T> {
  totalPages: number;
  totalElements: number;
  empty: boolean;
  number: number;
  size: number;
  numberOfElements: number;
  content: T[];
  sort: Sort;
  first: boolean;
  last: boolean;
  pageable: Pageable;
}

/**
 * 排序器的接口。
 * 另见：{@code org.springframework.data.domain.Sort}
 */
export interface Sort {
  empty: boolean;
  sorted: boolean;
  unsorted: boolean;
}

/**
 * 分页器的接口。
 * 另见：{@code org.springframework.data.domain.Pageable}
 */
export interface Pageable {
  paged: boolean;
  unpaged: boolean;
  pageNumber: number;
  pageSize: number;
  offset: number;
  sort: Sort;
}
