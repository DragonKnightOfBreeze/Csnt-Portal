import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {DynamicDetailComponent} from './dynamic-detail.component';

describe('DynamicDetailComponent', () => {
  let component: DynamicDetailComponent;
  let fixture: ComponentFixture<DynamicDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [DynamicDetailComponent]
    })
        .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DynamicDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
