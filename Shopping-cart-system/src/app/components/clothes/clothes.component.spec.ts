import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClothesComponent } from './clothes.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { CartService } from 'src/app/services/cart.service';

describe('ClothesComponent', () => {
  let component: ClothesComponent;
  let fixture: ComponentFixture<ClothesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ClothesComponent],
      imports: [HttpClientTestingModule],
      providers: [CartService]
    });
    fixture = TestBed.createComponent(ClothesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
