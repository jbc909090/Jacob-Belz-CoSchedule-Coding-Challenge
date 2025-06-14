import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class APIService {
 url = "https://api.giphy.com/v1/gifs/"
 key = "api_key=eHY8dRTVLBXeH71Q4d1jmTqdUh2SEz44"
  client: HttpClient;
  constructor(client: HttpClient) { 
    this.client = client;
  }
  trending(): Observable<Object> {
    return this.client.get(this.url + "trending?" + this.key + "&limit=10&offset=0&rating=g&bundle=messaging_non_clips");
  }
  searching(searchterm: string): Observable<Object> {
    return this.client.get(this.url + "search?" + this.key +"&q=" + searchterm + "&limit=10&offset=0&rating=g&lang=en&bundle=messaging_non_clips");
  }
  single(idterm: string): Observable<Object> {
    return this.client.get(this.url + idterm +"?" + this.key + "&rating=g");
  }
}
