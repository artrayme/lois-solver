package org.lois.logic.parser.tree;

import org.lois.logic.domain.OperationContext;
import org.lois.logic.domain.OperationContextProxy;
import org.lois.logic.domain.Value;
import org.lois.logic.domain.Variable;

import java.util.Map;

public class LETree {
    private final LENode root;
    private Map<String, Variable> values;
    private OperationContextProxy context;

    public LETree(LENode root, Map<String, Variable> values, OperationContextProxy context) {
        this.root = root;
        this.values = values;
        this.context = context;
    }

    public Map<String, Variable> getValues() {
        return values;
    }

    public OperationContext getContext() {
        return context.getOriginal();
    }

    public LENode getRoot() {
        return root;
    }

    public void setValues(Map<String, Variable> values) {
        this.values = values;
    }

    public void setContext(OperationContext context) {
        this.context.setOriginal(context);
    }

    public Value compute(){
        return root.calcValue();
    }
}
