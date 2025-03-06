package com.gustavoMartinsGripaldi.avaliacao.controller;

import com.gustavoMartinsGripaldi.avaliacao.dto.EventDTO;
import com.gustavoMartinsGripaldi.avaliacao.model.Event;
import com.gustavoMartinsGripaldi.avaliacao.model.EventStatus;
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

    // 🔹 Criar um evento
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) {
        Event event = eventDTO.toEntity();
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok(EventDTO.fromEntity(createdEvent));
    }

    // 🔹 Buscar todos os eventos de um usuário pelo e-mail
    @GetMapping("/user/{email}")
    public ResponseEntity<List<EventDTO>> getEventsByUser(@PathVariable String email) {
        List<EventDTO> events = eventService.getEventsByUserEmail(email)
                                            .stream()
                                            .map(EventDTO::fromEntity)
                                            .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }

    // 🔹 Buscar evento por ID
    @GetMapping("/{eventId}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable String eventId) {
        Optional<Event> event = eventService.getEventById(eventId);
        return event.map(e -> ResponseEntity.ok(EventDTO.fromEntity(e)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Atualizar um evento pelo ID
    @PutMapping("/{eventId}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable String eventId, @RequestBody EventDTO eventDTO) {
        Optional<Event> existingEvent = eventService.getEventById(eventId);
        if (existingEvent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Event updatedEvent = eventService.updateEvent(eventId, eventDTO.toEntity());
        return ResponseEntity.ok(EventDTO.fromEntity(updatedEvent));
    }

    // 🔹 Deletar um evento pelo ID
    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String eventId) {
        if (eventService.getEventById(eventId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Enviar convites para convidados
    @PostMapping("/{eventId}/invite")
    public ResponseEntity<EventDTO> addInvites(@PathVariable String eventId, @RequestBody List<String> convidados) {
        Event event = eventService.addInvites(eventId, convidados);
        return ResponseEntity.ok(EventDTO.fromEntity(event));
    }

    // 🔹 Responder convite (Aceitar ou Recusar)
    @PutMapping("/{eventId}/invite/{convidadoEmail}/{status}")
    public ResponseEntity<EventDTO> updateInviteStatus(
            @PathVariable String eventId,
            @PathVariable String convidadoEmail,
            @PathVariable EventStatus status) {
        Event event = eventService.updateInviteStatus(eventId, convidadoEmail, status);
        return ResponseEntity.ok(EventDTO.fromEntity(event));
    }

    // 🔹 Listar eventos em que um usuário foi convidado
    @GetMapping("/invites/{email}")
    public ResponseEntity<List<EventDTO>> getInvitedEvents(@PathVariable String email) {
        List<EventDTO> events = eventService.getInvitedEvents(email)
                                            .stream()
                                            .map(EventDTO::fromEntity)
                                            .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }

    // 🔹 Listar eventos com status específico para um convidado
    @GetMapping("/invites/{email}/{status}")
    public ResponseEntity<List<EventDTO>> getInvitedEventsByStatus(
            @PathVariable String email,
            @PathVariable EventStatus status) {
        List<EventDTO> events = eventService.getInvitedEventsByStatus(email, status)
                                            .stream()
                                            .map(EventDTO::fromEntity)
                                            .collect(Collectors.toList());
        return ResponseEntity.ok(events);
    }
}
