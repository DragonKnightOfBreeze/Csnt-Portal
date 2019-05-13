import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ReformColumnDetailComponent} from './reform-column-detail.component';

describe('ReformColumnDetailComponent', () => {
  let component: ReformColumnDetailComponent;
  let fixture: ComponentFixture<ReformColumnDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ReformColumnDetailComponent]
    })
        .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReformColumnDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
