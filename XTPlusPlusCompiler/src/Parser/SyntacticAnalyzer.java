package Parser;

import java.util.ArrayList;
import java.util.List;

import Lexer.LexicalAnalyzer;
import Lexer.Token.Token;
import Lexer.Token.TokenTypes;

public class SyntacticAnalyzer
{
    //region Fields

    private static final int bufferSize = 12;

    private List<Token> tokenBuffer;

    private LexicalAnalyzer lexicalAnalyzer;

    private boolean eof;

    //endregion

    //region Constructor

    public SyntacticAnalyzer(LexicalAnalyzer analyzer)
    {
        lexicalAnalyzer = analyzer;
        tokenBuffer = new ArrayList<Token>();

        ReadToken();
    }

    //endregion

    //region Private Methods

    private void ReadToken()
    {
        if(tokenBuffer.size() > 0)
            tokenBuffer.remove(0);

        Token nextToken;
        while(tokenBuffer.size() < bufferSize && !eof)
        {
            nextToken = lexicalAnalyzer.GetToken();
            tokenBuffer.add(nextToken);

            if(nextToken.Type == TokenTypes.FIM)
                eof = true;
        }
    }

    private Token LookAHead(int k)
    {
        if(tokenBuffer.isEmpty())
            return null;

        if(tokenBuffer.size() < k - 1)
            return tokenBuffer.get(tokenBuffer.size() - 1);
        
        return tokenBuffer.get(k - 1);
    }

    private void MatchToken(TokenTypes type)
    {
        var nextToken = LookAHead(1);
        if(nextToken.Type == type)
            ReadToken();
        else
            System.out.println("Erro sintatico: " + nextToken);
    }

    //region Grammar Methods

    private void DeclarationList()
    {
        if(LookAHead(4).Type == TokenTypes.V)
        {
            Declaration();
            DeclarationList();
        }
        else if(LookAHead(4).Type == TokenTypes.DL)
            Declaration();
        else
            System.out.println("Erro sintatico: " + LookAHead(1));
    }

    private void Declaration()
    {
        MatchToken(TokenTypes.V);
        MatchToken(TokenTypes.DL);
        DataType();
    }

    private void DataType()
    {
        if(LookAHead(1).Type == TokenTypes.INT)
            MatchToken(TokenTypes.INT);
        else if(LookAHead(1).Type == TokenTypes.DECI)
            MatchToken(TokenTypes.DECI);
        else
            System.out.println("Erro sintatico: " + LookAHead(1));
    }

    private void ArithmeticExpression()
    {
        ExternalArithmeticTerm();
        NewArithmeticExpression();
    }

    private void NewArithmeticExpression()
    {
        if(LookAHead(1).Type == TokenTypes.OASo || LookAHead(1).Type == TokenTypes.OASub)
        {
            SumSubtractionExpression();
            NewArithmeticExpression();
        }
    }

    private void SumSubtractionExpression()
    {
        if(LookAHead(1).Value.equals("+"))
        {
            MatchToken(TokenTypes.OASo);
            ExternalArithmeticTerm();
        }
        else if(LookAHead(1).Value.equals("-"))
        {
            MatchToken(TokenTypes.OASub);
            ExternalArithmeticTerm();
        }
        else
            System.out.println("Erro sintatico: " + LookAHead(1));
    }

    //endregion

    //endregion

    //region Public Methods

    public void ReadProgram()
    {
        MatchToken(TokenTypes.DL);
        MatchToken(TokenTypes.DEC);
        DeclarationList();
        MatchToken(TokenTypes.DL);
        MatchToken(TokenTypes.ALG);
        CommandList();
    }

    //endregion
}