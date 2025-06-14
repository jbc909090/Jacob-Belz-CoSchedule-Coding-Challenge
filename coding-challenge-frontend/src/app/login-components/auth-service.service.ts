import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';


export interface OutgoingUserDTO {
  userId: number;
  username: string;
  role: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  url = "http://localhost:8080/auth"
  client: HttpClient;
  constructor(client: HttpClient) { 
    this.client = client;
  }
  login(username: string, password: string): Observable<Object> {
    return this.client.post<OutgoingUserDTO>(this.url + "/login", {username, password}, {withCredentials: true});
  }
  register(username: string, password: string): Observable<Object> {
    return this.client.post<OutgoingUserDTO>(this.url + "/register", { username, password}, {withCredentials: true});
  }
}
