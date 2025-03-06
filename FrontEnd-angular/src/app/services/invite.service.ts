import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { EventService } from './event.service';

@Injectable({
	providedIn: 'root',
})
export class InviteService {
	private apiUrl = 'http://localhost:8080/api/events';

	// Injeta HttpClient para realizar requisições HTTP e EventService para recarregar eventos quando necessário
	constructor(private http: HttpClient, private eventService: EventService) { }

	getUserEmail(): string | null {
		return localStorage.getItem('userEmail');
	}

	listarConvites(): Observable<any> {
		const email = this.getUserEmail();
		if (!email) throw new Error('Usuário não autenticado.');
		return this.http.get(`${this.apiUrl}/invites/${email}`);
	}

	/**
     * Responde a um convite de evento, atualizando seu status (por exemplo, 'ACEITO' ou 'RECUSADO').
     * Caso o status seja 'ACEITO', recarrega os eventos do usuário para refletir a alteração.
     * @param eventId O ID do evento.
     * @param status O status da resposta ao convite.
     * @returns Um Observable que emite a resposta da API.
     * @throws Erro se o usuário não estiver autenticado.
     */
	responderConvite(eventId: string, status: string): Observable<any> {
		const email = this.getUserEmail();
		if (!email) throw new Error('Usuário não autenticado.');

		return this.http.put(`${this.apiUrl}/${eventId}/invite/${email}/${status}`, {}).pipe(
			tap(() => {
				if (status === 'ACEITO') {
					this.eventService.carregarEventos();
				}
			})
		);
	}

	/**
     * Envia um convite para um usuário específico para um determinado evento.
     * @param eventId O ID do evento.
     * @param email O email do usuário que receberá o convite.
     * @returns Um Observable que emite a resposta da API.
     */
	enviarConvite(eventId: string, email: string): Observable<any> {
		return this.http.post(`${this.apiUrl}/${eventId}/invite`, [email]);
	}
}
