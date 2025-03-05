import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
	selector: 'app-menu-bar',
	templateUrl: './menu-bar.component.html',
	styleUrls: ['./menu-bar.component.css']
})
export class MenuBarComponent {
	logoutMessage: String = '';
	showAlert = false;

	constructor(private router: Router) { }

	logout() {
		localStorage.removeItem('userId');
		localStorage.removeItem('userEmail');
		this.logoutMessage = 'Você saiu da conta!';
		this.showAlert = true;

		setTimeout(() => {
			this.logoutMessage = '';
			this.showAlert = false;
			this.router.navigate(['/login']);
		  }, 1500);
	}
}
