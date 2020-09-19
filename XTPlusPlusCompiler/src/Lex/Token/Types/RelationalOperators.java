package Lex.Token.Types;

import Lex.Token.ITokenType;

public enum RelationalOperators implements ITokenType
{
    EQUAL,        //IGUAL
    UNEQUAL,      //DIFERENTE
    GREATER,      //MAIOR
    LESS,         //MENOR
    GREATER_EQUAL, //MAIORIGUAL	
    LESS_EQUAL     //MENORIGUAL
}