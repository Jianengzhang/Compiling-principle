## Compiling-principle
######附录A：S 语言语法的BNF 表示

     (1) <程序>→[<常量说明>][<变量说明>]<语句>
     
     (2) <常量说明>→Const <常量定义>{，<常量定义>}；
     
     (3) <常量定义>→<标识符>＝<无符号整数>
     
     (4) <无符号整数>→<数字>{<数字>}
     
     (5) <字母>→a|b|c| … |z
     
     (6) <数字>→0|1|2| … |9
     
     (7) <标识符>→<字母>{<字母>|<数字>}
     
     (8) <变量说明>→Var <标识符>{，<标识符>}；
     
     (9) <语句>→<赋值语句>|<条件语句>|<当循环语句>|<复合语句>|ε
     
     (10) <赋值语句>→<标识符>＝<表达式>;
     
     (11) <表达式>→[＋|－]<项>{<加法运算符><项>}
     
     (12) <项>→<因子>{<乘法运算符><因子>}
     
     (13) <因子>→<标识符>|<无符号整数>|‘(’<表达式>‘)’
     
     (14) <加法运算符>→＋|－
     
     (15) <乘法运算符>→* |／
     
     (16) <条件语句>→if <条件> then <语句>| if <条件> then <语句> else <语句>
     
     (17) <条件>→<表达式><关系运算符><表达式>
     
     (18) <关系运算符>→＝＝|＜＝|＜|＞|＞＝|＜＞
     
     (19) <当循环语句>→while <条件> do <语句>
     
     (20) <复合语句>→begin <语句>{；<语句>} end
     
     注：产生式中<、>括起的部分表示一个非终结符号，[、]括起的部分表示可选项，{、}括起的部分表示可重复，符号 | 表示“或”。
