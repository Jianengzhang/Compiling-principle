import lexical.LexicalAnalysis;

public class MainApplication {
    public static void main(String args[]){
        LexicalAnalysis lexicalAnalysis = new LexicalAnalysis();
        //调用词法分析器
        lexicalAnalysis.analyse();
    }
}
