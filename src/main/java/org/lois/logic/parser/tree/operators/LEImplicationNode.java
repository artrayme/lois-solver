package org.lois.logic.parser.tree.operators;

import org.lois.logic.domain.OperationContext;
import org.lois.logic.domain.Value;

public class LEImplicationNode extends LEAbstractNode{
    public LEImplicationNode(OperationContext context) {
        this.context = context;
    }
    @Override
    public Value calcValue() {
        return context.impl(leftChild.calcValue(), rightChild.calcValue());
    }
}
