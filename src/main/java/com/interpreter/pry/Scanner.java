package com.interpreter.pry;

import java.util.ArrayList;
import java.util.List;

import static com.interpreter.pry.TokenType.*;

public class Scanner {
    private final String sourceCode;
    private final List<Token> tokens = new ArrayList<Token>();
    private int start = 0;
    private int current = 0;
    private int line = 1;

    Scanner(String src) {
        this.sourceCode = src;
    }

    List<Token> scanTokens() {
        while (!isAtEnd()) {
            // We are at the beginning of the next lexeme.
            start = current;
            scanToken();
        }
        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }

    private void scanToken() {
        char c = advance();
        switch (c) {
            case '(':
                addToken(LEFT_PAREN);
                break;
            case ')':
                addToken(RIGHT_PAREN);
                break;
            case '{':
                addToken(LEFT_BRACE);
                break;
            case '}':
                addToken(RIGHT_BRACE);
                break;
            case ',':
                addToken(COMMA);
                break;
            case '.':
                addToken(DOT);
                break;
            case '-':
                addToken(MINUS);
                break;
            case '+':
                addToken(PLUS);
                break;
            case ';':
                addToken(SEMICOLON);
                break;
            case '*':
                addToken(STAR);
                break;
            case '!':
                addToken(match('=') ? BANG_EQUAL : BANG);
                break;
            case '=':
                addToken(match('=') ? EQUAL_EQUAL : EQUAL);
                break;
            case '<':
                addToken(match('=') ? LESS_EQUAL : LESS);
                break;
            case '>':
                addToken(match('=') ? GREATER_EQUAL : GREATER);
                break;

            // Matching a slash for division or a double slash for a comment
            case '/':
                if (match('/')) {
                    while (peek() != '\n' && !isAtEnd()) advance();

                } else {
                    addToken(SLASH);
                }
                break;
            case '?':
                addToken(QUESTION_MARK);
                break;
            case ':':
                addToken(COLON);
                break;
            //If you find a double quotes that means we are about to start a string
            case '"':
                consumeString();
                break;
            case ' ':
            case '\r':
            case '\t':
                // Ignore whitespace.
                break;
            case '\n':
                line++;
                break;
            default:
                if (isDigit(c)) {
                    consumeNumber();
                } else if (isAlpha(c)) {
                    consumeIdentifier();
                } else {
                    Pry.error(line, "Unexpected character.");
                }
                break;
        }
    }

    private char peek() {
        if (isAtEnd()) return '\0';
        return sourceCode.charAt(current);
    }

    private char peekNext() {
        if (current + 1 >= sourceCode.length()) return '\0';
        return sourceCode.charAt(current + 1);

    }

    private char advance() {
        current++;
        return sourceCode.charAt(current - 1);
    }

    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal) {
        String text = sourceCode.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }

    private boolean match(char expected) {
        if (isAtEnd()) return false;
        if (sourceCode.charAt(current) != expected) return false;
        current++;
        return true;
    }

    private boolean isAtEnd() {
        return current >= sourceCode.length();
    }

    //TODO: Strings cannot be multi line , if you want multi line then use triple double Quotes to begin and triple to end
    private void consumeString() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') line++;
            advance();
        }
        if (isAtEnd()) {
            Pry.error(line, "Unterminated String literal");
        }
        //Consume the terminating double quotes
        advance();
        String value = sourceCode.substring(start + 1, current - 1);
        addToken(STRING, value);
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isAlpha(char c) {
        return (c >= 'A' && c <= 'Z') ||
                c == '_' ||
                (c >= 'a' && c <= 'z');
    }

    private void consumeNumber() {
        while (isDigit(peek())) advance();
        if (peek() == '.' && isDigit(peekNext())) {
            // Consume the "."
            advance();
            while (isDigit(peek())) advance();
        }
        addToken(NUMBER, Double.parseDouble(sourceCode.substring(start, current)));
    }

    private void consumeIdentifier() {
        while (isAlpha(peek()) || isDigit(peek())) advance();
        String identifier = sourceCode.substring(start, current);
        if (TokenType.getTokenType(identifier) == null) {
            addToken(IDENTIFIER);
        } else {
            addToken(TokenType.getTokenType(identifier));
        }

    }
}
