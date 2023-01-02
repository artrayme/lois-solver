package org.lois.logic.parser.tree.operators;

public class LENegationNode extends LEAbstractNode{

    @Override
    public boolean calcValue() {
        return false;
    }
}
