package org.lois.logic.domain;

import lombok.Data;

@Data
public class Variable {
    private Value value;

    public Variable(Value value) {
        this.value = value;
    }
}
