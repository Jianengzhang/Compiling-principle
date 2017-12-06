package Grammatical;

import java.util.ArrayList;
import java.util.List;

public class GrammaticalAnalysis {
    int i = 0;
    List<Grammatical> grammaticalList = new ArrayList<>();

    public void analysis(List<Grammatical> grammatical) {
        grammaticalList = grammatical;
        constExp();
        varExp();
        for (; i < grammaticalList.size(); i++) {
            statement();
        }
    }

    private boolean statement() {
        //赋值语句
        if (grammaticalList.get(i).getLeft() == 12) {
            assSen();
            return true;
        }
        //条件语句
        else if (grammaticalList.get(i).getLeft() == 3) {
            conExp();
            return true;
        }
        //循环语句
        else if (grammaticalList.get(i).getLeft() == 5) {
            cirSen();
            return true;
        }
        //复合语句
        else if (grammaticalList.get(i).getLeft() == 2) {
            comSen();
            return true;
        } else {
            return false;
        }
    }


    private boolean constExp() {
        if (grammaticalList.get(i).getLeft() == 10) {
            System.out.println("Const");
            i++;
            while (constDef()) {
                i++;
                //分号
                if (grammaticalList.get(i).getLeft() == 21) {
                    return true;
                }
                //逗号
                else if (grammaticalList.get(i).getLeft() == 22) {
                    System.out.println("逗号");
                    i++;
                    continue;
                }
                System.out.println("常量说明错误");
            }
        } else {
            return false;
        }
        return false;
    }

    private boolean constDef() {
        if (grammaticalList.get(i).getLeft() == 12) {
            System.out.println("Identifier :" + grammaticalList.get(i).getRight());
            i++;
            if (grammaticalList.get(i).getLeft() == 14) {
                System.out.println("=");
                i++;
                if (grammaticalList.get(i).getLeft() == 13) {
                    System.out.println("无符号整数" + grammaticalList.get(i).getRight());
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private boolean varExp() {
        i++;
        if (grammaticalList.get(i).getLeft() == 11) {
            System.out.println("Var");
            i++;
            while (ide()) {
                i++;
                if (grammaticalList.get(i).getLeft() == 21) {
                    System.out.println("分号");
                    return true;
                } else if (grammaticalList.get(i).getLeft() == 22) {
                    System.out.println("逗号");
                    i++;
                    continue;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private boolean ide() {
        if (grammaticalList.get(i).getLeft() == 12) {
            System.out.println(grammaticalList.get(i).getRight());
            return true;
        } else
            return false;
    }

    private boolean assSen() {
        i++;
        if (grammaticalList.get(i).getLeft() == 14) {
            System.out.println("=");
            i++;
            expression();
            return true;
        } else {
            System.out.println("没有=");
            return false;
        }
    }

    private boolean expression() {
        do {
            if (grammaticalList.get(i).getLeft() == 23 || grammaticalList.get(i).getLeft() == 24) {
                System.out.println("加法运算符");
                i++;
                item();
            } else {
                item();
            }

        } while (grammaticalList.get(i).getLeft() == 23 || grammaticalList.get(i).getLeft() == 24);
        return true;
    }

    private boolean item() {
        while (factor()) {
            if (grammaticalList.get(i).getLeft() == 25 || grammaticalList.get(i).getLeft() == 26){
                System.out.println("乘法运算符");
                i++;
            }else {
                return true;
            }

        }
        return false;
    }

//    factor -> ide | uInt | ( expression )
    private boolean factor() {
        if(grammaticalList.get(i).getLeft() == 12){
            System.out.println("Identifier");
            return true;
        }else if (grammaticalList.get(i).getLeft() == 13){
            System.out.println("Number");
            return true;
        }else if(grammaticalList.get(i).getLeft() == 27){
            System.out.println("(");
            i++;
            expression();
            if (grammaticalList.get(i).getLeft() == 28){
                System.out.println(")");
                return true;
            }
            return false;
        }
        return false;
    }

    private void cirSen() {

    }

    private void conExp() {
    }


    private void comSen() {

    }

}
