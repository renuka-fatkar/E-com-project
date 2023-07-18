import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DecoresComponent } from './decores.component';

describe('DecoresComponent', () => {
  let component: DecoresComponent;
  let fixture: ComponentFixture<DecoresComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DecoresComponent]
    });
    fixture = TestBed.createComponent(DecoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
