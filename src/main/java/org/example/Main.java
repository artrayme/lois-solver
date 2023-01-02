package org.example;

import org.logicng.datastructures.Assignment;
import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;
import org.logicng.formulas.Literal;
import org.logicng.formulas.Variable;
import org.logicng.io.parsers.ParserException;

public class Main {
    public static void main(String[] args) throws ParserException {
        System.out.println("Hello world!");
        FormulaFactory f = new FormulaFactory();
        Variable a = f.variable("A");
        Variable b = f.variable("B");
        Literal notC = f.literal("C", false);
        Formula formula = f.and(a, f.not(f.or(b, notC)));
        var assignment = new Assignment();
        assignment.addLiteral(a.negate());
        assignment.addLiteral(b.negate());
        System.out.println(f.parse("AB"));
//        formula.apply()
    }
}