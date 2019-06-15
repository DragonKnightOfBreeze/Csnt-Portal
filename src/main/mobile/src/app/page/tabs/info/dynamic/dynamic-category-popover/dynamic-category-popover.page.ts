import {Component, OnInit} from '@angular/core';
import {PopoverController} from "@ionic/angular";
import {Router} from "@angular/router";
import {DynamicCategory} from "../../../../../domain/enum/DynamicCategory";
import {DynamicQueryVo} from "../../../../../domain/vo/DynamicQueryVo";

//TODO
@Component({
  selector: 'app-dynamic-category-popover',
  templateUrl: './dynamic-category-popover.page.html',
  styleUrls: ['./dynamic-category-popover.page.scss'],
})
export class DynamicCategoryPopoverPage implements OnInit {
  queryVo = new DynamicQueryVo();

  DynamicCategory = DynamicCategory;

  constructor(private popoverController: PopoverController,
              private router: Router) {
  }


  ngOnInit() {
  }

  searchByCategory(category: DynamicCategory) {
    this.popoverController.dismiss();
    this.queryVo.categorySet = [category];
    this.router.navigate(["/tabs/info/dynamic"], {queryParams: {type: "category", field: this.queryVo}})
  }
}
