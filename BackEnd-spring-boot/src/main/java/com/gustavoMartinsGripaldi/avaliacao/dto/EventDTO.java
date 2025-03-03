package com.gustavoMartinsGripaldi.avaliacao.dto;

import com.gustavoMartinsGripaldi.avaliacao.model.Event;
import java.time.LocalDateTime;

public class EventDTO {
    private String descricao;
    private LocalDateTime horaInicio;
    private LocalDateTime horaTermino;
    private String userId;

    public EventDTO() {}

    public EventDTO( String descricao, LocalDateTime horaInicio, LocalDateTime horaTermino, String userId) {
        this.descricao = descricao;
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
        this.userId = userId;
    }

 

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDateTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalDateTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalDateTime getHoraTermino() { return horaTermino; }
    public void setHoraTermino(LocalDateTime horaTermino) { this.horaTermino = horaTermino; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public static EventDTO fromEntity(Event event) {
        return new EventDTO(
            event.getDescricao(),
            event.getHoraInicio(),
            event.getHoraTermino(),
            event.getUserId()
        );
    }
}
