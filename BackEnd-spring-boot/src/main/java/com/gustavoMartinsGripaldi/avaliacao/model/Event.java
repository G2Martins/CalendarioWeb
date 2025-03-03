package com.gustavoMartinsGripaldi.avaliacao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "events")
public class Event {
    @Id
    private String id;
    private String descricao;
    private LocalDateTime horaInicio;
    private LocalDateTime horaTermino;
    private String userId;

    public Event() {}

    public Event(String id, String descricao, LocalDateTime horaInicio, LocalDateTime horaTermino, String userId) {
        this.id = id;
        this.descricao = descricao;
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
        this.userId = userId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDateTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalDateTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalDateTime getHoraTermino() { return horaTermino; }
    public void setHoraTermino(LocalDateTime horaTermino) { this.horaTermino = horaTermino; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}
