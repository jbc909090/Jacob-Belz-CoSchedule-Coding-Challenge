import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { APIService } from '../api.service';
import { RateCommentService } from './rate-comment.service';
import { HistoryComponent } from './history/history.component';

export interface Gif {
  id: string;
  url:  string;
  rating: number;
  comment: string;
}

@Component({
  selector: 'app-gif-viewer',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './gif-viewer.component.html',
  styleUrl: './gif-viewer.component.css'
})
export class GifViewerComponent {
  search: string = "";
  gifs: any;
  constructor(private service: APIService, private rcService: RateCommentService, private router: Router) {}
  searching() {
    this.service.searching(this.search).subscribe((data) => {this.gifs = data
      for(let i = 0; i < 10; i++) {
        this.gifDisplay[i] = {
          id: (this.gifs.data[i].id || ""),
          url: (this.gifs.data[i].images.fixed_width.url || ""),
          rating: 3,
          comment: ""
        }
      }
    });
  }
  trending() {
    this.service.trending().subscribe((data) => {this.gifs = data
      for(let i = 0; i < 10; i++) {
        this.gifDisplay[i] = {
          id: (this.gifs.data[i].id || ""),
          url: (this.gifs.data[i].images.fixed_width.url || ""),
          rating: 3,
          comment: ""
        }
      }
    });
  }
  comment(id: string, comment: string) {
    this.rcService.postComment(id, comment).subscribe((data)=> console.log(data));
    this.rcService.postGif(id);
  }
  rate(id: string, rate: number) {
    console.log(id)
    console.log(rate)
    this.rcService.postRating(id, rate).subscribe((data)=> (console.log(data),
    this.rcService.postGif(id)));
  }
  goToHistory() {
    this.router.navigate(['/history']);
  }
  gifDisplay: Gif[] = [
    {id:"", url:"", rating:3, comment:""},
    {id:"", url:"", rating:3, comment:""},
    {id:"", url:"", rating:3, comment:""},
    {id:"", url:"", rating:3, comment:""},
    {id:"", url:"", rating:3, comment:""},
    {id:"", url:"", rating:3, comment:""},
    {id:"", url:"", rating:3, comment:""},
    {id:"", url:"", rating:3, comment:""},
    {id:"", url:"", rating:3, comment:""},
    {id:"", url:"", rating:3, comment:""},
  ];
}
