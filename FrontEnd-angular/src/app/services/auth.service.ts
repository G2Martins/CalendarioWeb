import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/users';

@Injectable({
	providedIn: 'root'
})
export class AuthService {
	constructor(private http: HttpClient) { }

	/**
     * Registra um novo usuário na API.
     * @param user Objeto contendo os dados do usuário: nome, email e senha.
     * @returns Um Observable que emite a resposta da API.
     */
	register(user: { nome: string; email: string; senha: string }): Observable<any> {
		return this.http.post(`${API_URL}`, user);
	}

	/**
     * Busca um usuário pelo email.
     * @param email O email do usuário a ser buscado.
     * @returns Um Observable que emite os dados do usuário retornados pela API.
     */
	getUserByEmail(email: string): Observable<any> {
		return this.http.get(`${API_URL}/email/${email}`);
	}
}
