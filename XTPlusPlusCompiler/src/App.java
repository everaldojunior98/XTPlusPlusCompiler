import Lexer.LexicalAnalyzer;
import Parser.SyntacticAnalyzer;

public class App
{
    //region Constructor

    public static void main(String[] args) throws Exception
    {
        var path = "src/Samples/Sample1.xtpp";
        var analyzer = new LexicalAnalyzer(path);
        var parser = new SyntacticAnalyzer(analyzer);
        parser.ReadProgram();
    }

    //endregion
}