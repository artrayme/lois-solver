package org.lois.logic.parser.tree;

import org.lois.logic.domain.Logic;
import org.lois.logic.domain.Value;

public interface LENode {
    LENode getLeftChild();

    void setLeftChild(LENode leftChild);

    LENode getRightChild();

    void setRightChild(LENode rightChild);

    Logic getContext();
    void setContext(Logic context);

    Value calcValue();
}
