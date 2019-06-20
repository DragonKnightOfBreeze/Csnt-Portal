import {Pageable} from "./Pageable";
import {Sort} from "./Sort";

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
  class: any;
}
