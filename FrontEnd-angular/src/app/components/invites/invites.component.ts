import { Component, OnInit } from '@angular/core';
import { InviteService } from 'src/app/services/invite.service';

@Component({
	selector: 'app-invites',
	templateUrl: './invites.component.html',
})
export class InvitesComponent implements OnInit {
	convites: any[] = [];
	modalAberto: boolean = false;
	eventoSelecionado: any = null;
	emailConvidado: string = '';

	constructor(private inviteService: InviteService) { }

	ngOnInit(): void {
		this.carregarConvites();
	}

	carregarConvites(): void {
		this.inviteService.listarConvites().subscribe((data) => {
			this.convites = data;
		});
	}

	aceitarConvite(eventId: string): void {
		this.inviteService.responderConvite(eventId, 'ACEITO').subscribe(() => {
			this.carregarConvites();
		});
	}

	recusarConvite(eventId: string): void {
		this.inviteService.responderConvite(eventId, 'RECUSADO').subscribe(() => {
			this.carregarConvites();
		});
	}

	abrirModal(evento: any): void {
		this.eventoSelecionado = evento;
		this.emailConvidado = '';
		this.modalAberto = true;
	}

	fecharModal(): void {
		this.modalAberto = false;
		this.eventoSelecionado = null;
		this.emailConvidado = '';
	}

	enviarConvite(): void {
		if (!this.emailConvidado) return;

		this.inviteService.enviarConvite(this.eventoSelecionado.id, this.emailConvidado).subscribe(() => {
			this.fecharModal();
		});
	}
}
