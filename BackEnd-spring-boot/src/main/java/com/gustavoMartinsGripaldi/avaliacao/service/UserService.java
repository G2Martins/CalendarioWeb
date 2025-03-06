package com.gustavoMartinsGripaldi.avaliacao.service;

import com.gustavoMartinsGripaldi.avaliacao.model.User;
import com.gustavoMartinsGripaldi.avaliacao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 🔹 Cadastra um novo usuário impedindo e-mails duplicados
    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Usuário com este e-mail já existe.");
        }
    
        // Criar o usuário sem passar um ID
        return userRepository.save(new User(null, user.getNome(), user.getEmail(), user.getSenha()));
    }
    
    // 🔹 Retorna um usuário pelo e-mail
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 🔹 Atualiza os dados de um usuário
    public User updateUser(String userId, User updatedUser) {
        return userRepository.findById(userId).map(user -> {
            user.setNome(updatedUser.getNome());
            user.setEmail(updatedUser.getEmail());
            user.setSenha(updatedUser.getSenha());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    // 🔹 Deleta um usuário pelo ID
    public void deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("Usuário não encontrado.");
        }
        userRepository.deleteById(userId);
    }
}
