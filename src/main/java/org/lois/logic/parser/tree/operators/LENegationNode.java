package org.lois.logic.parser.tree.operators;

import org.lois.logic.domain.OperationContext;
import org.lois.logic.domain.Value;

public class LENegationNode extends LEAbstractNode{

    public LENegationNode(OperationContext context) {
        this.context = context;
    }

    @Override
    public Value calcValue() {
        return context.not(rightChild.calcValue());
    }
}
