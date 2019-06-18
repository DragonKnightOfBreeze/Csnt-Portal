import {Component} from '@angular/core';
import {PopoverController} from "@ionic/angular";
import {Router} from "@angular/router";
import {ProfessionLevel} from "../../../../../domain/enum/ProfessionLevel";
import {TeacherTeamQueryVo} from "../../../../../domain/vo/TeacherTeamQueryVo";

@Component({
  selector: 'app-teacher-team-level-popover',
  templateUrl: './teacher-team-level-popover.page.html',
  styleUrls: ['./teacher-team-level-popover.page.scss'],
})
export class TeacherTeamLevelPopoverPage {
  queryVo = new TeacherTeamQueryVo();

  ProfessionLevel = ProfessionLevel;

  constructor(private popoverController: PopoverController,
              private router: Router) {
  }


  searchByProfessionLevel(level: ProfessionLevel) {
    this.popoverController.dismiss();
    this.queryVo.levelSet = [level];
    this.router.navigate(["tabs/info/dynamic"], {
      queryParams: {type: "professionLevel", field: JSON.stringify(this.queryVo)}
    })
  }
}
