package org.lois.logic.table;

import org.junit.jupiter.api.Test;
import org.lois.logic.logics.ThreeDigitLogic;
import org.lois.logic.parser.LEParser;
import org.lois.logic.parser.exceptions.InvalidAtomicExpressionSyntaxException;
import org.lois.logic.parser.exceptions.InvalidBracketsException;
import org.lois.logic.parser.exceptions.InvalidOperatorException;
import org.lois.logic.parser.exceptions.InvalidSyntaxCharacterException;
import org.lois.logic.table.utils.TripletLogicStateGenerator;

class LogicTableGeneratorTest {
    @Test
    public void generateTruthTable() throws InvalidOperatorException, InvalidSyntaxCharacterException, InvalidAtomicExpressionSyntaxException, InvalidBracketsException {
        var tree = LEParser.valueOf("((◊(A∧(!A)))→(□(A∧(!A))))");
//        tree.setLogicProxy(new FourDigitLogic());
        tree.setLogicProxy(new ThreeDigitLogic());
        var tableGenerator = new LogicTableGenerator();
        var table = tableGenerator.generateTruthTable(tree, new TripletLogicStateGenerator(tree.getValues().keySet().size()));
        System.out.println(table);
    }
}