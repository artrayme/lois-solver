package org.lois.logic.parser.tree.operators;

import org.lois.logic.domain.Logic;
import org.lois.logic.domain.Value;

public class LEImplicationNode extends LEAbstractNode{
    public LEImplicationNode(Logic context) {
        this.context = context;
    }
    @Override
    public Value calcValue() {
        return context.implication(leftChild.calcValue(), rightChild.calcValue());
    }
}
