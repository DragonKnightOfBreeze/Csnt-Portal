import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StudyColumnDetailComponent} from './study-column-detail.component';

describe('StudyColumnDetailComponent', () => {
  let component: StudyColumnDetailComponent;
  let fixture: ComponentFixture<StudyColumnDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StudyColumnDetailComponent]
    })
        .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudyColumnDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
