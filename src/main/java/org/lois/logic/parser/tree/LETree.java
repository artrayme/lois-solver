package org.lois.logic.parser.tree;

import org.lois.logic.domain.OperationContext;
import org.lois.logic.domain.Value;

import java.util.Map;

public class LETree {
    private final LENode root;
    private final Map<String, Value> values;
    private final OperationContext context;

    public LETree(LENode root, Map<String, Value> values, OperationContext context) {
        this.root = root;
        this.values = values;
        this.context = context;
    }

    public Map<String, Value> getValues() {
        return values;
    }

    public OperationContext getContext() {
        return context;
    }

    public LENode getRoot() {
        return root;
    }

}
