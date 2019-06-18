import {Component} from '@angular/core';
import {ModalController} from "@ionic/angular";
import {Router} from "@angular/router";
import {DynamicQueryVo} from "../../../../../domain/vo/DynamicQueryVo";
import {DynamicCategory} from "../../../../../domain/enum/DynamicCategory";

@Component({
  selector: 'app-dynamic-search-modal',
  templateUrl: './dynamic-search-modal.page.html',
  styleUrls: ['./dynamic-search-modal.page.scss'],
})
export class DynamicSearchModalPage {
  queryVo = new DynamicQueryVo();

  DynamicCategory = DynamicCategory;

  constructor(private modalController: ModalController,
              private router: Router) {
  }


  advanceSearch() {
    this.modalController.dismiss();
    this.router.navigate(["tabs/info/dynamic"], {
      queryParams: {type: "advance", field: JSON.stringify(this.queryVo)}
    });
  }
}
