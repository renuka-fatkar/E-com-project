import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-todaysdeals',
  templateUrl: './todaysdeals.component.html',
  styleUrls: ['./todaysdeals.component.css']
})
export class TodaysdealsComponent implements OnInit{


  images: string[] = ["assets/jewel/j9.jpg", "assets/jewel/j2.jpg","assets/jewel/j3.jpg","assets/jewel/j4.jpg","assets/jewel/j5.jpg","assets/jewel/j8.jpg"];
  images2: string[] = ["assets/decores/d1.jpeg","assets/decores/d2.jpeg","assets/decores/d3.jpeg","assets/decores/d4.jpeg","assets/decores/d5.jpeg"];
  images3: string[] = ["assets/electronics/e9.jpeg", "assets/electronics/e2.jpeg","assets/electronics/e3.jpeg","assets/electronics/e4.jpeg","assets/electronics/e5.jpg","assets/electronics/e8.jpeg"];

  currentIndex = 0;
  currentIndex2 = 0;
  currentIndex3 = 0;
  slideshowImageSrc = '';
  slideshowImageSrc2 = '';
  slideshowImageSrc3 = '';

  ngOnInit(): void {
    this.startSlideshow();
  }

  startSlideshow(): void {
    setInterval(() => {
      this.slideshowImageSrc = this.images[this.currentIndex];
      this.slideshowImageSrc2 = this.images2[this.currentIndex2];
      this.slideshowImageSrc3 = this.images3[this.currentIndex3];
      this.currentIndex = (this.currentIndex + 1) % this.images.length;
      this.currentIndex2 = (this.currentIndex2 + 1) % this.images2.length;
      this.currentIndex3 = (this.currentIndex3 + 1) % this.images3.length;
    }, 3000); // Change image every 4 seconds
  }

}
