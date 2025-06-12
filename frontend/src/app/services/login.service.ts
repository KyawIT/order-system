import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Person } from '../models/person.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl = 'http://localhost:8080/';
  constructor(private http: HttpClient) { }

  login(username: string, password: string) :Observable<Person>{
    const body = { username, password };
    return this.http.post<Person>(this.apiUrl+'login', body);
  }
}
