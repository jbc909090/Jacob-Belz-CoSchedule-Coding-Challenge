import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

export interface Comment {
  commentId: number;
  userId: number;
  gif: string;
  comment: string;
}

export interface Rating {
  ratingId: number;
  userId: number;
  gif: string;
  rating: number;
}
export interface GifDTO {
  comment: string;
  url: string;
  rating: number;
}

@Injectable({
  providedIn: 'root'
})
export class RateCommentService {
  url = "http://localhost:8080/"
  url_rate = "/rating"
  url_comm = "/comment"
  client: HttpClient;
  constructor(client: HttpClient) { 
    this.client = client;
  }
  postComment(gifId: string, comment: string): Observable<Comment> {
    return this.client.post<Comment>(this.url + gifId + this.url_comm, comment, {withCredentials: true});
  }
  getComment(gifId: string): Observable<Comment> {
    return this.client.get<Comment>(this.url + gifId + this.url_comm, {withCredentials: true});
  }
  getAllComment(gifId: string): Observable<Array<Comment>> {
    return this.client.get<Array<Comment>>(this.url + gifId + this.url_comm + "/all", {withCredentials: true});
  }
  putComment(gifId: string, comment: string): Observable<Comment> {
    return this.client.put<Comment>(this.url + gifId + this.url_comm, comment, {withCredentials: true});
  }
  deleteComment(gifId: string): Observable<string> {
    return this.client.delete<string>(this.url + gifId + this.url_comm, {withCredentials: true});
  }

  postRating(gifId: string, rating: number): Observable<Rating> {
    return this.client.post<Rating>(this.url + gifId + this.url_rate, rating, {headers: new HttpHeaders({'Content-Type': 'application/json'}), withCredentials: true});
  }
  getRating(gifId: string): Observable<Rating> {
    return this.client.get<Rating>(this.url + gifId + this.url_rate, {withCredentials: true});
  }
  getAllRating(gifId: string): Observable<Array<Rating>> {
    return this.client.get<Array<Rating>>(this.url + gifId + this.url_rate + "/all", {withCredentials: true});
  }
  putRating(gifId: string, rating: number): Observable<Rating> {
    return this.client.put<Rating>(this.url + gifId + this.url_rate, rating, {headers: new HttpHeaders({'Content-Type': 'application/json'}), withCredentials: true});
  }
  deleteRating(gifId: string): Observable<string> {
    return this.client.delete<string>(this.url + gifId + this.url_rate, {withCredentials: true});
  }
  //gif stuff for history
  url_gif = "api"
  postGif(gifId: string): Observable<string> {
    return this.client.post<string>(this.url + this.url_gif + "/save", gifId, {withCredentials: true});
  }
  getAllGif(): Observable<Array<GifDTO>> {
    return this.client.get<Array<GifDTO>>(this.url + this.url_gif, {withCredentials: true});
  }
}
