import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TeacherInfoDetailComponent} from './teacher-info-detail.component';

describe('TeacherInfoDetailComponent', () => {
  let component: TeacherInfoDetailComponent;
  let fixture: ComponentFixture<TeacherInfoDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TeacherInfoDetailComponent]
    })
        .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeacherInfoDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
