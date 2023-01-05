package org.lois.logic.controller;

import lombok.Data;

@Data
public class FormulaDTO {
    private String formula;

    public FormulaDTO(String formula) {
        this.formula = formula;
    }
}
