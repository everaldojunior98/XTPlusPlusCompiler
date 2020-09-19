package Lex;

import Lex.Token.*;
import Lex.Token.Types.ArithmeticOperators;
import Lex.Token.Types.Delimiters;

public class LexicalAnalyzer
{
    //region Fields

    private LexicalFile lexicalFile;

    //endregion

    //region Constructor

    public LexicalAnalyzer(String path)
    {
        lexicalFile = new LexicalFile(path);
    }

    //endregion

    //region Public Methods

    public Token GetToken()
    {
        var index = -1;

        while ((index = lexicalFile.GetCharCode()) != -1)
        {
            var currentChar = (char)index;

            if(Character.isWhitespace(currentChar) || currentChar == Character.LINE_SEPARATOR)
            {
                continue;
            }
            //Delimiter
            else if(currentChar == '#')
            {
                return new Token(Delimiters.HASH, Character.toString(currentChar));
            }
            else if(currentChar == ':')
            {
                return new Token(Delimiters.COLON, Character.toString(currentChar));
            }
            //Arithmetic Operators
            else if(currentChar == '+')
            {
                return new Token(ArithmeticOperators.ADDITION, Character.toString(currentChar));
            }
            else if(currentChar == '-')
            {
                return new Token(ArithmeticOperators.SUBTRACTION, Character.toString(currentChar));
            }
            else if(currentChar == '*')
            {
                return new Token(ArithmeticOperators.MULTIPLICATION, Character.toString(currentChar));
            }
            else if(currentChar == '/')
            {
                return new Token(ArithmeticOperators.DIVISION, Character.toString(currentChar));
            }
        }

        return null;
    }

    //endregion
}