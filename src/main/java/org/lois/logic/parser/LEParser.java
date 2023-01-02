package org.lois.logic.parser;

import org.lois.logic.domain.OperationContext;
import org.lois.logic.domain.OperationContextProxy;
import org.lois.logic.domain.Value;
import org.lois.logic.domain.Variable;
import org.lois.logic.parser.exceptions.InvalidBracketsException;
import org.lois.logic.parser.tree.LENode;
import org.lois.logic.parser.tree.LETree;
import org.lois.logic.parser.exceptions.InvalidAtomicExpressionSyntaxException;
import org.lois.logic.parser.exceptions.InvalidOperatorException;
import org.lois.logic.parser.exceptions.InvalidSyntaxCharacterException;
import org.lois.logic.parser.tree.operators.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class LEParser {

    private Map<String, Variable> valueMap;
    private OperationContextProxy proxyContext;

    private LEParser() {
    }

    public static LETree valueOf(String expression) throws InvalidBracketsException, InvalidSyntaxCharacterException, InvalidAtomicExpressionSyntaxException, InvalidOperatorException {
        LEParser LEParser = new LEParser();
        if (checkBrackets(expression) == -1) {
            throw new InvalidBracketsException();
        }
        Optional<Character> invalidSymbol = checkSymbolsValidity(expression);
        if (invalidSymbol.isPresent()) {
            throw new InvalidSyntaxCharacterException(invalidSymbol.get());
        }
        LEParser.valueMap = new HashMap<>();
        LEParser.proxyContext = new OperationContextProxy();
        LETree leTree = new LETree(LEParser.parseRecursive(expression), LEParser.valueMap, LEParser.proxyContext);
        return leTree;
    }

    public static int checkBrackets(String expression) {
        if (expression.isEmpty())
            return 0;

        int maxDepth = 0;
        int counter = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (counter > maxDepth)
                maxDepth = counter;
            char c = expression.charAt(i);
            if (c == Constants.OPEN_BRACKET)
                counter++;
            if (c == Constants.CLOSE_BRACKET)
                counter--;
            if (counter < 0)
                return -1;
            if (counter == 0 && i < expression.length() - 1)
                return -1;
        }
        if (counter != 0) {
            return -1;
        }

        return maxDepth;
    }

    public static Optional<Character> checkSymbolsValidity(String expression) {
        for (char c : expression.toCharArray()) {
            if (!isAtomicExpressionSymbol(c)
                    && !isOperatorSymbol(c)
                    && c != Constants.OPEN_BRACKET
                    && c != Constants.CLOSE_BRACKET
            ) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    public static boolean checkAtomicSyntax(String expression) {
        if (expression.length() != 1)
            return false;
        return isAtomicExpressionSymbol(expression.charAt(0));
    }

    private static boolean isOperatorSymbol(Character symbol) {
        return symbol == Constants.CONJUNCTION
                || symbol == Constants.DISJUNCTION
                || symbol == Constants.NEGATION
                || symbol == Constants.EQUALITY
                || symbol == Constants.RHOMBUS
                || symbol == Constants.SQUARE
                || symbol == Constants.IMPLICIT;
    }

    private static boolean isAtomicExpressionSymbol(char symbol) {
        return ((symbol >= 'A' && symbol <= 'Z') || symbol == Constants.TRUE || symbol == Constants.FALSE);
    }

    private LENode parseRecursive(String expressionPart) throws InvalidAtomicExpressionSyntaxException, InvalidOperatorException, InvalidBracketsException {
        if (expressionPart.isEmpty())
            throw new InvalidAtomicExpressionSyntaxException(expressionPart);
        if (expressionPart.charAt(0) != Constants.OPEN_BRACKET) {
            if (!checkAtomicSyntax(expressionPart)) {
                throw new InvalidAtomicExpressionSyntaxException(expressionPart);
            }
            return buildVariableNode(expressionPart);
        }
        if (expressionPart.length() < 3)
            throw new InvalidAtomicExpressionSyntaxException(expressionPart);

        if (expressionPart.charAt(1) == Constants.NEGATION
                || expressionPart.charAt(1) == Constants.RHOMBUS
                || expressionPart.charAt(1) == Constants.SQUARE) {
            return convertToUnaryOperator(expressionPart.charAt(1), parseRecursive(expressionPart.substring(2, expressionPart.length() - 1)));
        } else if (checkBrackets(expressionPart) == 1) {
            return flatExpression(expressionPart.substring(1, expressionPart.length() - 1));
        } else {
            return deepExpression(expressionPart);
        }

    }

    private LEVariable buildVariableNode(String expressionPart) {
        if (valueMap.containsKey(expressionPart)) return new LEVariable(new Variable(valueMap.get(expressionPart).getValue()));
        else {
            var value = Value.placeholder();
            valueMap.put(expressionPart, new Variable(value));
            return new LEVariable(new Variable(value));
        }
    }

    private String getPartByRule(AtomicInteger index, String expressionPart, Predicate<Character> rule) {
        StringBuilder part = new StringBuilder();
        while (index.get() < expressionPart.length() && rule.test(expressionPart.charAt(index.get()))) {
            part.append(expressionPart.charAt(index.get()));
            index.incrementAndGet();
        }
        return part.toString();
    }

    private LENode flatExpression(String expressionPart) throws InvalidOperatorException, InvalidAtomicExpressionSyntaxException {
        AtomicInteger index = new AtomicInteger();
        String firstPart = getPartByRule(index, expressionPart, LEParser::isAtomicExpressionSymbol);
        if (!checkAtomicSyntax(firstPart)) {
            throw new InvalidAtomicExpressionSyntaxException(firstPart);
        }
        String operator = getPartByRule(index, expressionPart, e -> !isAtomicExpressionSymbol(e) && e != Constants.NEGATION);
        if (operator.length() > 1)
            throw new InvalidOperatorException(operator);

        if (index.get() >= expressionPart.length())
            throw new InvalidOperatorException(expressionPart);

        String secondPart = expressionPart.substring(index.get());
        if (!checkAtomicSyntax(secondPart)) {
            throw new InvalidAtomicExpressionSyntaxException(secondPart);
        }

        return convertToBinaryOperator(operator.charAt(0), buildVariableNode(firstPart), buildVariableNode(secondPart));

    }

    private LENode deepExpression(String expressionPart) throws InvalidOperatorException, InvalidAtomicExpressionSyntaxException, InvalidBracketsException {
        LEParsedEntity entity = splitExpression(expressionPart.substring(1, expressionPart.length() - 1));
        if (entity.getOperator().length() != 1)
            throw new InvalidOperatorException(entity.getOperator());
        return convertToBinaryOperator(entity.getOperator().charAt(0), parseRecursive(entity.getFirstPart()), parseRecursive(entity.getSecondPart()));
    }

    private LEParsedEntity splitExpression(String expression) throws InvalidOperatorException, InvalidBracketsException {
        LEParsedEntity result = new LEParsedEntity();
        StringBuilder operator = new StringBuilder();
        AtomicInteger index = new AtomicInteger(0);
        result.setFirstPart(extractExpressionPart(expression, index));
        if (index.get() == expression.length())
            throw new InvalidOperatorException(expression);
        if (expression.charAt(index.get()) == Constants.NEGATION)
            throw new InvalidOperatorException(Constants.NEGATION);

        while (LEParser.isOperatorSymbol(expression.charAt(index.get()))) {
            operator.append(expression.charAt(index.get()));
            index.incrementAndGet();
            if (index.get() == expression.length())
                throw new InvalidOperatorException(expression);
        }
        result.setOperator(operator.toString());

        result.setSecondPart(extractExpressionPart(expression, index));
        if (!(result.getFirstPart() + result.getOperator() + result.getSecondPart()).equals(expression))
            throw new InvalidOperatorException(expression.substring(index.get()));
        return result;
    }

    private String extractExpressionPart(String expression, AtomicInteger index) throws InvalidBracketsException {
        int counter = 0;
        StringBuilder part = new StringBuilder();
        do {
            char c = expression.charAt(index.get());
            if (c == Constants.OPEN_BRACKET)
                counter++;
            if (c == Constants.CLOSE_BRACKET)
                counter--;
            part.append(c);
            index.incrementAndGet();
            if (counter < 0)
                throw new InvalidBracketsException();
        } while (counter != 0);
        return part.toString();
    }

    private LENode convertToBinaryOperator(Character sign, LENode left, LENode right) throws InvalidOperatorException {
        var result = switch (sign) {
            case Constants.CONJUNCTION -> new LEConjunctionNode(proxyContext);
            case Constants.DISJUNCTION -> new LEDisjunctionNode(proxyContext);
            case Constants.EQUALITY -> new LEEqualNode(proxyContext);
            case Constants.NEGATION -> new LENegationNode(proxyContext);
            case Constants.IMPLICIT -> new LEImplicationNode(proxyContext);
            case Constants.RHOMBUS -> new LEDiamondNode(proxyContext);
            case Constants.SQUARE -> new LESquareNode(proxyContext);
            default -> throw new InvalidOperatorException(sign);
        };

        result.setLeftChild(left);
        result.setRightChild(right);
        return result;
    }
    private LENode convertToUnaryOperator(Character sign, LENode right) throws InvalidOperatorException {
        var result = switch (sign) {
            case Constants.NEGATION -> new LEConjunctionNode(proxyContext);
            case Constants.RHOMBUS -> new LEDiamondNode(proxyContext);
            case Constants.SQUARE -> new LESquareNode(proxyContext);
            default -> throw new InvalidOperatorException(sign);
        };

        result.setRightChild(right);
        return result;
    }
}
