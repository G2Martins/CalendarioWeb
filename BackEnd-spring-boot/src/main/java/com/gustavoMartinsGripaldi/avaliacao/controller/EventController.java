package com.gustavoMartinsGripaldi.avaliacao.controller;

import com.gustavoMartinsGripaldi.avaliacao.dto.EventDTO;
import com.gustavoMartinsGripaldi.avaliacao.model.Event;
import com.gustavoMartinsGripaldi.avaliacao.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;
    
    // Criar um evento
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        Event event = new Event(
            null,  
            eventDTO.getDescricao(),
            eventDTO.getHoraInicio(),
            eventDTO.getHoraTermino(),
            eventDTO.getUserId()
        );

        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok(EventDTO.fromEntity(createdEvent));
    }

    // Buscar todos os eventos de um usuário
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EventDTO>> getEventsByUser(@PathVariable String userId) {
        List<EventDTO> events = eventService.getEventsByUser(userId)
                                           .stream()
                                           .map(EventDTO::fromEntity)
                                           .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }

    // Buscar evento por ID
    @GetMapping("/{eventId}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable String eventId) {
        Optional<Event> event = eventService.getEventById(eventId);
        return event.map(e -> ResponseEntity.ok(EventDTO.fromEntity(e)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um evento existente
    @PutMapping("/{eventId}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable String eventId, @RequestBody EventDTO eventDTO) {
        Optional<Event> existingEvent = eventService.getEventById(eventId);
        if (existingEvent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Event event = new Event(
            eventId, 
            eventDTO.getDescricao(),
            eventDTO.getHoraInicio(),
            eventDTO.getHoraTermino(),
            eventDTO.getUserId()
        );

        Event updatedEvent = eventService.updateEvent(eventId, event);
        return ResponseEntity.ok(EventDTO.fromEntity(updatedEvent));
    }

    // Excluir um evento
    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }
}
