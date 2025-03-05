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

  constructor(private authService: AuthService, private router: Router) {}

  register() {
    const userData = { nome: this.nome, email: this.email, senha: this.senha };

    this.authService.register(userData).subscribe({
      next: () => this.router.navigate(['/login']),
      error: () => this.errorMessage = 'Erro ao criar usuário!'
    });
  }
}
