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

            if(nextToken == null || nextToken.Type == TokenTypes.FIM)
                eof = true;

            System.out.println("Lido:  " + LookAHead(1));
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
        {
            System.out.println("Combinação: " + LookAHead(1));
            ReadToken();
        }
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
        if(LookAHead(1).Type == TokenTypes.OASo)
        {
            MatchToken(TokenTypes.OASo);
            ExternalArithmeticTerm();
        }
        else if(LookAHead(1).Type == TokenTypes.OASub)
        {
            MatchToken(TokenTypes.OASub);
            ExternalArithmeticTerm();
        }
        else
            System.out.println("Erro sintatico: " + LookAHead(1));
    }

    private void ExternalArithmeticTerm()
    {
        InternalArithmeticTerm();
        NewExternalArithmeticTerm();
    }

    private void NewExternalArithmeticTerm()
    {
        if(LookAHead(1).Type == TokenTypes.OAMult || LookAHead(1).Type == TokenTypes.OADiv)
        {
            MultDivExpression();
            NewArithmeticExpression();
        }
    }

    private void MultDivExpression()
    {
        if(LookAHead(1).Type == TokenTypes.OAMult)
        {
            MatchToken(TokenTypes.OAMult);
            InternalArithmeticTerm();
        }
        else if(LookAHead(1).Type == TokenTypes.OADiv)
        {
            MatchToken(TokenTypes.OADiv);
            InternalArithmeticTerm();
        }
        else
            System.out.println("Erro sintatico: " + LookAHead(1));
    }

    private void InternalArithmeticTerm()
    {
        if(LookAHead(1).Type == TokenTypes.Ni)
            MatchToken(TokenTypes.Ni);
        else if(LookAHead(1).Type == TokenTypes.Nd)
            MatchToken(TokenTypes.Nd);
        else if(LookAHead(1).Type == TokenTypes.V)
            MatchToken(TokenTypes.V);
        else if(LookAHead(1).Type == TokenTypes.Ap)
        {
            MatchToken(TokenTypes.Ap);
            ArithmeticExpression();
            MatchToken(TokenTypes.Fp);
        }
        else
            System.out.println("Erro sintatico: " + LookAHead(1));
    }

    private void RelationalExpression()
    {
        RelationalTerm();
        NewRelationalExpression();
    }

    private void NewRelationalExpression()
    {
        if(LookAHead(1).Type == TokenTypes.OR_E || LookAHead(1).Type == TokenTypes.OR_OU)
        {
            LogicalOperator();
            RelationalTerm();
            NewRelationalExpression();
        }
    }

    private void RelationalTerm()
    {
        if(LookAHead(1).Type == TokenTypes.Ni || LookAHead(1).Type == TokenTypes.Nd || LookAHead(1).Type == TokenTypes.V  || LookAHead(1).Type == TokenTypes.Ap)
        {
            ArithmeticExpression();
            RelationalOperator();
            ArithmeticExpression();
        }
        else
            System.out.println("Erro sintatico: " + LookAHead(1));
    }

    private void RelationalOperator()
    {
        if(LookAHead(1).Type == TokenTypes.OR_DIFERENTE)
            MatchToken(TokenTypes.OR_DIFERENTE);
        else if(LookAHead(1).Type == TokenTypes.OR_IGUAL)
            MatchToken(TokenTypes.OR_IGUAL);
        else if(LookAHead(1).Type == TokenTypes.OR_MAIOR)
            MatchToken(TokenTypes.OR_MAIOR);
        else if(LookAHead(1).Type == TokenTypes.OR_MAIOR_IGUAL)
            MatchToken(TokenTypes.OR_MAIOR_IGUAL);
            else if(LookAHead(1).Type == TokenTypes.OR_MENOR)
            MatchToken(TokenTypes.OR_MENOR);
        else if(LookAHead(1).Type == TokenTypes.OR_MENOR_IGUAL)
            MatchToken(TokenTypes.OR_MENOR_IGUAL);
        else
            System.out.println("Erro sintatico: " + LookAHead(1));
    }

    private void LogicalOperator()
    {
        if(LookAHead(1).Type == TokenTypes.OR_E)
            MatchToken(TokenTypes.OR_E);
        else if(LookAHead(1).Type == TokenTypes.OR_OU)
            MatchToken(TokenTypes.OR_OU);
        else
            System.out.println("Erro sintatico: " + LookAHead(1));
    }

    private void CommandList()
    {
        Command();
        CommandListAlternative();
    }

    private void CommandListAlternative()
    {
        var lookAHead = LookAHead(1);

        if(lookAHead == null)
            return;

        if(lookAHead.Type == TokenTypes.ARM || lookAHead.Type == TokenTypes.LER || lookAHead.Type == TokenTypes.ESC || lookAHead.Type == TokenTypes.SE || lookAHead.Type == TokenTypes.ENQ || lookAHead.Type == TokenTypes.INIB)
            CommandList();
    }

    private void Command()
    {
        if(LookAHead(1).Type == TokenTypes.ARM)
            CommandArmazenar();
        else if(LookAHead(1).Type == TokenTypes.LER)
            CommandLer();
        else if(LookAHead(1).Type == TokenTypes.ESC)
            CommandEscrever();
        else if(LookAHead(1).Type == TokenTypes.SE)
            CommandSe();
        else if(LookAHead(1).Type == TokenTypes.ENQ)
            CommandEnquanto();
        else if(LookAHead(1).Type == TokenTypes.INIB)
            CommandInicioBloco();
        else
            System.out.println("Erro sintatico: " + LookAHead(1));
    }

    private void CommandArmazenar()
    {
        MatchToken(TokenTypes.ARM);
        ArithmeticExpression();
        MatchToken(TokenTypes.EM);
        MatchToken(TokenTypes.V);
    }

    private void CommandLer()
    {
        MatchToken(TokenTypes.LER);
        MatchToken(TokenTypes.V);
    }

    private void CommandEscrever()
    {
        MatchToken(TokenTypes.ESC);
        CommandEscreverAlternative();
    }

    private void CommandEscreverAlternative()
    {
        if(LookAHead(1).Type == TokenTypes.V)
            MatchToken(TokenTypes.V);
        else if(LookAHead(1).Type == TokenTypes.C)
            MatchToken(TokenTypes.C);
        else
            System.out.println("Erro sintatico: " + LookAHead(1));
    }

    private void CommandSe()
    {
        MatchToken(TokenTypes.SE);
        RelationalExpression();
        MatchToken(TokenTypes.ENT);
        Command();
        CommandSeAlternative();
    }

    private void CommandSeAlternative()
    {
        if(LookAHead(1).Type == TokenTypes.SENAO)
        {
            MatchToken(TokenTypes.SENAO);
            Command();
        }
    }

    private void CommandEnquanto()
    {
        MatchToken(TokenTypes.ENQ);
        RelationalExpression();
        Command();
    }

    private void CommandInicioBloco()
    {
        MatchToken(TokenTypes.INIB);
        CommandList();
        MatchToken(TokenTypes.FIMB);
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