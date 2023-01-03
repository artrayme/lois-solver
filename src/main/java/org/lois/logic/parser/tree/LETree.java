package org.lois.logic.parser.tree;

import lombok.Getter;
import lombok.Setter;
import org.lois.logic.domain.Logic;
import org.lois.logic.domain.LogicProxy;
import org.lois.logic.domain.Value;
import org.lois.logic.domain.Variable;

import java.util.Map;

@Getter
@Setter
public class LETree {
    private final LENode root;
    private final Map<String, Variable> values;
    private LogicProxy logicProxy;

    public LETree(LENode root, Map<String, Variable> values, LogicProxy logicProxy) {
        this.root = root;
        this.values = values;
        this.logicProxy = logicProxy;
    }

    public Value compute() {
        return root.calcValue();
    }

    public void setLogicProxy(Logic logic) {
        this.logicProxy.setOriginal(logic);
    }
}
