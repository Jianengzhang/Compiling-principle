import Grammatical.GrammaticalAnalysis;
import lexical.LexicalAnalysis;

public class MainApplication {
    public static void main(String args[]){
        LexicalAnalysis lexicalAnalysis = new LexicalAnalysis();
        //调用词法分析器
        lexicalAnalysis.analyse();
        GrammaticalAnalysis grammaticalAnalysis =new GrammaticalAnalysis();
        //调用语法分析器
        grammaticalAnalysis.analysis();
    }
}
