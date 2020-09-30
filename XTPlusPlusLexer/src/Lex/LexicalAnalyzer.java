package Lex;

import Lex.Token.*;

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
            //Comment
            else if(currentChar == '$')
            {
                while(nextChar != Character.LINE_SEPARATOR)
                    nextChar = (char)lexicalFile.GetCharCode();
                continue;
            }
            //Delimiter
            else if(currentChar == '#')
            {
                return new Token(TokenTypes.D, Character.toString(currentChar));
            }
            else if(currentChar == ':')
            {
                return new Token(TokenTypes.D, Character.toString(currentChar));
            }
            //Arithmetic Operators
            else if(currentChar == '+')
            {
                return new Token(TokenTypes.OA, Character.toString(currentChar));
            }
            else if(currentChar == '-' || currentChar == '–')
            {
                return new Token(TokenTypes.OA, Character.toString(currentChar));
            }
            else if(currentChar == '*')
            {
                return new Token(TokenTypes.OA, Character.toString(currentChar));
            }
            else if(currentChar == '/')
            {
                return new Token(TokenTypes.OA, Character.toString(currentChar));
            }
            else if(Character.isLetter(currentChar) || Character.isDigit(currentChar) || currentChar == ',')
            {
                currentLexeme += currentChar;

                //Keywords
                if(currentLexeme.equals("DECLARACAO"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(TokenTypes.DEC, currentLexeme);
                }
                else if(currentLexeme.equals("ALGORITMO"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(TokenTypes.ALG, currentLexeme);
                }
                else if(currentLexeme.equals("INIB"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(TokenTypes.INIB, currentLexeme);
                }
                else if(currentLexeme.equals("FIMB"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(TokenTypes.FIMB, currentLexeme);
                }
                else if(currentLexeme.equals("LER"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(TokenTypes.LER, currentLexeme);
                }
                else if(currentLexeme.equals("ESCREVER"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(TokenTypes.ESC, currentLexeme);
                }
                else if(currentLexeme.equals("SE"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(TokenTypes.SE, currentLexeme);
                }
                else if(currentLexeme.equals("ENTAO"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(TokenTypes.ENT, currentLexeme);
                }
                else if(currentLexeme.equals("ENQUANTO"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(TokenTypes.ENQ, currentLexeme);
                }
                else if(currentLexeme.equals("ARMAZENAR"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(TokenTypes.ARM, currentLexeme);
                }
                else if(currentLexeme.equals("INTEIRO"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(TokenTypes.INT, currentLexeme);
                }
                else if(currentLexeme.equals("EM"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(TokenTypes.EM, currentLexeme);
                }
                else if(currentLexeme.equals("FIM"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(TokenTypes.FIM, currentLexeme);
                }
                //Logical Operators
                else if(currentLexeme.equals("E"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(TokenTypes.OB, currentLexeme);
                }
                else if(currentLexeme.equals("OU"))
                {
                    if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar))
                        return new Token(TokenTypes.OB, currentLexeme);
                }
                else if(!Character.isLetter(nextChar) && !Character.isDigit(nextChar) && nextChar != ',')
                {
                    var lexemeArray = currentLexeme.toCharArray();

                    for(var i = 0; i < currentLexeme.length(); i++)
                    {
                        if(Character.isLetter(lexemeArray[i]))
                            return new Token(TokenTypes.V, currentLexeme);
                        else if(lexemeArray[i] == ',')
                            return new Token(TokenTypes.Nd, currentLexeme);
                    }

                    return new Token(TokenTypes.Ni, currentLexeme);
                }
            }
            //Misc
            else if(currentChar == '(')
            {
                return new Token(TokenTypes.Ap, Character.toString(currentChar));
            }
            else if(currentChar == ')')
            {
                return new Token(TokenTypes.Fp, Character.toString(currentChar));
            }
            //Relational Operators
            else if(currentChar == '=')
            {
                return new Token(TokenTypes.OR, Character.toString(currentChar));
            }
            else if(currentChar == '<')
            {
                if(nextChar == '>')
                {
                    lexicalFile.IncreaseIndex();
                    return new Token(TokenTypes.OR, "<>");
                }
                else if(nextChar == '=')
                {
                    lexicalFile.IncreaseIndex();
                    return new Token(TokenTypes.OR, "<=");
                }
                else
                    return new Token(TokenTypes.OR, Character.toString(currentChar));
            }
            else if(currentChar == '>')
            {
                if(nextChar == '=')
                {
                    lexicalFile.IncreaseIndex();
                    return new Token(TokenTypes.OR, ">=");
                }
                else
                    return new Token(TokenTypes.OR, Character.toString(currentChar));
            }
            else if(currentChar == '=')
            {
                return new Token(TokenTypes.OR, Character.toString(currentChar));
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