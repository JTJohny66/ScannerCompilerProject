package org.example;

import java.io.IOException;
import java.io.PushbackReader;
import java.util.List;

public class Scanner {

    public enum TOKEN{ SCANEOF, ID, INTCONST, DECLARE,
        PRINT, INITIALIZE, EQUALS, NOTEQUALS, IF, THEN, ENDIF, LOOP, DO,
        ENDLOOP, CALC, PLUS}

    private PushbackReader input;
    private StringBuilder tokenBuffer;
    private List<String> reservedWords;

    public TOKEN scan() throws IOException{


    }

}
