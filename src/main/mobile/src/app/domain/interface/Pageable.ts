/**
 * 分页器的接口。
 * 另见：{@code org.springframework.data.domain.Pageable}
 */
import {Sort} from "./Sort";

export interface Pageable {
  paged: boolean;
  unpaged: boolean;
  pageNumber: number;
  pageSize: number;
  offset: number;
  sort: Sort;
  class: any;
}
