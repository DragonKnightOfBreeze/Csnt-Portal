import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TeacherTeamDetailComponent} from './teacher-team-detail.component';

describe('TeacherTeamDetailComponent', () => {
  let component: TeacherTeamDetailComponent;
  let fixture: ComponentFixture<TeacherTeamDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TeacherTeamDetailComponent]
    })
        .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherTeamDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
