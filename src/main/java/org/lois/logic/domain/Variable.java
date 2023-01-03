package org.lois.logic.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Variable {
    private Value value;

    public Variable(Value value) {
        this.value = value;
    }
}
