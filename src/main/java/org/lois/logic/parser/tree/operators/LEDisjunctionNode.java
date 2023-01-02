package org.lois.logic.parser.tree.operators;

import org.lois.logic.domain.Logic;
import org.lois.logic.domain.Value;

public class LEDisjunctionNode extends LEAbstractNode{

    public LEDisjunctionNode(Logic context) {
        this.context = context;
    }
    @Override
    public Value calcValue() {
        return context.or(leftChild.calcValue(), rightChild.calcValue());
    }
}
