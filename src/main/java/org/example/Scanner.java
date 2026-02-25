package org.example;

import java.io.IOException;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.List;

public class Scanner {

    //defining a java enum TOKEN
    public enum TOKEN{ SCANEOF, ID, INTCONST, DECLARE,
        PRINT, INITIALIZE, EQUALS, NOTEQUALS, IF, THEN, ENDIF, LOOP, DO,
        ENDLOOP, CALC, PLUS}

    //member variables
    private PushbackReader pbr;
    private StringBuilder tokenBuffer;
    private List<String> reservedWords;

    //one parameter constructor with pushBackReader
    public Scanner(PushbackReader pbr) {

        this.pbr = pbr;
        this.tokenBuffer = new StringBuilder();
        this.reservedWords = new ArrayList<>();

        //populate reservedWords
        reservedWords.add("declare");
        reservedWords.add("print");
        reservedWords.add("initialize");
        reservedWords.add("if");
        reservedWords.add("then");
        reservedWords.add("endif");
        reservedWords.add("loop");
        reservedWords.add("do");
        reservedWords.add("endloop");
        reservedWords.add("calc");
    }

    //scan method to return the appropriate TOKEN enum value
    public TOKEN scan() throws IOException{

        //always clear tokenBuffer when scan starts
        tokenBuffer.setLength(0);
        String lexeme;
        int c;
        c = readNextChar();

        //while the file is not ended ignore whitespace and continue
        while (c != -1 && isWhiteSpace(c)){
            c = readNextChar();
        }

        //if the file is completed return the scanEOF
        if(c==-1){
            return TOKEN.SCANEOF;
        }

        //if + return TOKEN.PLUS
        if(c == '+'){
            tokenBuffer.append('+');
            return TOKEN.PLUS;
        }

        //if = return TOKEN.EQUALS
        if (c == '=') {
            tokenBuffer.append('=');
            return TOKEN.EQUALS;
        }

        /*
            if ! check to see if it's followed by =
            if it is then return TOKEN.NOTEQUALS
            if not then throw an Exception because there is
            no other token with ! besides !=
         */
        if (c == '!') {
            int next = readNextChar();
            if (next == '=') {
                tokenBuffer.append('!');
                tokenBuffer.append('=');
                return TOKEN.NOTEQUALS;
            }
            throw new RuntimeException("Token is Invalid");
        }

        /*
            if it is a digit add the digit and continue
            to read until the end of file or there is no
            longer a digit following
        */
        if(Character.isDigit(c)){
            tokenBuffer.append((char)c);

            int next = readNextChar();

            while(next != -1 && Character.isDigit(next)){
                tokenBuffer.append((char)next);
                next = readNextChar();
            }

            /*
                because you need to check the characters following a
                digit you overstep by one, just unread that one as long
                as it's not the end of file then return INTCONST
            */
            if (next != -1) {
                pbr.unread(next);
            }

            return TOKEN.INTCONST;
        }


        /*
            if it is a character add the character and continue
            to read until the end of file or there is no
            longer a character OR digit OR _ following (I know you didn't say
            in the PDF to add _ but in class you mentioned that it
            also is a part of variable names so I added it hope you don't mind.
        */
        if( Character.isLetter(c)){
            tokenBuffer.append((char)c);

            int next = readNextChar();

            while (next != -1 && (Character.isLetterOrDigit(next)||next == '_')){
                tokenBuffer.append((char)next);
                next = readNextChar();
            }

            /*
                once again because you need to check the characters following a
                character you overstep by one, just unread that one as long
                as it's not the end of file
            */
            if (next != -1) {
                pbr.unread(next);
            }

            //toString the StringBuilder to compare to reservedWords List
            lexeme = tokenBuffer.toString();

            //check if the String is in reservedWords, if not return TOKEN.ID
            if (reservedWords.contains(lexeme)) {
                return TOKEN.valueOf(lexeme.toUpperCase());
            }
            return TOKEN.ID;
        }


        //If TOKEN didnt start with character or digit and wasnt a specified TOKEN throw exception
        throw new RuntimeException("Unexpected character: " + (char) c);

    }

    //is whitespace method from labs in class
    private boolean isWhiteSpace(int c){
        return (c == 32) || (c == 9) || (c == 10) || (c == 13);
    }


    //readNextChar method from labs in class as well
    private int readNextChar(){

        int c;
        try {
            c = pbr.read();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    //method to get the token Buffer string
    public String getTokenBufferString() {
        return tokenBuffer.toString();
    }

}
