/**
 * 排序器的接口。
 * 另见：{@code org.springframework.data.domain.Sort}
 */
export interface Sort {
  empty: boolean;
  sorted: boolean;
  unsorted: boolean;
  class: any;
}
