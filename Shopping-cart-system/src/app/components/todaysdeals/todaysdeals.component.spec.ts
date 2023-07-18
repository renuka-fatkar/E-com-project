import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodaysdealsComponent } from './todaysdeals.component';

describe('TodaysdealsComponent', () => {
  let component: TodaysdealsComponent;
  let fixture: ComponentFixture<TodaysdealsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TodaysdealsComponent]
    });
    fixture = TestBed.createComponent(TodaysdealsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
