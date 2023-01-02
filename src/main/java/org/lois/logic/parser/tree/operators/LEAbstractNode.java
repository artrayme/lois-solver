package org.lois.logic.parser.tree.operators;

import org.lois.logic.parser.tree.LENode;

public abstract class LEAbstractNode implements LENode {

    private LENode leftChild;
    private LENode rightChild;
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

}
