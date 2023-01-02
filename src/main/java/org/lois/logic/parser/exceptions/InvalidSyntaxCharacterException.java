package org.lois.logic.parser.exceptions;

public class InvalidSyntaxCharacterException extends Exception {
    private final char invalidCharacter;

    public InvalidSyntaxCharacterException(Character invalidChar) {
        super("You cannot use '" + invalidChar + "' character");
        invalidCharacter = invalidChar;
    }

    public InvalidSyntaxCharacterException(String customMessage, Character invalidChar) {
        super(customMessage);
        invalidCharacter = invalidChar;
    }

    public char getInvalidCharacter() {
        return invalidCharacter;
    }
}
