package com.gabriel.gonoring.question.app.user.dto;


import java.util.UUID;

public class UpdatedUserDTO extends BaseManagedUserDTO{

    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
