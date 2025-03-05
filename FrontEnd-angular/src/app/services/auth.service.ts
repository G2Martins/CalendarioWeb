import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/users';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  register(user: { nome: string; email: string; senha: string }): Observable<any> {
    return this.http.post(`${API_URL}`, user);
  }

  getUserByEmail(email: string): Observable<any> {
    return this.http.get(`${API_URL}/email/${email}`);
  }
}
