package com.epam.bookLibrary.model.abstracts;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public abstract class AbstractEnum extends AbstractEntity implements Serializable {

    @NotEmpty
    @Size(max = 40)
    private String description;

    public AbstractEnum(String description) {
        this.description = description;
    }

    public AbstractEnum() {
        setId(-1);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
