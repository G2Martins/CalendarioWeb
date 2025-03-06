package com.gustavoMartinsGripaldi.avaliacao.repository;

import com.gustavoMartinsGripaldi.avaliacao.model.Event;
import com.gustavoMartinsGripaldi.avaliacao.model.EventStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findByUserEmail(String userEmail); // Para listar eventos de um usuário específico.
    
    List<Event> findByConvidadosContaining(String convidadoEmail); // Para listar eventos que o usuário foi convidado.

    List<Event> findByConvidadosContainingAndStatus(String convidadoEmail, EventStatus status); // Para listar convites filtrando pelo status.
}
