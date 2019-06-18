import {Component} from '@angular/core';
import {DynamicService} from "../../../../../service/api/dynamic.service";
import {Dynamic} from "../../../../../domain/entity/Dynamic";
import {DynamicCategory} from "../../../../../domain/enum/DynamicCategory";
import {Router} from "@angular/router";
import {ModalController} from "@ionic/angular";

@Component({
  selector: 'app-dynamic-create-modal',
  templateUrl: './dynamic-create-modal.page.html',
  styleUrls: ['./dynamic-create-modal.page.scss'],
})
export class DynamicCreateModalPage {
  dynamic = new Dynamic();

  DynamicCategory = DynamicCategory;

  constructor(private service: DynamicService,
              private modalController: ModalController,
              private router: Router) {
  }


  create() {
    this.modalController.dismiss();
    this.service.create(this.dynamic).subscribe(() => {
      this.router.navigate(["tabs/info/dynamic"])
    });
  }
}
