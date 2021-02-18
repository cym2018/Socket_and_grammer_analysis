package xyz.cym2018.lexical;

import java.io.Serializable;

/**
 * 词法分析 单词
 */
public class Word implements Serializable {
    final WordType type;
    final Integer numberValue;
    final String operatorValue;

    Word(Integer numberValue) {
        this.type = WordType.NUMBER;
        this.numberValue = numberValue;
        this.operatorValue = null;
    }

    Word(String operatorValue) {
        this.type = WordType.OPERATOR;
        this.operatorValue = operatorValue;
        this.numberValue = null;
    }

    public static Word createNumber(String number) {
        return new Word(Integer.valueOf(number));
    }

    public static Word createOperator(char operator) {
        return new Word(String.valueOf(operator));
    }

    public WordType getType() {
        return type;
    }

    public Integer getNumberValue() {
        return numberValue;
    }

    public String getOperatorValue() {
        return operatorValue;
    }

    @Override
    public String toString() {
        return numberValue == null ? operatorValue : String.valueOf(numberValue);
    }
}
