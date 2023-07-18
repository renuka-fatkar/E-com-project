import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Rating } from 'src/app/data-types';

@Component({
  selector: 'app-ratings',
  templateUrl: './ratings.component.html',
  styleUrls: ['./ratings.component.css']
})
export class RatingsComponent implements OnInit {
  
  ratingForm!: FormGroup;
  ratings: Rating[] = [];
  receivedData: number = 0;

  constructor(private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private http: HttpClient) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.receivedData = params['data'];
    });
    this.ratingForm = this.formBuilder.group({
      rating: ['', Validators.required],
      comment: ['', Validators.required]
    });

    this.getRatings();
  }

  getRatings(): void {
    this.http.get<Rating[]>('http://localhost:8085/rating/viewRatingsByProdId/'+this.receivedData).subscribe(
      (response: Rating[]) => {
        this.ratings = response;
      },
      (error) => {
        console.error('Failed to retrieve ratings:', error);
      }
    );
  }

  onSubmit(): void {
    if (this.ratingForm.invalid) {
      return;
    }

    const ratingData = this.ratingForm.value;
    const newRating: Rating = {
      rateId:0,
      userId:0,
      prodId:this.receivedData,
      rating: ratingData.rating,
      comment: ratingData.comment
    };

    this.http.post('http://localhost:8085/rating/rateTheProduct', newRating).subscribe(
      () => {
        this.ratingForm.reset();
        this.getRatings();
      },
      (error) => {
        console.error('Failed to save rating:', error);
      }
    );
  }
}
