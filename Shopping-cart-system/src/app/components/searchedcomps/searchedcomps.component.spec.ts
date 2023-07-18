import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchedcompsComponent } from './searchedcomps.component';

describe('SearchedcompsComponent', () => {
  let component: SearchedcompsComponent;
  let fixture: ComponentFixture<SearchedcompsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SearchedcompsComponent]
    });
    fixture = TestBed.createComponent(SearchedcompsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
