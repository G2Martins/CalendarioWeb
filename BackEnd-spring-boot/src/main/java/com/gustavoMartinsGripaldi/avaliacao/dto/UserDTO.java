package com.gustavoMartinsGripaldi.avaliacao.dto;

import com.gustavoMartinsGripaldi.avaliacao.model.User;
//DTO (Data Transfer Object) é um padrão de projeto usado para transferir dados entre camadas de um sistema
public class UserDTO {
    
    private String nome;
    private String email;
    private String senha;

    public UserDTO() {}

    public UserDTO( String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public static UserDTO fromEntity(User user) {
        return new UserDTO(
            user.getNome(),
            user.getEmail(),
            user.getSenha()
        );
    }
}
