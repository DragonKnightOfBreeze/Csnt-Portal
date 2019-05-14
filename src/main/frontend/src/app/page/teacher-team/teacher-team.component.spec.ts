import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TeacherTeamComponent} from './teacher-team.component';

describe('TeacherTeamComponent', () => {
  let component: TeacherTeamComponent;
  let fixture: ComponentFixture<TeacherTeamComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TeacherTeamComponent]
    })
        .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherTeamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
