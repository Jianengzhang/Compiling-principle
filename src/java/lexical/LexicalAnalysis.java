package lexical;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LexicalAnalysis {

    private static final List<String> key = Arrays.asList("begin", "if", "then", "while", "do", "end", "read", "write", "Const", "Var");
    private static final char[] charArray = {' ','\t','\n','\r','+','-','*','/','=','>','<',';',',','.','(',')','[',']','{','}'};

    public void analyse(String path) {
        int count =1;
        String arr = "";
        try {
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(path));
            char ch;
            int chr = -1;
            // is.read()方法：返回读取的字符，如果已到达流的末尾，则返回 -1
            while ((chr = is.read()) != -1) {
                ch = (char) chr;
                if (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r') {
                    if (ch == '\n') {
                        count++;
                    }
                    continue;
                } else if (isLetter(ch)) {
                    arr = "";
                    while (isLetter(ch) || isDigital(ch)) {
                        arr += ch;
                        is.mark(1);
                        ch = (char) is.read();
                    }
                    is.reset();
                    if (isKeyWord(arr)) {
                        System.out.println(count + ":" + "Reserved words:\t" + arr);
                    } else
                        System.out.println(count + ":" + "Identifier:\t" + arr);

                } else if (isDigital(ch)) {
                    arr = "";
                    while (isDigital(ch) || isDigital((char) is.read())) {
                        arr = arr + ch;
                        is.mark(1);
                        ch = (char) is.read();
                    }
                    if (!isDe(ch)) {
                        System.out.println(count + ":" + "Variable Name error\t" + ch);
                        continue;
                    }
                    is.reset();
                    System.out.println(count + ":" + "Identifier:\t" + arr);
                } else switch (ch) {
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        System.out.println(count + ":" + "Relation Operator:\t" + ch);
                        break;
                    case '(':
                    case ')':
                    case '[':
                    case ']':
                    case ';':
                    case '.':
                    case ',':
                    case '{':
                    case '}':
                        System.out.println(count + ":" + "Delimiter:\t" + ch);
                        break;
                    case '=': {
                        is.mark(1);
                        ch = (char) is.read();
                        if (ch == '=') {
                            System.out.println(count + ":" + "Relation Operator:\t" + "==");
                        } else {
                            System.out.println(count + ":" + "Relation Operator:\t" + "=");
                            is.reset();
                        }
                    }
                    break;
                    case '>': {
                        is.mark(1);
                        ch = (char) is.read();
                        if (ch == '=') {
                            System.out.println(count + ":" + "Relation Operator:\t" + ">=");
                        } else {
                            is.reset();
                            System.out.println(count + ":" + "Relation Operator:\t" + ">");
                        }
                    }
                    break;
                    case '<': {
                        is.mark(1);
                        ch = (char) is.read();
                        if (ch == '=') {
                            System.out.println(count + ":" + "Relation Operator:\t" + "<=");
                        } else if (ch == '>') {
                            System.out.println(count + ":" + "Relation Operator:\t" + "<>");
                        } else {
                            is.reset();
                            System.out.println(count + ":" + "Relation Operator:\t" + "<");
                        }
                    }
                    break;
                    default:
                        System.out.println(count + ":" + ch + "\t$无法识别字符");
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //判断是不是数字
    private boolean isDigital(char num) {
        if (num >= '0' && num <= '9') {
            return true;
        } else
            return false;
    }

    //判断是不是字母
    private  boolean isLetter(char letter) {
        if ((letter <= 'z' && letter >= 'a') || (letter <= 'Z' && letter >= 'A')) {
            return true;
        } else
            return false;
    }

    //判断是不是关键字
    private static boolean isKeyWord(String s) {
        for (String stri:key) {
            if (s.equals(stri)){
                return true;
            }

        }
        return false;
    }
    //判断是不是分界符
    private boolean isDe(char ch){
        for (char c:charArray) {
            if (ch == c){
                return true;
            }
        }
        return false;
    }

}
