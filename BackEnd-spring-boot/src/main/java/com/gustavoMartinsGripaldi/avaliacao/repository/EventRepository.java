package com.gustavoMartinsGripaldi.avaliacao.repository;

import com.gustavoMartinsGripaldi.avaliacao.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface EventRepository extends MongoRepository<Event, String> {
    
    List<Event> findByUserEmail(String userEmail); // Busca eventos criados pelo usuário

    List<Event> findByConvidadosContaining(String convidadoEmail); // Busca eventos onde o usuário foi convidado
}
