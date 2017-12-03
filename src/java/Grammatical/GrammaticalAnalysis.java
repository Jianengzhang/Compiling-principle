package Grammatical;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class GrammaticalAnalysis {
    private final static List<String> key = Arrays.asList("begin", "if", "then", "else", "while", "do", "Const", "Var", "end");
    int syn;//存放单词的类型
    char program[] = new char[200];
    char token[] = new char[20];
    int sum;

    public void analysis(String path) {
        try {
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(path));
        } catch (FileNotFoundException e) {
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
    private boolean isLetter(char letter) {
        if ((letter <= 'z' && letter >= 'a') || (letter <= 'Z' && letter >= 'A')) {
            return true;
        } else
            return false;
    }

    private void Scanner() {
    }

    //判断是不是常量定义
    private boolean Constan_Defined() {
        if (syn == 10) {
            System.out.println("常量定义" + token);
            Scanner();
            if (syn == 16) {
                System.out.println("等于" + token);
                Scanner();
                if (syn == 11) {
                    System.out.println("无符号整数" + sum);
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private boolean Constan_Description() {
        Scanner();
        if (syn == 7) {
            System.out.println("产量说明" + token);
            Scanner();
            while (Constan_Defined()) {
                Scanner();
                if (syn == 23) {
                    System.out.println("分号" + token);
                    return true;
                } else if (syn == 26) {
                    System.out.println("逗号" + token);
                    Scanner();
                    continue;
                }
                System.out.println("常量说明错误");
            }
        } else
            return false;
        return false;
    }

    //变量定义
    private boolean Variable_Defined() {
        if (syn == 10) {
            System.out.println("变量定义" + token);
            return true;
        } else
            return false;
    }

    //变量描述
    private boolean Variable_Description() {
        if (syn == 10 || syn == 2 || syn == 5 || syn == 1 || syn == 0) {
            return true;
        }
        Scanner();
        if (syn == 8) {
            System.out.println("变量说明" + token);
            Scanner();
            while (Variable_Defined()) {
                Scanner();
                if (syn == 23) {
                    System.out.println("分号" + token);
                    return true;
                } else if (syn == 26) {
                    System.out.println("逗号" + token);
                    Scanner();
                    continue;
                }

            }
        }
        return false;
    }


    boolean Condition() {
        System.out.println("条件");
        Expression();
        if (syn == 17 || syn == 18 || syn == 19 || syn == 20 || syn == 21
                || syn == 22) {
            System.out.println("关系运算符" + token);
            Scanner();
        } else {
            System.out.println("关系运算符错误");
            return false;
        }
        Expression();
        return false;
    }

    boolean Expression() {
        System.out.println("表达式");
        //Scanner();
        do {
            if (syn == 12 || syn == 13) {
                System.out.println("加法运算符" + token);
                Scanner();
                Item_expression();
            } else {
                Item_expression();
            }
        } while (syn == 12 || syn == 13);
        return true;
    }

    boolean Item_expression() {
        System.out.println("项");
        while (Factor()) {
            //Scanner();
            if (syn == 14 || syn == 15) {
                System.out.println("乘法运算符" + token);
                Scanner();
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean Factor() {
        System.out.println("因子");
        //Scanner();
        if (syn == 10) {
            System.out.println("标识符");
            Scanner();//特殊
            return true;
        } else if (syn == 11) {
            System.out.println("无符号数字");
            Scanner();
            return true;
        } else if (syn == 24) {
            System.out.println("左括号");
            Scanner();
            Expression();
            if (syn == 25) {
                System.out.println("右括号");
                Scanner();
                return true;
            } else {
                System.out.println( "没有），错误");
                return false;
            }
        } else {
            System.out.println( "没有(");
            return false;
        }
    }

    boolean Assignment_statement() {
        System.out.println("赋值语句");
        System.out.println("标识符" + token);
        Scanner();
        if (syn == 16) {
            System.out.println( "赋值语句=");
            Scanner();
            Expression();
            return true;
        } else {
            System.out.println("没有=");
            return false;
        }
    }

    boolean Compound_statements() {
        System.out.println("复合语句" + token);
        Scanner();
        while (Statement()) {
            if (syn == 23) {
                System.out.println("复合语句中的分割符" + token);
                Scanner();
                if (syn == 9) {
                    //cout<<"复合语句"<<token<<endl;
                    break;
                }
            }
        }
        if (syn == 9) {
            System.out.println("复合语句" + token);
            Scanner();
            return true;
        } else {
            System.out.println("复合语句缺乏");
            return false;
        }
    }


    private boolean Conditional_statements() {
        if (syn == 2) {
            System.out.println("条件语句if");
            Scanner();
            Condition();
            if (syn == 3) {
                System.out.println("then");
                Scanner();
                Statement();
                if (syn == 4) {
                    Scanner();
                    Statement();
                } else {
                    return true;
                }
            } else {
                System.out.println("条件语句中缺少 then");
                return false;
            }
        } else {
            return false;
        }
        return false;
    }

    private boolean While_Statement() {
        System.out.println("循环语句");
        Scanner();

        Condition();
        if (syn == 6) {
            System.out.println("while循环的do");
            Scanner();
            Statement();
            return true;
        } else
            return false;
    }

    private boolean Statement() {
        if (syn == 10) {
            Assignment_statement();
            return true;
        } else if (syn == 5) {
            While_Statement();
            return true;
        } else if (syn == 1) {
            Compound_statements();
            return true;
        } else if (syn == 2) {
            Conditional_statements();
            return true;
        } else {
            return false;
        }
    }


}
