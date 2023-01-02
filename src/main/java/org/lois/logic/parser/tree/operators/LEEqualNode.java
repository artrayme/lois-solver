package org.lois.logic.parser.tree.operators;

import org.lois.logic.domain.OperationContext;
import org.lois.logic.domain.Value;

public class LEEqualNode extends LEAbstractNode {

    public LEEqualNode(OperationContext context) {
        this.context = context;
    }
    @Override
    public Value calcValue() {
        return context.equal(leftChild.calcValue(), rightChild.calcValue());
    }
}
