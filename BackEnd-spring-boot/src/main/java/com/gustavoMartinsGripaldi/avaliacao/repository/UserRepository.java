package com.gustavoMartinsGripaldi.avaliacao.repository;

import com.gustavoMartinsGripaldi.avaliacao.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email); // findByEmail para buscar usuários pelo e-mail.
}
