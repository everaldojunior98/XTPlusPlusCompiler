package Lexer;

import java.io.FileInputStream;
import java.io.InputStream;

public class LexicalFile
{
    // region Fields

    private InputStream stream;

    private static final int bufferSize = 12;
    private int[] buffer;
    private int index;

    private int bufferCode;

    // endregion

    // region Constructor

    public LexicalFile(String path)
    {
        try
        {
            stream = new FileInputStream(path);
            
            InitializeBuffer();
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);
        }
    }

    // endregion

    // region Private Methods

    private void InitializeBuffer()
    {
        buffer = new int[bufferSize * 2];
        index = 0;

        bufferCode = 2;

        GenerateFirstBuffer();
    }

    private void GenerateFirstBuffer()
    {
        if(bufferCode == 2)
        {
            bufferCode = 1;

            for(var i = 0; i < bufferSize; i++)
            {
                try
                {
                    buffer[i] = stream.read();
                    
                    if(buffer[i] == -1)
                        break;
                }
                catch (Exception e)
                {
					e.printStackTrace(System.err);
				}
            }
        }
    }

    private void GenerateSecondBuffer()
    {
        if(bufferCode == 1)
        {
            bufferCode = 2;

            for(var i = bufferSize; i < bufferSize * 2; i++)
            {
                try
                {
                    buffer[i] = stream.read();
                    
                    if(buffer[i] == -1)
                        break;
                }
                catch (Exception e)
                {
					e.printStackTrace(System.err);
				}
            }
        }
    }

    private int GetBufferCharCode()
    {
        var code = buffer[index];
        IncreaseIndex();

        return code;
    }

    // endregion

    // region Public Methods

    public void IncreaseIndex()
    {
        index++;

        if(index == bufferSize)
        {
            GenerateSecondBuffer();
        }
        else if(index == bufferSize * 2)
        {
            GenerateFirstBuffer();
            index = 0;
        }
    }

    public void DecreaseIndex()
    {
        index--;

        if(index < 0)
            index = bufferSize * 2 - 1;
    }

    public int GetCharCode()
    {
        try
        {
            return GetBufferCharCode();
        }
        catch (Exception e)
        {
            e.printStackTrace(System.err);
            return -1;
        }
    }

    //endregion
}