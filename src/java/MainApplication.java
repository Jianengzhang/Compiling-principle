import grammatical.GrammaticalAnalysis;
import intermediateCode.IntermediateCodeGeneration;
import lexical.LexicalAnalysis;

import java.util.ArrayList;
import java.util.List;

public class MainApplication {
    public static void main(String args[]){
        String lexicalPath = "D:/input.txt";
        LexicalAnalysis lexicalAnalysis = new LexicalAnalysis();
        GrammaticalAnalysis grammaticalAnalysis =new GrammaticalAnalysis();
        List<String> strings = new ArrayList<>();
        strings = grammaticalAnalysis.analysis(lexicalAnalysis.analyse(lexicalPath));
        IntermediateCodeGeneration intermediateCodeGeneration = new IntermediateCodeGeneration();
        intermediateCodeGeneration.Generation(strings);

    }
}
