package lexical;


import Grammatical.Grammatical;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LexicalAnalysis {

    private static final List<String> key = Arrays.asList("begin", "if", "then", "while", "do", "end", "read", "write", "Const", "Var");
    private static final char[] charArray = {' ','\t','\n','\r','+','-','*','/','=','>','<',';',',','.','(',')','[',']','{','}'};

    /**
     * 1、else
     * 2、begin
     * 3、if
     * 4、then
     * 5、while
     * 6、do
     * 7、end
     * 8、read
     * 9、write
     * 10、Const
     * 11、Var
     * 12、Identifier
     * 13、Number
     * 14、=
     * 15、==
     * 16、>
     * 17、>=
     * 18、<
     * 19、<=
     * 20、<>
     * 21、;
     * 22、,
     * 23、+
     * 24、-
     * 25、*
     * 26、/
     * 27、(
     * 28、)
     */
    public List<Grammatical> analyse(String path) {
        List<Grammatical> strings = new ArrayList<>();
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
                        switch (arr){
                            case "if":strings.add(new Grammatical().init(3,arr));break;
                            case "else":strings.add(new Grammatical().init(1,arr));break;
                            case "begin":strings.add(new Grammatical().init(2,arr));break;
                            case "end":strings.add(new Grammatical().init(7,arr));break;
                            case "then":strings.add(new Grammatical().init(4,arr));break;
                            case "while":strings.add(new Grammatical().init(5,arr));break;
                            case "do":strings.add(new Grammatical().init(6,arr));break;
                            case "read":strings.add(new Grammatical().init(8,arr));break;
                            case "write":strings.add(new Grammatical().init(9,arr));break;
                            case "Const":strings.add(new Grammatical().init(10,arr));break;
                            case "Var":strings.add(new Grammatical().init(11,arr));break;
                            default:break;
                        }
                        System.out.println(count + ":" + "Reserved words:\t" + arr);
                    } else{
                        System.out.println(count + ":" + "Identifier:\t" + arr);
                        strings.add(new Grammatical().init(12,arr));
                    }

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
                    System.out.println(count + ":" + "Number:\t" + arr);
                    strings.add(new Grammatical().init(13,arr));
                } else switch (ch) {
                    case '+':{
                        strings.add(new Grammatical().init(23,String.valueOf(ch)));
                        System.out.println(count + ":" + "Relation Operator:\t" + ch);
                    }break;
                    case '-':{
                        strings.add(new Grammatical().init(24,String.valueOf(ch)));
                        System.out.println(count + ":" + "Relation Operator:\t" + ch);
                    }break;
                    case '*':{
                        strings.add(new Grammatical().init(25,String.valueOf(ch)));
                        System.out.println(count + ":" + "Relation Operator:\t" + ch);
                    }break;
                    case '/':{
                        strings.add(new Grammatical().init(26,String.valueOf(ch)));
                        System.out.println(count + ":" + "Relation Operator:\t" + ch);
                    }break;
                    case '(':{
                        strings.add(new Grammatical().init(27,String.valueOf(ch)));
                        System.out.println(count + ":" + "Delimiter:\t" + ch);
                    }break;
                    case ')':{
                        strings.add(new Grammatical().init(28,String.valueOf(ch)));
                        System.out.println(count + ":" + "Delimiter:\t" + ch);
                    }break;
                    case ';':{
                        strings.add(new Grammatical().init(21,String.valueOf(ch)));
                        System.out.println(count + ":" + "Delimiter:\t" + ch);
                    }break;
                    case ',':{
                        strings.add(new Grammatical().init(22,String.valueOf(ch)));
                        System.out.println(count + ":" + "Delimiter:\t" + ch);
                    }break;
                    case '=': {
                        is.mark(1);
                        ch = (char) is.read();
                        if (ch == '=') {
                            System.out.println(count + ":" + "Relation Operator:\t" + "==");
                            strings.add(new Grammatical().init(15,"=="));
                        } else {
                            System.out.println(count + ":" + "Relation Operator:\t" + "=");
                            strings.add(new Grammatical().init(14,"="));
                            is.reset();
                        }
                    } break;
                    case '>': {
                        is.mark(1);
                        ch = (char) is.read();
                        if (ch == '=') {
                            System.out.println(count + ":" + "Relation Operator:\t" + ">=");
                            strings.add(new Grammatical().init(17,">="));
                        } else {
                            is.reset();
                            System.out.println(count + ":" + "Relation Operator:\t" + ">");
                            strings.add(new Grammatical().init(16,">"));
                        }
                    }
                    break;
                    case '<': {
                        is.mark(1);
                        ch = (char) is.read();
                        if (ch == '=') {
                            System.out.println(count + ":" + "Relation Operator:\t" + "<=");
                            strings.add(new Grammatical().init(19,"<="));
                        } else if (ch == '>') {
                            System.out.println(count + ":" + "Relation Operator:\t" + "<>");
                            strings.add(new Grammatical().init(20,"<>"));
                        } else {
                            is.reset();
                            System.out.println(count + ":" + "Relation Operator:\t" + "<");
                            strings.add(new Grammatical().init(18,"<"));
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
        return strings;
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
