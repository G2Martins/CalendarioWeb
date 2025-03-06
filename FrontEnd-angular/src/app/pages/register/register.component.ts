import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.css']
})
export class RegisterComponent {
	nome = '';
	email = '';
	senha = '';
	errorMessage = '';
	successMessage = '';

	constructor(private authService: AuthService, private router: Router) { }

	isEmailValid(email: string): boolean {
		const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
		return emailPattern.test(email);
	}

	register() {
		if (!this.isEmailValid(this.email)) {
			this.errorMessage = 'Formato de e-mail inválido!';
			return;
		}

		// Cria um objeto com os dados do usuário para envio à API
		const userData = { nome: this.nome, email: this.email, senha: this.senha };

		this.authService.register(userData).subscribe({
			next: () => {
				this.successMessage = 'Usuário cadastrado com sucesso!';
				setTimeout(() => this.router.navigate(['/login']), 1000);
			  },
			error: () => this.errorMessage = 'Erro ao criar usuário!'
		});
	}
}
