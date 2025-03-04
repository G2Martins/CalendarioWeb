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

	constructor(private authService: AuthService, private router: Router) { }

	login() {
		this.authService.getUserByEmail(this.email).subscribe({
			next: (user) => {
				if (user && user.senha === this.password) {
					localStorage.setItem('userId', user.id);
					this.router.navigate(['/calendar']);
				} else {
					this.errorMessage = 'Credenciais inválidas!';
				}
			},
			error: (error) => {
				if (error.status === 404) {
					this.errorMessage = 'Usuário não encontrado!';
				} else {
					this.errorMessage = 'Erro ao conectar ao servidor!';
				}
			}
		});
	}
}
