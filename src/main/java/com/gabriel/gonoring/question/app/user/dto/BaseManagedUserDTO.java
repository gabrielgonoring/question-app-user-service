package com.gabriel.gonoring.question.app.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BaseManagedUserDTO {

    @NotNull
    @NotBlank
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
