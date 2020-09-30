package Lex.Token;

public class Token
{
    // region Fields

    public TokenTypes Type;
    public String Value;

    // endregion

    // region Constructor

    public Token(TokenTypes type, String value)
    {
        Type = type;
        Value = value;
    }

    //endregion

    // region Overrides

    @Override
    public String toString()
    {
        return "<" + Type + ",\"" + Value + "\">";
    }
    
    //endregion
}