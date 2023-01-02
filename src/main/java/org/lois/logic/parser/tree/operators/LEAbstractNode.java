package org.lois.logic.parser.tree.operators;

import org.lois.logic.domain.Logic;
import org.lois.logic.parser.tree.LENode;

public abstract class LEAbstractNode implements LENode {
    protected Logic context;
    protected LENode leftChild;
    protected LENode rightChild;
    @Override
    public LENode getLeftChild() {
        return leftChild;
    }

    @Override
    public void setLeftChild(LENode leftChild) {
        this.leftChild = leftChild;
    }

    @Override
    public LENode getRightChild() {
        return rightChild;
    }

    @Override
    public void setRightChild(LENode rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public Logic getContext() {
        return context;
    }
    @Override
    public void setContext(Logic context) {
        this.context = context;
    }
}
