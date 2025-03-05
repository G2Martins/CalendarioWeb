package com.gustavoMartinsGripaldi.avaliacao.dto;

import com.gustavoMartinsGripaldi.avaliacao.model.Event;
import java.time.LocalDateTime;

//DTO (Data Transfer Object) é um padrão de projeto usado para transferir dados entre camadas de um sistema
public class EventDTO {
    private String id;
    private String descricao;
    private LocalDateTime horaInicio;
    private LocalDateTime horaTermino;
    private String userEmail;

    public EventDTO() {}

    public EventDTO(String id, String descricao, LocalDateTime horaInicio, LocalDateTime horaTermino, String userEmail) {
        this.id = id;
        this.descricao = descricao;
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
        this.userEmail = userEmail;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDateTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalDateTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalDateTime getHoraTermino() { return horaTermino; }
    public void setHoraTermino(LocalDateTime horaTermino) { this.horaTermino = horaTermino; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public static EventDTO fromEntity(Event event) {
        return new EventDTO(
            event.getId(),
            event.getDescricao(),
            event.getHoraInicio(),
            event.getHoraTermino(),
            event.getUserEmail()
        );
    }

    public Event toEntity() {
        return new Event(id, descricao, horaInicio, horaTermino, userEmail);
    }
}
