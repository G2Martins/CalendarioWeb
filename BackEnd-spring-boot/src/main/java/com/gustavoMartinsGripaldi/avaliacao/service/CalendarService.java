package com.gustavoMartinsGripaldi.avaliacao.service;

import com.gustavoMartinsGripaldi.avaliacao.dto.EventDTO;
import com.gustavoMartinsGripaldi.avaliacao.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarService {

    private final Logger logger = LoggerFactory.getLogger(CalendarService.class);

    @Autowired
    private EventService eventService;

    // 🔹 Retorna todos os eventos de um usuário pelo e-mail
    public List<EventDTO> getUserEvents(String email) {
        List<Event> events = eventService.getEventsByUserEmail(email);

        if (events.isEmpty()) {
            logger.warn("Nenhum evento encontrado para o usuário: {}", email);
        }

        return events.stream()
                .map(EventDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 🔹 Cria um evento para um usuário
    public EventDTO createEventForUser(String email, EventDTO eventDTO) {
        Event event = new Event(
                null,
                eventDTO.getDescricao(),
                eventDTO.getHoraInicio(),
                eventDTO.getHoraTermino(),
                email
        );

        Event savedEvent = eventService.createEvent(event);
        logger.info("Evento criado com sucesso para usuário {} com ID {}", email, savedEvent.getId());

        return EventDTO.fromEntity(savedEvent);
    }

    // 🔹 Atualiza um evento pelo ID
    public EventDTO updateEvent(String eventId, EventDTO eventDTO) {
        Event updatedEvent = eventService.updateEvent(eventId, eventDTO.toEntity());
        logger.info("Evento {} atualizado com sucesso", eventId);
        return EventDTO.fromEntity(updatedEvent);
    }

    // 🔹 Deleta um evento pelo ID
    public void deleteEvent(String eventId) {
        if (eventService.getEventById(eventId).isEmpty()) {
            logger.warn("Tentativa de deletar evento inexistente: {}", eventId);
            throw new RuntimeException("Evento não encontrado.");
        }
        eventService.deleteEvent(eventId);
        logger.info("Evento {} deletado com sucesso", eventId);
    }
}
