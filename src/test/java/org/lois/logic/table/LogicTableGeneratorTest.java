package org.lois.logic.table;

import org.junit.jupiter.api.Test;
import org.lois.logic.domain.Value;
import org.lois.logic.logics.FourDigitLogic;
import org.lois.logic.logics.ThreeDigitLogic;
import org.lois.logic.parser.LEParser;
import org.lois.logic.parser.exceptions.InvalidAtomicExpressionSyntaxException;
import org.lois.logic.parser.exceptions.InvalidBracketsException;
import org.lois.logic.parser.exceptions.InvalidOperatorException;
import org.lois.logic.parser.exceptions.InvalidSyntaxCharacterException;
import org.lois.logic.table.utils.FourDigitLogicStateGenerator;
import org.lois.logic.table.utils.TripletLogicStateGenerator;
import org.lois.logic.utils.BitConverterUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogicTableGeneratorTest {
    @Test
    public void generateTruthTableTripletLogic1() throws InvalidOperatorException, InvalidSyntaxCharacterException, InvalidAtomicExpressionSyntaxException, InvalidBracketsException {
        var tree = LEParser.valueOf("((◊(A∧(!A)))→(□(A∧(!A))))");
        tree.setLogicProxy(new ThreeDigitLogic());
        var tableGenerator = new LogicTableGenerator();
        var table = tableGenerator.generateTruthTable(tree, new TripletLogicStateGenerator(tree.getValues().keySet().size()));

        var results = table.stream().map(TableEntry::result).toList();
        assertEquals(results, BitConverterUtils.toTripletLogicBits(List.of(2, 0, 2)));
    }
    @Test
    public void generateTruthTableFourDigitLogic1() throws InvalidOperatorException, InvalidSyntaxCharacterException, InvalidAtomicExpressionSyntaxException, InvalidBracketsException {
        var tree = LEParser.valueOf("((◊(A∧(!A)))→(□(A∧(!A))))");
        tree.setLogicProxy(new FourDigitLogic());
        var tableGenerator = new LogicTableGenerator();
        var table = tableGenerator.generateTruthTable(tree, new FourDigitLogicStateGenerator(tree.getValues().keySet().size()));

        var results = table.stream().map(TableEntry::result).toList();
        assertEquals(results, BitConverterUtils.toFourDigitLogicBits(List.of(3, 3, 3, 3)));
    }
    @Test
    public void generateTruthTableTripletLogic2() throws InvalidOperatorException, InvalidSyntaxCharacterException, InvalidAtomicExpressionSyntaxException, InvalidBracketsException {
        var tree = LEParser.valueOf("((A∨(!A))∨(B∧(!B)))");
        tree.setLogicProxy(new ThreeDigitLogic());
        var tableGenerator = new LogicTableGenerator();
        var table = tableGenerator.generateTruthTable(tree, new TripletLogicStateGenerator(tree.getValues().keySet().size()));

        var results = table.stream().map(TableEntry::result).toList();
        assertEquals(results, BitConverterUtils.toTripletLogicBits(List.of(2, 2, 2, 1, 1, 1, 2, 2, 2)));
    }
    @Test
    public void generateTruthTableFourDigitLogic2() throws InvalidOperatorException, InvalidSyntaxCharacterException, InvalidAtomicExpressionSyntaxException, InvalidBracketsException {
        var tree = LEParser.valueOf("((A∨(!A))∨(B∧(!B)))");
        tree.setLogicProxy(new FourDigitLogic());
        var tableGenerator = new LogicTableGenerator();
        var table = tableGenerator.generateTruthTable(tree, new FourDigitLogicStateGenerator(tree.getValues().keySet().size()));

        var results = table.stream().map(TableEntry::result).toList();
        assertEquals(results, BitConverterUtils.toFourDigitLogicBits(List.of(3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3)));
    }
}