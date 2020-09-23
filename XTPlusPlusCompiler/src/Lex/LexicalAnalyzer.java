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
            else if(Character.isLetter(currentChar) || Character.isDigit(currentChar) || currentChar == ',')
            {
                currentLexeme += currentChar;

                //Keywords
                if(currentLexeme.equals("DECLARACAO"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(Keywords.DECLARACAO, currentLexeme);
                }
                else if(currentLexeme.equals("ALGORITMO"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(Keywords.ALGORITMO, currentLexeme);
                }
                else if(currentLexeme.equals("INIB"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(Keywords.INICIOB, currentLexeme);
                }
                else if(currentLexeme.equals("FIMB"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(Keywords.FINALB, currentLexeme);
                }
                else if(currentLexeme.equals("LER"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(Keywords.LER, currentLexeme);
                }
                else if(currentLexeme.equals("ESCREVER"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(Keywords.ESCREVER, currentLexeme);
                }
                else if(currentLexeme.equals("SE"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(Keywords.SE, currentLexeme);
                }
                else if(currentLexeme.equals("ENTAO"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(Keywords.ENTAO, currentLexeme);
                }
                else if(currentLexeme.equals("ENQUANTO"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(Keywords.ENQUANTO, currentLexeme);
                }
                else if(currentLexeme.equals("ARMAZENAR"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(Keywords.ARMAZENAR, currentLexeme);
                }
                else if(currentLexeme.equals("INTEIRO"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(Keywords.INTEIRO, currentLexeme);
                }
                else if(currentLexeme.equals("EM"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(Keywords.EM, currentLexeme);
                }
                else if(currentLexeme.equals("FIM"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(Keywords.FIM, currentLexeme);
                }
                //Logical Operators
                else if(currentLexeme.equals("E"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(LogicalOperators.AND, currentLexeme);
                }
                else if(currentLexeme.equals("OU"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(LogicalOperators.OR, currentLexeme);
                }
                else if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar) && nextChar != ',')
                {
                    var lexemeArray = currentLexeme.toCharArray();

                    for(var i = 0; i < currentLexeme.length(); i++)
                    {
                        if(Character.isLetter(lexemeArray[i]))
                            return new Token(Variables.VARIABLE, currentLexeme);
                        else if(lexemeArray[i] == ',')
                        return new Token(Variables.NUMBER_FLOAT, currentLexeme);
                    }

                    return new Token(Variables.NUMBER_INTEGER, currentLexeme);
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