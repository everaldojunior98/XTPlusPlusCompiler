import Lex.LexicalAnalyzer;
import Lex.Token.*;

public class App
{
    //region Constructor

    public static void main(String[] args) throws Exception
    {
        var path = "src/Samples/Sample1.xtpp";
        var analyzer = new LexicalAnalyzer(path);

        Token token = null;
        while ((token = analyzer.GetToken()) != null)
        {
            System.out.println(token.toString());
        }
    }

    //endregion
}