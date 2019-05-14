import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StudyColumnComponent} from './study-column.component';

describe('StudyColumnComponent', () => {
  let component: StudyColumnComponent;
  let fixture: ComponentFixture<StudyColumnComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StudyColumnComponent]
    })
        .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudyColumnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
