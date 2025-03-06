package com.gustavoMartinsGripaldi.avaliacao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "events")
public class Event {
    @Id
    private String id;
    private String descricao;
    private LocalDateTime horaInicio;
    private LocalDateTime horaTermino;
    private String userEmail;
    private List<String> convidados = new ArrayList<>();

    private EventStatus status;

    public Event() {
        this.status = EventStatus.SEM_CONVIDADOS;
    }

    public Event(String id, String descricao, LocalDateTime horaInicio, LocalDateTime horaTermino, String userEmail, List<String> convidados, EventStatus status) {
        this.id = id;
        this.descricao = descricao;
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
        this.userEmail = userEmail;
        this.convidados = convidados;
        this.status = status != null ? status : EventStatus.SEM_CONVIDADOS;
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
    public List<String> getConvidados() { return convidados; }
    public void setConvidados(List<String> convidados) { this.convidados = convidados; }

    public EventStatus getStatus() { return status; }
    public void setStatus(EventStatus status) { this.status = status; }
}
