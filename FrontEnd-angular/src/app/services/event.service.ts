import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/events';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  constructor(private http: HttpClient) {}

  getEventsByUser(userId: string): Observable<any> {
    return this.http.get(`${API_URL}/user/${userId}`);
  }

  createEvent(event: any): Observable<any> {
    return this.http.post(`${API_URL}`, event);
  }

  updateEvent(eventId: string, event: any): Observable<any> {
    return this.http.put(`${API_URL}/${eventId}`, event);
  }

  deleteEvent(eventId: string): Observable<any> {
    return this.http.delete(`${API_URL}/${eventId}`);
  }
}
