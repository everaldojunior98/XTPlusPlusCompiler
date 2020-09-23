package Lex;

import Lex.Token.*;
import Lex.Token.Types.ArithmeticOperators;
import Lex.Token.Types.Delimiters;
import Lex.Token.Types.Keywords;
import Lex.Token.Types.LogicalOperators;
import Lex.Token.Types.Misc;
import Lex.Token.Types.RelationalOperators;
import Lex.Token.Types.Variables;

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
        var currentLexeme = "";
        
        while ((index = lexicalFile.GetCharCode()) != -1)
        {
            var currentChar = (char)index;
            var nextChar = (char)lexicalFile.GetCharCode();
            lexicalFile.DecreaseIndex();

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
            else if(currentChar == '-' || currentChar == '–')
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
            else if(Character.isLetter(currentChar))
            {
                currentLexeme += currentChar;

                //Keywords
                if(currentLexeme.equals("DECLARACAO"))
                {
                    if(!Character.isLetter(nextChar))
                        return new Token(Keywords.DEC, currentLexeme);
                }
                else if(currentLexeme.equals("ALGORITMO"))
                {
                    if(!Character.isLetter(nextChar))
                        return new Token(Keywords.ALG, currentLexeme);
                }
                else if(currentLexeme.equals("INICIOB"))
                {
                    if(!Character.isLetter(nextChar))
                        return new Token(Keywords.IB, currentLexeme);
                }
                else if(currentLexeme.equals("FINALB"))
                {
                    if(!Character.isLetter(nextChar))
                        return new Token(Keywords.FB, currentLexeme);
                }
                else if(currentLexeme.equals("LER"))
                {
                    if(!Character.isLetter(nextChar))
                        return new Token(Keywords.LER, currentLexeme);
                }
                else if(currentLexeme.equals("ESCREVER"))
                {
                    if(!Character.isLetter(nextChar))
                        return new Token(Keywords.ESC, currentLexeme);
                }
                else if(currentLexeme.equals("SE"))
                {
                    if(!Character.isLetter(nextChar))
                        return new Token(Keywords.SE, currentLexeme);
                }
                else if(currentLexeme.equals("ENTAO"))
                {
                    if(!Character.isLetter(nextChar))
                        return new Token(Keywords.ENT, currentLexeme);
                }
                else if(currentLexeme.equals("ENQUANTO"))
                {
                    if(!Character.isLetter(nextChar))
                        return new Token(Keywords.ENQ, currentLexeme);
                }
                else if(currentLexeme.equals("ARMAZENAR"))
                {
                    if(!Character.isLetter(nextChar))
                        return new Token(Keywords.ARM, currentLexeme);
                }
                else if(currentLexeme.equals("INTEIRO"))
                {
                    if(!Character.isLetter(nextChar))
                        return new Token(Keywords.INT, currentLexeme);
                }
                else if(currentLexeme.equals("EM"))
                {
                    if(!Character.isLetter(nextChar))
                        return new Token(Keywords.EM, currentLexeme);
                }
                else if(currentLexeme.equals("FIM"))
                {
                    if(!Character.isLetter(nextChar))
                        return new Token(Keywords.FIM, currentLexeme);
                }
                //Logical Operators
                else if(currentLexeme.equals("E"))
                {
                    if(!Character.isLetter(nextChar))
                        return new Token(LogicalOperators.AND, currentLexeme);
                }
                else if(currentLexeme.equals("OU"))
                {
                    if(!Character.isLetter(nextChar))
                        return new Token(LogicalOperators.OR, currentLexeme);
                }
                else if(!Character.isLetter(nextChar))
                {
                    return new Token(Variables.VARIABLE, currentLexeme);
                }
            }
            //Misc
            else if(currentChar == '(')
            {
                return new Token(Misc.OPEN_PARENTHESIS, Character.toString(currentChar));
            }
            else if(currentChar == ')')
            {
                return new Token(Misc.CLOSE_PARENTHESIS, Character.toString(currentChar));
            }
            //Relational Operators
            else if(currentChar == '=')
            {
                return new Token(RelationalOperators.EQUAL, Character.toString(currentChar));
            }
            else if(currentChar == '<')
            {
                if(nextChar == '>')
                    return new Token(RelationalOperators.UNEQUAL, "<>");
                else if(nextChar == '=')
                    return new Token(RelationalOperators.LESS_EQUAL, "<=");
                else
                    return new Token(RelationalOperators.LESS, Character.toString(currentChar));
            }
            else if(currentChar == '>')
            {
                if(nextChar == '=')
                    return new Token(RelationalOperators.GREATER_EQUAL, ">=");
                else
                    return new Token(RelationalOperators.GREATER, Character.toString(currentChar));
            }
            else if(currentChar == '=')
            {
                return new Token(RelationalOperators.EQUAL, Character.toString(currentChar));
            }
            else
            {
                currentLexeme = "";
            }
        }

        return null;
    }

    //endregion
}