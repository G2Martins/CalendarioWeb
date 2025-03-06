package com.gustavoMartinsGripaldi.avaliacao.service;

import com.gustavoMartinsGripaldi.avaliacao.model.Event;
import com.gustavoMartinsGripaldi.avaliacao.model.EventStatus;
import com.gustavoMartinsGripaldi.avaliacao.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // 🔹 Criar um evento (verificando conflitos de horário)
    public Event createEvent(Event event) {
        List<Event> conflictingEvents = eventRepository.findByUserEmail(event.getUserEmail())
                .stream()
                .filter(e -> overlaps(e, event))
                .toList();

        if (!conflictingEvents.isEmpty()) {
            throw new RuntimeException("Já existe um evento nesse horário.");
        }

        return eventRepository.save(event);
    }

    // 🔹 Buscar eventos do usuário (criados)
    public List<Event> getEventsByUserEmail(String email) {
        // Eventos criados pelo usuário
        List<Event> createdEvents = eventRepository.findByUserEmail(email);

        // Eventos onde o usuário foi convidado, mas somente os que estão ACEITO
        List<Event> invitedEvents = eventRepository.findByConvidadosContaining(email)
                .stream()
                .filter(event -> event.getStatus() == EventStatus.ACEITO)
                .toList();

        // Mesclar as duas listas
        createdEvents.addAll(invitedEvents);
        return createdEvents;
    }

    // 🔹 Buscar um evento pelo ID
    public Optional<Event> getEventById(String eventId) {
        return eventRepository.findById(eventId);
    }

    // 🔹 Atualizar um evento pelo ID
    public Event updateEvent(String eventId, Event updatedEvent) {
        return eventRepository.findById(eventId).map(event -> {
            event.setDescricao(updatedEvent.getDescricao());
            event.setHoraInicio(updatedEvent.getHoraInicio());
            event.setHoraTermino(updatedEvent.getHoraTermino());
            event.setUserEmail(updatedEvent.getUserEmail());
            return eventRepository.save(event);
        }).orElseThrow(() -> new RuntimeException("Evento não encontrado."));
    }

    // 🔹 Deletar um evento pelo ID
    public void deleteEvent(String eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new RuntimeException("Evento não encontrado.");
        }
        eventRepository.deleteById(eventId);
    }

    // 🔹 Verifica sobreposição de horários
    private boolean overlaps(Event existing, Event newEvent) {
        return existing.getHoraInicio().isBefore(newEvent.getHoraTermino()) &&
                newEvent.getHoraInicio().isBefore(existing.getHoraTermino());
    }

    // 🔹 Enviar convites para usuários
    public Event addInvites(String eventId, List<String> emails) {
        return eventRepository.findById(eventId).map(event -> {
            event.getConvidados().addAll(emails);
            event.setStatus(EventStatus.PENDENTE);
            return eventRepository.save(event);
        }).orElseThrow(() -> new RuntimeException("Evento não encontrado."));
    }

    // 🔹 Responder convite (Aceitar ou Recusar)
    public Event updateInviteStatus(String eventId, String convidadoEmail, EventStatus status) {
        return eventRepository.findById(eventId).map(event -> {
            if (!event.getConvidados().contains(convidadoEmail)) {
                throw new RuntimeException("Usuário não convidado para este evento.");
            }

            if (status == EventStatus.ACEITO) {
                // Se pelo menos um convidado aceita, o evento é marcado como ACEITO
                event.setStatus(EventStatus.ACEITO);
            } else if (status == EventStatus.RECUSADO) {
                // Remove o email do convidado ao recusar
                event.getConvidados().remove(convidadoEmail);
                // Se não houver mais convidados, define o status como SEM_CONVIDADOS; caso
                // contrário, mantém como PENDENTE
                if (event.getConvidados().isEmpty()) {
                    event.setStatus(EventStatus.SEM_CONVIDADOS);
                } else {
                    event.setStatus(EventStatus.PENDENTE);
                }
            }

            return eventRepository.save(event);
        }).orElseThrow(() -> new RuntimeException("Evento não encontrado."));
    }

    // 🔹 Buscar eventos em que um usuário foi convidado
    public List<Event> getInvitedEvents(String convidadoEmail) {
        return eventRepository.findByConvidadosContaining(convidadoEmail);
    }

    // 🔹 Buscar eventos em que um usuário foi convidado e possuem um status
    // específico
    public List<Event> getInvitedEventsByStatus(String convidadoEmail, EventStatus status) {
        return eventRepository.findByConvidadosContaining(convidadoEmail)
                .stream()
                .filter(event -> event.getStatus() == status)
                .toList();
    }

}
