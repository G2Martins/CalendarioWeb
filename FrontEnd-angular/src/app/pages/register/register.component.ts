import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.css']
})
export class RegisterComponent {
	email = '';
	password = '';
	errorMessage = '';

	constructor(private authService: AuthService, private router: Router) { }

	register() {
		this.authService.register({ email: this.email, password: this.password }).subscribe(
			() => this.router.navigate(['/login']),
			() => this.errorMessage = 'Erro ao criar usuário!'
		);
	}
}
