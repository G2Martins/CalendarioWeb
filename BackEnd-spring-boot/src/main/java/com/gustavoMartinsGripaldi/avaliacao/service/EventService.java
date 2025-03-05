package com.gustavoMartinsGripaldi.avaliacao.service;

import com.gustavoMartinsGripaldi.avaliacao.model.Event;
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

    // 🔹 Buscar eventos pelo email do usuário
    public List<Event> getEventsByUserEmail(String email) {
        return eventRepository.findByUserEmail(email);
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
}
