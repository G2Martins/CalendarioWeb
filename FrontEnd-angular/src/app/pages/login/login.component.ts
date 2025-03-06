import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent {

	email = '';
	password = '';
	errorMessage = '';
	showPassword = false;

	constructor(private authService: AuthService, private router: Router) { }

	togglePasswordVisibility() {
		this.showPassword = !this.showPassword;
	}

	login() {
		this.authService.getUserByEmail(this.email).subscribe({
			next: (user) => {
				// Verifica se o usuário existe e se a senha está correta
				if (user && user.senha === this.password) {
					localStorage.setItem('userId', user.id);
					localStorage.setItem('userEmail', this.email);
					this.router.navigate(['/calendar']);
				} else {
					this.errorMessage = 'Credenciais inválidas!';
				}
			},
			error: (error) => {
				// Trata os erros da requisição, diferenciando usuário não encontrado de outros erros
				if (error.status === 404) {
					this.errorMessage = 'Usuário não encontrado!';
				} else {
					this.errorMessage = 'Erro ao conectar ao servidor!';
				}
			}
		});
	}
}
