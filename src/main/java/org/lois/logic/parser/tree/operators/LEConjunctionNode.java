package org.lois.logic.parser.tree.operators;

import org.lois.logic.domain.Logic;
import org.lois.logic.domain.Value;

public class LEConjunctionNode extends LEAbstractNode{

    public LEConjunctionNode(Logic context) {
        this.context = context;
    }
    @Override
    public Value calcValue() {
        return context.and(leftChild.calcValue(), rightChild.calcValue());
    }
}
