package org.lois.logic.parser.tree.operators;

import org.lois.logic.domain.Value;
import org.lois.logic.domain.Variable;

public class LEVariable extends LEAbstractNode{

    final Variable variable;

    public LEVariable(Variable variable) {
        this.variable = variable;
    }

    @Override
    public Value calcValue() {
        return variable.value();
    }
}
