package com.unlam.library.domain;

import java.util.Date;

public class Client extends Person {

    private Long clientId;
    private Date created_at;
    private Status status;

    public Client (){

    }
    public Client(Long clientId,Date created_at) {
        super();
        this.clientId=clientId;
        this.created_at=created_at;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
