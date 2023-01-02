package org.lois.logic.parser.tree.operators;

import org.lois.logic.domain.Logic;
import org.lois.logic.domain.Value;

public class LESquareNode extends LEAbstractNode{

    public LESquareNode(Logic context) {
        this.context = context;
    }
    @Override
    public Value calcValue() {
        return context.square(rightChild.calcValue());
    }
}
