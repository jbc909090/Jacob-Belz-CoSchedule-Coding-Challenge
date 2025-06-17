import { AfterContentInit, Component, OnInit } from '@angular/core';
import { GifDTO, RateCommentService } from '../rate-comment.service';
import { Router } from '@angular/router';
import { APIService } from '../../api.service';
import { Gif } from '../gif-viewer.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

export interface GifIndex {
  id: string;
  url:  string;
  rating: number;
  comment: string;
  index: number;
  comment_new: string;
  rating_new: number;
}

@Component({
  selector: 'app-history',
  standalone: true,
  imports: [CommonModule, FormsModule, CommonModule],
  templateUrl: './history.component.html',
  styleUrl: './history.component.css'
})
export class HistoryComponent implements OnInit {
  relevant: GifDTO[] = [];
  gifs: any;
  constructor(private service: APIService, private rcService: RateCommentService, private router: Router) {}
  ngOnInit(): void {
    this.rcService.getAllGif().subscribe((data)=> {this.relevant = data
    this.relevant.forEach((value, index) =>
      {this.service.single(value.url).subscribe((data) =>
        {this.gifs = data
        this.gifDisplay[index] = {
          id: this.gifs.data.id,
          url: this.gifs.data.images.fixed_width.url,
          rating: value.rating,
          comment: value.comment,
          index: index,
          comment_new: "",
          rating_new: 3
        }}
      )})});
  }
  editComm(id: string, comm: string, index: number) {
    this.rcService.putComment(id, comm).subscribe((data) => (this.gifDisplay[index].comment = data.comment));
  }
  editRate(id: string, rate: number, index: number) {
    this.rcService.putRating(id, rate).subscribe((data) => (this.gifDisplay[index].rating = data.rating));
  }
  delRate(id: string, index: number) {
    this.rcService.deleteRating(id).subscribe((data) => {this.gifDisplay[index].rating = 0
      this.relevant[index].rating = 0;
      this.gifDisplay[index].rating_new = 0;
    })
  }
  delComm(id: string, index: number) {
    this.rcService.deleteComment(id).subscribe((data) => {this.gifDisplay[index].comment = ""
      this.relevant[index].comment = "";
      this.gifDisplay[index].comment_new = "";
    })
  }
  gifDisplay: GifIndex[] = [];
  goToHome() {
    this.router.navigate(['/home']);
  }
}
