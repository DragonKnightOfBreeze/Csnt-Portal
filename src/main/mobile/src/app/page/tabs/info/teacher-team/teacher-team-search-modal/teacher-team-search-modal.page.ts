import {Component} from '@angular/core';
import {TeacherTeamQueryVo} from "../../../../../domain/vo/TeacherTeamQueryVo";
import {ModalController} from "@ionic/angular";
import {Router} from "@angular/router";
import {ProfessionLevel} from 'src/app/domain/enum/ProfessionLevel';

@Component({
  selector: 'app-teacher-team-search-modal',
  templateUrl: './teacher-team-search-modal.page.html',
  styleUrls: ['./teacher-team-search-modal.page.scss'],
})
export class TeacherTeamSearchModalPage {
  queryVo = new TeacherTeamQueryVo();

  ProfessionLevel = ProfessionLevel;

  constructor(private modalController: ModalController,
              private router: Router) {
  }


  advanceSearch() {
    this.modalController.dismiss();
    this.router.navigate(["tabs/info/teacher-team"], {queryParams: {type: "advance", field: this.queryVo}});
  }
}
