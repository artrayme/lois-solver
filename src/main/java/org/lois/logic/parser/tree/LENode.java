package org.lois.logic.parser.tree;

public interface LENode {
    LENode getLeftChild();

    void setLeftChild(LENode leftChild);

    LENode getRightChild();

    void setRightChild(LENode rightChild);

    boolean calcValue();
}
