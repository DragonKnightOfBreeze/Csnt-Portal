import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {DevelopmentColumnDetailComponent} from './development-column-detail.component';

describe('DevelopmentColumnDetailComponent', () => {
  let component: DevelopmentColumnDetailComponent;
  let fixture: ComponentFixture<DevelopmentColumnDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [DevelopmentColumnDetailComponent]
    })
        .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DevelopmentColumnDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
