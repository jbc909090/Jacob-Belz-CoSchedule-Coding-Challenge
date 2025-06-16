import { AfterContentInit, Component, OnInit } from '@angular/core';
import { RateCommentService } from '../rate-comment.service';
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
}

@Component({
  selector: 'app-history',
  standalone: true,
  imports: [CommonModule, FormsModule, CommonModule],
  templateUrl: './history.component.html',
  styleUrl: './history.component.css'
})
export class HistoryComponent implements OnInit, AfterContentInit {
  relevant: string[] = [];
  gifs: any;
  constructor(private service: APIService, private rcService: RateCommentService, private router: Router) {}
  ngOnInit(): void {
    this.rcService.getAllGif().subscribe((data)=> (this.relevant = data));
  }
  ngAfterContentInit(): void {
    this.relevant.forEach((value, index) =>
      {this.service.single(value).subscribe((data) =>
        {this.gifs = data
        this.gifDisplay[index] = {
          id: this.gifs.data[index].id,
          url: this.gifs.data[index].images.fixed_width.url,
          rating: 3,
          comment: "",
          index: index
        }}
      )});
  }
  editComm(id: string, comm: string, index: number) {
    this.rcService.putComment(id, comm).subscribe((data) => (this.gifDisplay[index].comment = data.comment));
  }
  editRate(id: string, rate: number, index: number) {
    this.rcService.putRating(id, rate).subscribe((data) => (this.gifDisplay[index].rating = data.rating));
  }
  delRate(id: string, index: number) {
    this.rcService.deleteRating(id).subscribe((data) => (this.gifDisplay[index].rating = 0))
  }
  delComm(id: string, index: number) {
    this.rcService.deleteComment(id).subscribe((data) => (this.gifDisplay[index].comment = ""))
  }
  gifDisplay: GifIndex[] = [];
  goToHome() {
    this.router.navigate(['/home']);
  }
}
