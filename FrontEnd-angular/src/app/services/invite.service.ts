import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap} from 'rxjs';
import { EventService } from './event.service';

@Injectable({
	providedIn: 'root',
})
export class InviteService {
	private apiUrl = 'http://localhost:8080/api/events';

	constructor(private http: HttpClient, private eventService: EventService) { }

	getUserEmail(): string | null {
		return localStorage.getItem('userEmail');
	}

	listarConvites(): Observable<any> {
		const email = this.getUserEmail();
		if (!email) throw new Error('Usuário não autenticado.');
		return this.http.get(`${this.apiUrl}/invites/${email}`);
	}

	responderConvite(eventId: string, status: string): Observable<any> {
		const email = this.getUserEmail();
		if (!email) throw new Error('Usuário não autenticado.');
		
		return this.http.put(`${this.apiUrl}/${eventId}/invite/${email}/${status}`, {}).pipe(
		  tap(() => {
			if (status === 'ACEITO') {
			  this.eventService.carregarEventos(); // Atualiza o calendário do usuário convidado
			}
		  })
		);
	  }

	enviarConvite(eventId: string, email: string): Observable<any> {
		return this.http.post(`${this.apiUrl}/${eventId}/invite`, [email]);
	}  

	
}
