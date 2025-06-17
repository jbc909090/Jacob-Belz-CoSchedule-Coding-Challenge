import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';


export interface OutgoingUserDTO {
  userId: number;
  username: string;
  role: string;
}

export interface LoginDTO {
  username: string;
  password: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  url = "http://localhost:8080/auth"
  body: LoginDTO = {username: "", password: ""};
  constructor(private client: HttpClient) {}
  login(username: string, password: string): Observable<OutgoingUserDTO> {
    this.body = {username: username, password: password};
    return this.client.post<OutgoingUserDTO>(this.url + "/login", this.body, {withCredentials: true});
  }
  register(username: string, password: string): Observable<OutgoingUserDTO> {
    this.body = {username: username, password: password};
    return this.client.post<OutgoingUserDTO>(this.url + "/register", this.body, {withCredentials: true});
  }
}
