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

    // 🔹 Cria um evento, verificando conflitos de horário
    public Event createEvent(Event event) {
        List<Event> conflictingEvents = eventRepository.findByUserId(event.getUserId())
                .stream()
                .filter(e -> overlaps(e, event))
                .toList();
    
        if (!conflictingEvents.isEmpty()) {
            throw new RuntimeException("Já existe um evento nesse horário.");
        }
    
        // Criar o evento sem passar um ID
        return eventRepository.save(new Event(null, event.getDescricao(), event.getHoraInicio(), event.getHoraTermino(), event.getUserId()));
    }
    
    

    // 🔹 Lista eventos de um usuário
    public List<Event> getEventsByUser(String userId) {
        return eventRepository.findByUserId(userId);
    }

    // 🔹 Busca um evento pelo ID
    public Optional<Event> getEventById(String eventId) {
        return eventRepository.findById(eventId);
    }

    // 🔹 Atualiza um evento
    public Event updateEvent(String eventId, Event updatedEvent) {
        return eventRepository.findById(eventId).map(event -> {
            event.setDescricao(updatedEvent.getDescricao());
            event.setHoraInicio(updatedEvent.getHoraInicio());
            event.setHoraTermino(updatedEvent.getHoraTermino());
            event.setUserId(updatedEvent.getUserId());
            return eventRepository.save(event);
        }).orElseThrow(() -> new RuntimeException("Evento não encontrado."));
    }
    

    // 🔹 Remove um evento
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
