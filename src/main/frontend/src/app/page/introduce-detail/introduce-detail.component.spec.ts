import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {IntroduceDetailComponent} from './introduce-detail.component';

describe('IntroduceDetailComponent', () => {
  let component: IntroduceDetailComponent;
  let fixture: ComponentFixture<IntroduceDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [IntroduceDetailComponent]
    })
        .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IntroduceDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
