package org.lois.logic.parser.tree.operators;

import org.lois.logic.domain.Logic;
import org.lois.logic.domain.Value;

public class LEDiamondNode extends LEAbstractNode{

    public LEDiamondNode(Logic context) {
        this.context = context;
    }
    @Override
    public Value calcValue() {
        return context.diamond(rightChild.calcValue());
    }
}
