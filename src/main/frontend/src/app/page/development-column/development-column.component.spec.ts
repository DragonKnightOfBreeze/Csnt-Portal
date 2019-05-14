import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {DevelopmentColumnComponent} from './development-column.component';

describe('DevelopmentColumnComponent', () => {
  let component: DevelopmentColumnComponent;
  let fixture: ComponentFixture<DevelopmentColumnComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [DevelopmentColumnComponent]
    })
        .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DevelopmentColumnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
