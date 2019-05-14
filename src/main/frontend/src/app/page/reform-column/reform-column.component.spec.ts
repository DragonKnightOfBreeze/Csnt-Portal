import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ReformColumnComponent} from './reform-column.component';

describe('ReformColumnComponent', () => {
  let component: ReformColumnComponent;
  let fixture: ComponentFixture<ReformColumnComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ReformColumnComponent]
    })
        .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReformColumnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
