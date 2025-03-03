package com.gustavoMartinsGripaldi.avaliacao.controller;

import com.gustavoMartinsGripaldi.avaliacao.dto.UserDTO;
import com.gustavoMartinsGripaldi.avaliacao.model.User;
import com.gustavoMartinsGripaldi.avaliacao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Criar um novo usuário
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = new User(
            null, 
            userDTO.getNome(),
            userDTO.getEmail(),
            userDTO.getSenha()
        );

        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(UserDTO.fromEntity(createdUser));
    }

    // Buscar um usuário pelo e-mail
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(u -> ResponseEntity.ok(UserDTO.fromEntity(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
