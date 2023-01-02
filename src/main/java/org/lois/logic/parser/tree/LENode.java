package org.lois.logic.parser.tree;

import org.lois.logic.domain.OperationContext;
import org.lois.logic.domain.Value;

public interface LENode {
    LENode getLeftChild();

    void setLeftChild(LENode leftChild);

    LENode getRightChild();

    void setRightChild(LENode rightChild);

    OperationContext getContext();
    void setContext(OperationContext context);

    Value calcValue();
}
