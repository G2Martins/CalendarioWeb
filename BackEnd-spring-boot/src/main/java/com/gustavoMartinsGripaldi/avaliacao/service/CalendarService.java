package com.gustavoMartinsGripaldi.avaliacao.service;

import com.gustavoMartinsGripaldi.avaliacao.dto.EventDTO;
import com.gustavoMartinsGripaldi.avaliacao.dto.UserDTO;
import com.gustavoMartinsGripaldi.avaliacao.model.Event;
import com.gustavoMartinsGripaldi.avaliacao.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CalendarService {

    private final Logger logger = LoggerFactory.getLogger(CalendarService.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    // 🔹 Retorna todos os eventos de um usuário com DTOs
    public List<EventDTO> getUserEvents(String email) {
        Optional<User> user = userService.getUserByEmail(email);
        if (user.isEmpty()) {
            logger.warn("Usuário com email {} não encontrado", email);
            throw new RuntimeException("Usuário não encontrado.");
        }

        return eventService.getEventsByUser(user.get().getId())
                .stream()
                .map(EventDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 🔹 Cria um evento para um usuário
    public EventDTO createEventForUser(String email, EventDTO eventDTO) {
        Optional<User> user = userService.getUserByEmail(email);
        if (user.isEmpty()) {
            logger.warn("Tentativa de criar evento para usuário inexistente: {}", email);
            throw new RuntimeException("Usuário não encontrado.");
        }

        // Gera um ID manualmente (ou poderia vir do front-end, se necessário)
        String eventId = UUID.randomUUID().toString();

        Event event = new Event(
                eventId,
                eventDTO.getDescricao(),
                eventDTO.getHoraInicio(),
                eventDTO.getHoraTermino(),
                user.get().getId()
        );

        Event savedEvent = eventService.createEvent(event);
        logger.info("Evento criado com sucesso para usuário {} com ID {}", email, eventId);

        return EventDTO.fromEntity(savedEvent);
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

    // 🔹 Cria um usuário
    public UserDTO createUser(UserDTO userDTO) {
        // Gera um ID manualmente para o usuário
        String userId = UUID.randomUUID().toString();

        User user = new User(
                userId,
                userDTO.getNome(),
                userDTO.getEmail(),
                userDTO.getSenha()
        );

        User savedUser = userService.createUser(user);
        logger.info("Usuário criado com sucesso: {} (ID: {})", savedUser.getEmail(), userId);

        return UserDTO.fromEntity(savedUser);
    }
}
