import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';

const API_URL = 'http://localhost:8080/api/events';

@Injectable({
	providedIn: 'root',
})
export class EventService {
	// Subject para armazenar e emitir a lista de eventos de forma reativa
	private eventosSubject = new BehaviorSubject<any[]>([]);

	// Observable que permite a outros componentes se inscreverem nas atualizações dos eventos
	eventos$ = this.eventosSubject.asObservable();

	constructor(private http: HttpClient) { }

	getUserEmail(): string | null {
		return localStorage.getItem('userEmail');
	}

	 /**
     * Carrega os eventos do usuário e atualiza o BehaviorSubject.
     * Caso o email do usuário não esteja disponível, a função é encerrada.
     */
	carregarEventos(): void {
		const userEmail = this.getUserEmail();
		if (!userEmail) return;

		this.getEventsByUser(userEmail).subscribe((eventos) => {
			this.eventosSubject.next(eventos);
		});
	}

	getEventsByUser(userEmail: string): Observable<any> {
		return this.http.get(`${API_URL}/user/${userEmail}`);
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
