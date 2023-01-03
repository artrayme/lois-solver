package org.lois.logic.table;

import org.lois.logic.domain.Value;
import org.lois.logic.domain.Variable;
import org.lois.logic.parser.tree.LETree;
import org.lois.logic.table.utils.FourDigitLogicStateGenerator;
import org.lois.logic.table.utils.StateGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LogicTableGenerator {

    public List<TableEntry> generateTruthTable(LETree tree, StateGenerator stateGenerator){
        var letters = tree.getValues().keySet();
        List<TableEntry> result = new ArrayList<>();
        do{
            fillValues(tree, stateGenerator, letters, result);
            stateGenerator.incrementAndGet();
        } while (stateGenerator.hasNext());
        fillValues(tree, stateGenerator, letters, result);
        return result;
    }

    private void fillValues(LETree tree, StateGenerator stateGenerator, Set<String> letters, List<TableEntry> result) {
        int i = 0;
        for (String letter : letters) {
            tree.getValues().get(letter).setValue(stateGenerator.getCurrent().get(i++));
        }
        var input = flatten(tree.getValues());
        result.add(new TableEntry(input, tree.compute()));
    }

    private Map<String, Value> flatten(Map<String, Variable> variableByLiteral) {
        return variableByLiteral.entrySet().stream()
                .map(e -> Map.entry(e.getKey(), e.getValue().getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
