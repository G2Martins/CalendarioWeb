import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
	selector: 'app-menu-bar',
	templateUrl: './menu-bar.component.html',
	styleUrls: ['./menu-bar.component.css']
})
export class MenuBarComponent {
	constructor(private router: Router) { }

	logout() {
		localStorage.removeItem('userId');
		this.router.navigate(['/login']);
	}
}
