import {Component, OnDestroy, OnInit} from "@angular/core";
import {DevelopmentColumnService} from "../../../../../service/api/development-column.service";
import {UserService} from "../../../../../service/api/user.service";
import {ActivatedRoute} from "@angular/router";
import {DevelopmentColumn} from "../../../../../domain/entity/DevelopmentColumn";
import {Page} from "../../../../../domain/interface/Page";
import {QueryParams} from "../../../../../domain/vo/QueryParams";
import {BehaviorSubject, Observable} from "rxjs";
import {switchMap, tap} from "rxjs/operators";

//尝试将这个组件改成异步的

@Component({
  selector: "app-development-column-list",
  templateUrl: "./development-column-list.page.html",
  styleUrls: ["./development-column-list.page.scss"],
})
export class DevelopmentColumnListPage implements OnInit, OnDestroy {
  queryParams$ = new BehaviorSubject(new QueryParams<string>());

  currentPage$: Observable<Page<DevelopmentColumn>>;

  constructor(private service: DevelopmentColumnService,
              public userService: UserService,
              private route: ActivatedRoute) {
  }


  ngOnInit() {
    this.getQueryParams();
    this.show();
  }

  private getQueryParams() {
    this.route.queryParamMap.pipe(
      //value=>void
      tap(queryParamMap => {
        this.queryParams$.next({
          type: queryParamMap.get("type") || "all",
          field: queryParamMap.get("field") || "",
          page: +queryParamMap.get("page") || 1,
          size: +queryParamMap.get("size") || 10
        });
      })
    );
  }

  private show() {
    this.currentPage$ = this.queryParams$.pipe(
      //如果新的值相同，则忽略这次请求，不能直接使用，因为这是一个对象
      // distinctUntilChanged(),
      // distinctUntilChanged((a, b) => a.field == b.field && a.page == b.page && a.size == b.size),
      //value=>otherValue
      switchMap(queryParams => {
        const {type, field, page, size} = queryParams;
        if (type == "title") {
          return this.service.searchByTitle(field, page, size);
        } else {
          return this.service.list(page, size);
        }
      })
    )
  }

  delete(id: number) {
    this.currentPage$.subscribe(currentPage => {
      currentPage.content = currentPage.content.filter(e => e.id != id)
    });
    this.service.delete(id).subscribe();
  }

  search(event) {
    //搜索结果重新从第一页开始
    const queryParams = new QueryParams<string>();
    queryParams.type = "title";
    queryParams.field = event.target.value;
    this.queryParams$.next(queryParams);
  }

  goPreviousPage() {
    let first = false;
    this.currentPage$.subscribe(currentPage => first = currentPage.first);
    //以下代码必须写到subscribe()外面，否则某些情况下会无限循环
    if (!first) {
      let queryParams = this.queryParams$.value;
      queryParams.page -= 1;
      this.queryParams$.next(queryParams)
    }
  }

  goNextPage() {
    let last = false;
    this.currentPage$.subscribe(currentPage => last = currentPage.last);
    //以下代码必须写到subscribe()外面，否则某些情况下会无限循环
    if (!last) {
      let queryParams = this.queryParams$.value;
      queryParams.page += 1;
      this.queryParams$.next(queryParams)
    }
  }

  ngOnDestroy() {
    this.queryParams$.unsubscribe();
  }
}
