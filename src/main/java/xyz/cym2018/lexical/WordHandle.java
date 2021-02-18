package xyz.cym2018.lexical;

import java.util.ArrayList;
import java.util.List;

import static xyz.cym2018.lexical.LexicalStaticValue.operators;

/**
 * 词法分析 将语句分解为单词
 */
public abstract class WordHandle {
    public static List<Word> getWords(String expression) {
        List<Word> wordList = new ArrayList<>();
        StringBuilder num = new StringBuilder();
        char c;
        for (int i = 0; i < expression.length(); i++) {
            c = expression.charAt(i);
            if (operators.contains(c)) {
                if (num.length() > 0) {
                    wordList.add(Word.createNumber(num.toString()));
                    num = new StringBuilder();
                }
                wordList.add(Word.createOperator(c));
            } else {
                num.append(c);
            }
        }
        if (num.length() > 0) {
            wordList.add(Word.createNumber(num.toString()));
        }
        return wordList;
    }
}
