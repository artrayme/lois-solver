package org.lois.logic.parser.tree.operators;

import org.lois.logic.domain.OperationContext;
import org.lois.logic.domain.Value;

public class LEDiamondNode extends LEAbstractNode{

    public LEDiamondNode(OperationContext context) {
        this.context = context;
    }
    @Override
    public Value calcValue() {
        return context.diamond(leftChild.calcValue(), rightChild.calcValue());
    }
}
