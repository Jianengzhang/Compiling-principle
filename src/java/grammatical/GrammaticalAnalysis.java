package grammatical;

import java.util.ArrayList;
import java.util.List;

public class GrammaticalAnalysis {
    int i = 0;
    int count = 0;
    int countTemp = 0;
    List<String> stringList = new ArrayList<>();
    String temp = "";
    String temp1= "";
    List<Grammatical> grammaticalList = new ArrayList<>();

    public List<String> analysis(List<Grammatical> grammatical) {
        System.out.println("program -> constExp + varExp + sen");
        grammaticalList = grammatical;
        if (grammaticalList.get(i).getLeft() == 10) {
            constExp();
        }
        if (grammaticalList.get(i).getLeft() == 11) {
            varExp();
        }

        for (i = i + 1; i < grammaticalList.size(); i++) {
            sen();
        }

        System.out.println("---------------------------------------------------");
        i = 0;
        count = 0;
        return stringList;
    }


    private boolean constExp() {
        count++;
        for (int j = 0; j < count; j++) {
            System.out.print("    ");
        }
        System.out.println("constExp -> Const + constDef + constRec");
        count++;
        if (grammaticalList.get(i).getLeft() == 10) {
            for (int j = 0; j < count; j++) {
                System.out.print("    ");
            }
            System.out.println("Const");
            i++;
            while (constDef()) {
                i++;
                //分号
                if (grammaticalList.get(i).getLeft() == 21) {
                    for (int j = 0; j < count; j++) {
                        System.out.print("    ");
                    }
                    System.out.println(";");
                    count = 1;
                    i++;
                    return true;
                }
                //逗号
                else if (grammaticalList.get(i).getLeft() == 22) {
                    for (int j = 0; j < count; j++) {
                        System.out.print("    ");
                    }
                    System.out.println(",");
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
        for (int j = 0; j < count; j++) {
            System.out.print("    ");
        }
        System.out.println("constDef ->  ide = uInt");
        count++;
        if (ide()) {
            i++;
            for (int j = 0; j < count; j++) {
                System.out.print("    ");
            }
            if (grammaticalList.get(i).getLeft() == 14) {
                System.out.println("=");
                i++;
                if (grammaticalList.get(i).getLeft() == 13) {
                    for (int j = 0; j < count; j++) {
                        System.out.print("    ");
                    }
                    System.out.println("uInt: " + grammaticalList.get(i).getRight());
                    count--;
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private boolean varExp() {
        for (int j = 0; j < count; j++) {
            System.out.print("    ");
        }
        System.out.println("varExp -> Var + ide + ideRec");
        if (grammaticalList.get(i).getLeft() == 11) {
            count++;
            for (int j = 0; j < count; j++) {
                System.out.print("    ");
            }
            System.out.println("Var");
            i++;
            while (ide()) {
                i++;
                if (grammaticalList.get(i).getLeft() == 21) {
                    for (int j = 0; j < count; j++) {
                        System.out.print("    ");
                    }
                    System.out.println(";");
                    count = 1;
                    return true;
                } else if (grammaticalList.get(i).getLeft() == 22) {
                    for (int j = 0; j < count; j++) {
                        System.out.print("    ");
                    }
                    System.out.println(",");
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
        //count++;
        for (int j = 0; j < count; j++) {
            System.out.print("    ");
        }
        if (grammaticalList.get(i).getLeft() == 12) {
            System.out.println("ide :" + grammaticalList.get(i).getRight());
            return true;
        } else
            return false;
    }


    private boolean sen() {
        //赋值语句
        if (grammaticalList.get(i).getLeft() == 12) {
            for (int j = 0; j < count; j++) {
                System.out.print("    ");
            }
            System.out.println("sen -> assSen");
            assSen();
            return true;
        }
        //条件语句
        else if (grammaticalList.get(i).getLeft() == 3) {
            for (int j = 0; j < count; j++) {
                System.out.print("    ");
            }
            System.out.println("sen -> conSen");
            conSen();
            return true;
        }
        //循环语句
        else if (grammaticalList.get(i).getLeft() == 5) {
            for (int j = 0; j < count; j++) {
                System.out.print("    ");
            }
            System.out.println("sen -> cirSen");
            cirSen();
            return true;
        }
        //复合语句
        else if (grammaticalList.get(i).getLeft() == 2) {
            for (int j = 0; j < count; j++) {
                System.out.print("    ");
            }
            System.out.println("sen -> comSen");
            comSen();
            return true;
        } else {
            return false;
        }
    }

    private boolean assSen() {
        temp="t"+countTemp+"=";
        temp1="";
        count++;
        for (int j = 0; j < count; j++) {
            System.out.print("    ");
        }
        System.out.println("assSen -> ide = expression");
        count++;
        for (int j = 0; j < count; j++) {
            System.out.print("    ");
        }
        System.out.println("ide: " + grammaticalList.get(i).getRight());
        temp1=grammaticalList.get(i).getRight() + "=" + "t" + countTemp;
        i++;
        if (grammaticalList.get(i).getLeft() == 14) {
            for (int j = 0; j < count; j++) {
                System.out.print("    ");
            }
            System.out.println("=");
            i++;
            expression();
            stringList.add(temp);
            stringList.add(temp1);
            if (grammaticalList.get(i).getLeft() == 21) {
                for (int j = 0; j < count; j++) {
                    System.out.print("    ");
                }
                System.out.println(";");
                count -= 2;
            }
            countTemp++;
            return true;
        } else {
            System.out.println("没有=");
            return false;
        }
    }

    private boolean expression() {
        for (int j = 0; j < count; j++) {
            System.out.print("    ");
        }
        System.out.println("expression -> addOpe + item +ItemRec");
        count++;
        do {
            if (grammaticalList.get(i).getLeft() == 23 || grammaticalList.get(i).getLeft() == 24) {
                for (int j = 0; j < count; j++) {
                    System.out.print("    ");
                }
                System.out.println(grammaticalList.get(i).getRight());
                temp += grammaticalList.get(i).getRight();
                i++;
                item();
            } else {
                item();
            }
        } while (grammaticalList.get(i).getLeft() == 23 || grammaticalList.get(i).getLeft() == 24);
        count--;
        return true;
    }

    private boolean item() {

        while (factor()) {
            i++;
            if (grammaticalList.get(i).getLeft() == 25 || grammaticalList.get(i).getLeft() == 26) {
                for (int j = 0; j < count; j++) {
                    System.out.print("    ");
                }
                System.out.println(grammaticalList.get(i).getRight());
                temp+=grammaticalList.get(i).getRight();
                i++;
            } else {
                return true;
            }

        }
        return false;
    }

    //    factor -> ide | uInt | ( expression )
    private boolean factor() {
        if (grammaticalList.get(i).getLeft() == 12) {
            for (int j = 0; j < count; j++) {
                System.out.print("    ");
            }
            System.out.println("Identifier: " + grammaticalList.get(i).getRight());
            temp+=grammaticalList.get(i).getRight();
            return true;
        } else if (grammaticalList.get(i).getLeft() == 13) {
            for (int j = 0; j < count; j++) {
                System.out.print("    ");
            }
            System.out.println("Number:" + grammaticalList.get(i).getRight());
            temp+=grammaticalList.get(i).getRight();
            return true;
        } else if (grammaticalList.get(i).getLeft() == 27) {
            for (int j = 0; j < count; j++) {
                System.out.print("    ");
            }
            System.out.println("(");
            temp+=grammaticalList.get(i).getRight();
            i++;
            expression();
            if (grammaticalList.get(i).getLeft() == 28) {
                for (int j = 0; j < count; j++) {
                    System.out.print("    ");
                }
                System.out.println(")");
                temp+=grammaticalList.get(i).getRight();
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean conSen() {
        count++;
        for (int j = 0; j < count; j++) {
            System.out.print("    ");
        }
        System.out.println("conSen -> if + condition + then + sen + conOpt");
        count++;
        for (int j = 0; j < count; j++) {
            System.out.print("    ");
        }
        System.out.println(grammaticalList.get(i).getRight());
        i++;
        if (condition()) {
            if (grammaticalList.get(i).getLeft() == 4) {
                for (int j = 0; j < count; j++) {
                    System.out.print("    ");
                }
                System.out.println("then");
                i++;
                if (sen()) {
                    if (grammaticalList.get(i).getLeft() == 1) {
                        for (int j = 0; j < count; j++) {
                            System.out.print("    ");
                        }
                        System.out.println("else");
                        i++;
                        sen();
                        count -= 2;
                        return true;
                    } else {
                        count -= 2;
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private boolean condition() {
        for (int j = 0; j < count; j++) {
            System.out.print("    ");
        }
        System.out.println("condition -> expression + relOpp + expression");
        count++;
        if (expression()) {
            if (grammaticalList.get(i).getLeft() <= 20 && grammaticalList.get(i).getLeft() >= 15) {
                for (int j = 0; j < count; j++) {
                    System.out.print("    ");
                }
                System.out.println(grammaticalList.get(i).getRight());
                i++;
                if (expression()) {
                    count--;
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private boolean cirSen() {
        i++;
        if (condition()) {
            if (grammaticalList.get(i).getLeft() == 6) {
                System.out.println("do");
                i++;
                if (sen()) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }


    private boolean comSen() {
        count++;
        for (int j = 0; j < count; j++) {
            System.out.print("    ");
        }
        System.out.println("comSen -> begin + sen + senRec +end");
        count++;
        for (int j = 0; j < count; j++) {
            System.out.print("    ");
        }
        System.out.println("begin");
        i++;
        while (sen()) {
            if (grammaticalList.get(i).getLeft() == 21) {
                i++;
                if (grammaticalList.get(i).getLeft() == 7) {
                    break;
                }
            }
        }
        if (grammaticalList.get(i).getLeft() == 7) {
            for (int j = 0; j < count; j++) {
                System.out.print("    ");
            }
            System.out.println("end");
            i++;
            count -= 2;
            return true;
        } else {
            return false;
        }
    }

}
