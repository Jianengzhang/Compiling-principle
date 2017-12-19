import grammatical.GrammaticalAnalysis;
import intermediateCode.IntermediateCodeGeneration;
import lexical.LexicalAnalysis;
import sun.applet.Main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainApplication {
    public static void main(String args[]){
        String lexicalPath = System.getProperty("user.dir")+"/src/resourse/input.txt";
        LexicalAnalysis lexicalAnalysis = new LexicalAnalysis();
        GrammaticalAnalysis grammaticalAnalysis =new GrammaticalAnalysis();
        List<String> strings = new ArrayList<>();
        strings = grammaticalAnalysis.analysis(lexicalAnalysis.analyse(lexicalPath));
        IntermediateCodeGeneration intermediateCodeGeneration = new IntermediateCodeGeneration();
        intermediateCodeGeneration.Generation(strings);

    }
}
