import { Component, OnInit } from '@angular/core';
import { EventService } from 'src/app/services/event.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
	selector: 'app-calendar',
	templateUrl: './calendar.component.html',
	styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {
	userEmail: string = '';
	diasSemana = ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'];
	diasDoMes: any[] = [];
	mesAtual: number = new Date().getMonth();
	anoAtual: number = new Date().getFullYear();
	modalAberto: boolean = false;
	eventoSelecionado: any = null;
	descricao: string = '';
	horaInicio: string = '';
	horaTermino: string = '';
	alertaConflito: string = '';

	constructor(private eventService: EventService, private authService: AuthService) { }

	ngOnInit(): void {
		// Obtém o email do usuário logado a partir do localStorage
		const userData = localStorage.getItem('userEmail');
		if (userData) {
			this.userEmail = userData;
			console.log("Usuário logado:", this.userEmail);
			this.carregarEventos();
		} else {
			console.log("Nenhum usuário encontrado no localStorage.");
		}
	}

	// Carrega os eventos do usuário a partir do serviço
	carregarEventos(): void {
		this.eventService.getEventsByUser(this.userEmail).subscribe(events => {
			this.gerarCalendario(events);
		});
	}

	// Gera a estrutura do calendário para o mês atual
	gerarCalendario(eventos: any[]): void {
		const ultimoDia = new Date(this.anoAtual, this.mesAtual + 1, 0);
		const dias = [];

		for (let dia = 1; dia <= ultimoDia.getDate(); dia++) {
			const dataAtual = new Date(this.anoAtual, this.mesAtual, dia);
			dias.push({
				date: dataAtual,
				isToday: dataAtual.toDateString() === new Date().toDateString(),
				events: eventos.filter(e => {
					const eventoInicio = new Date(e.horaInicio);
					const eventoFim = new Date(e.horaTermino);
					return (
						eventoInicio.toDateString() === dataAtual.toDateString() || 
						(eventoInicio < dataAtual && eventoFim >= dataAtual) 
					);
				})
			});
		}

		this.diasDoMes = dias;
	}

	// Abre o modal para criação ou edição de evento
	abrirModal(date?: Date): void {
		this.eventoSelecionado = null;
		this.descricao = '';
		this.horaInicio = date ? date.toISOString().slice(0, 16) : '';
		this.horaTermino = date ? date.toISOString().slice(0, 16) : '';
		this.alertaConflito = '';
		this.modalAberto = true;
	}

	// Abre o modal preenchendo os dados de um evento existente para edição
	editarEvento(evento: any): void {
		this.eventoSelecionado = evento;
		this.descricao = evento.descricao;
		this.horaInicio = evento.horaInicio;
		this.horaTermino = evento.horaTermino;
		this.modalAberto = true;
		this.alertaConflito = '';
	}

	// Verifica se há conflito de horário com eventos existente
	verificarConflito(): boolean {
		const inicio = new Date(this.horaInicio);
		const fim = new Date(this.horaTermino);

		for (let dia of this.diasDoMes) 
		{
			for (let evento of dia.events) 
			{
				// Ignora o evento que está sendo editado
				if (this.eventoSelecionado && evento.id === this.eventoSelecionado.id) {
					continue; // Pula a verificação para o próprio evento
				}

				const eventoInicio = new Date(evento.horaInicio);
				const eventoFim = new Date(evento.horaTermino);

				if (
					(inicio >= eventoInicio && inicio < eventoFim) ||
					(fim > eventoInicio && fim <= eventoFim) ||
					(inicio <= eventoInicio && fim >= eventoFim)
				) {
					this.alertaConflito = 'Conflito de horário! Escolha outro horário para o evento.';
					return true;
				}
			}
		}
		this.alertaConflito = '';
		return false;
	}

	// Salva um novo evento ou atualiza um existente
	salvarEvento(): void {
		const inicio = new Date(this.horaInicio);
  		const fim = new Date(this.horaTermino);
		
		// Verifica se a hora de início é maior ou igual à hora de término
		if (inicio >= fim) {
			this.alertaConflito = 'A hora de início não pode ser posterior ou igual à hora de término.';
			return;
		}
		
		if (this.verificarConflito()) {
			return;
		}
		
		const evento = {
			descricao: this.descricao,
			horaInicio: this.horaInicio,
			horaTermino: this.horaTermino,
			userEmail: this.userEmail
		};

		if (this.eventoSelecionado) {
			this.eventService.updateEvent(this.eventoSelecionado.id, evento).subscribe(() => {
				this.carregarEventos();
			});
		} else {
			this.eventService.createEvent(evento).subscribe(() => {
				this.carregarEventos();
			});
		}
		this.alertaConflito = '';
		this.modalAberto = false;
	}

	excluirEvento(): void {
		if (this.eventoSelecionado) {
			this.eventService.deleteEvent(this.eventoSelecionado.id).subscribe(() => {
				this.carregarEventos();
			});
		}
		this.modalAberto = false;
	}

	prevMonth(): void {
		if (this.mesAtual === 0) {
			this.mesAtual = 11;
			this.anoAtual--;
		} else {
			this.mesAtual--;
		}
		this.carregarEventos();
	}

	nextMonth(): void {
		if (this.mesAtual === 11) {
			this.mesAtual = 0;
			this.anoAtual++;
		} else {
			this.mesAtual++;
		}
		this.carregarEventos();
	}

	// Retorna o nome do mês atual formatado em português
	get mesAtualNome(): string {
		return new Date(this.anoAtual, this.mesAtual).toLocaleString('pt-BR', { month: 'long' })
			.replace(/^./, (char) => char.toUpperCase());
	}
}
