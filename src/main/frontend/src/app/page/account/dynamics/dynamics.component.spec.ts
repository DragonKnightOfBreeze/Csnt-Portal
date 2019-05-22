import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {DynamicsComponent} from './dynamics.component';

describe('DynamicsComponent', () => {
  let component: DynamicsComponent;
  let fixture: ComponentFixture<DynamicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [DynamicsComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DynamicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
