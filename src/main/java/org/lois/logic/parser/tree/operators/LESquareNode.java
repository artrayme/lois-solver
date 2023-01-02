package org.lois.logic.parser.tree.operators;

import org.lois.logic.domain.OperationContext;
import org.lois.logic.domain.Value;

public class LESquareNode extends LEAbstractNode{

    public LESquareNode(OperationContext context) {
        this.context = context;
    }
    @Override
    public Value calcValue() {
        return context.square(leftChild.calcValue(), rightChild.calcValue());
    }
}
