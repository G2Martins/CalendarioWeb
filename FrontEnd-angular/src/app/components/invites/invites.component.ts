import { Component, OnInit } from '@angular/core';
import { InviteService } from 'src/app/services/invite.service';
import { EventService } from 'src/app/services/event.service';

@Component({
	selector: 'app-invites',
	templateUrl: './invites.component.html',
})
export class InvitesComponent implements OnInit {
	convites: any[] = [];
	modalAberto: boolean = false;
	eventoSelecionado: any = null;
	emailConvidado: string = '';
	eventos: any[] = [];
	dropdownAberto: boolean = false;

	constructor(
		private inviteService: InviteService,
		private eventService: EventService
	) {}

	ngOnInit(): void {
		this.carregarConvites();
		this.carregarEventos();
	}

	carregarConvites(): void {
		this.inviteService.listarConvites().subscribe((data) => {
			this.convites = data.filter((evento: any) => evento.status === 'PENDENTE');
		});
	}

	carregarEventos(): void {
		const userEmail = localStorage.getItem('userEmail');
		if (userEmail) {
			this.eventService.getEventsByUser(userEmail).subscribe((eventos) => {
				this.eventos = eventos;
			});
		}
	}

	toggleDropdown(): void {
		this.dropdownAberto = !this.dropdownAberto;
	}

	selecionarEvento(evento: any): void {
		this.eventoSelecionado = evento;
		this.dropdownAberto = false;
		this.abrirModal(evento);
	}

	abrirModal(evento: any): void {
		this.eventoSelecionado = evento || { descricao: 'Novo Evento' };
		this.emailConvidado = '';
		this.modalAberto = true;
	}

	fecharModal(): void {
		this.modalAberto = false;
		this.eventoSelecionado = null;
		this.emailConvidado = '';
	}

	enviarConvite(): void {
		if (!this.emailConvidado || !this.eventoSelecionado?.id) return;

		this.inviteService.enviarConvite(this.eventoSelecionado.id, this.emailConvidado)
			.subscribe(() => {
				this.fecharModal();
			});
	}

	aceitarConvite(eventId: string): void {
		this.inviteService.responderConvite(eventId, 'ACEITO').subscribe({
			next: () => {
				console.log(`Convite para o evento ${eventId} aceito.`);
				this.carregarConvites();
				this.eventService.carregarEventos();
			},
			error: (err) => console.error(`Erro ao aceitar convite:`, err)
		});
	}

	recusarConvite(eventId: string): void {
		this.inviteService.responderConvite(eventId, 'RECUSADO').subscribe(() => {
			this.carregarConvites();
			this.eventService.carregarEventos();
		});
	}
}