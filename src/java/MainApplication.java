import Grammatical.GrammaticalAnalysis;
import lexical.LexicalAnalysis;

public class MainApplication {
    public static void main(String args[]){
        //词法分析输入样例

        String lexicalPath = "D:/input.txt";
        //语法分析输入样例
        String grammaticalPath = "";
        LexicalAnalysis lexicalAnalysis = new LexicalAnalysis();
        //调用词法分析器
//        for (Grammatical grammatical:lexicalAnalysis.analyse(lexicalPath)
//             ) {
//            System.out.println(grammatical.getRight());
//
//        }

        GrammaticalAnalysis grammaticalAnalysis =new GrammaticalAnalysis();
        //调用语法分析器
        grammaticalAnalysis.analysis(lexicalAnalysis.analyse(lexicalPath));

    }
}
