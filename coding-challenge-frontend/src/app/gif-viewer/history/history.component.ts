import { AfterContentInit, Component, OnInit } from '@angular/core';
import { RateCommentService } from '../rate-comment.service';
import { Router } from '@angular/router';
import { APIService } from '../../api.service';
import { Gif } from '../gif-viewer.component';

@Component({
  selector: 'app-history',
  standalone: true,
  imports: [],
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
          comment: ""
        }}
      )});
  }
  gifDisplay: Gif[] = [];

}
