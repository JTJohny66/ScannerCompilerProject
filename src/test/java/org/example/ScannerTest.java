package org.example;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class ScannerTest {

    @Test
    void testDeclareScore123() throws IOException {
        PushbackReader pbr = new PushbackReader(new StringReader("declare score123"));
        Scanner scanner = new Scanner(pbr);

        //checks first scan is reservedWord declare
        assertEquals(Scanner.TOKEN.DECLARE, scanner.scan());

        //checks second scan is an ID and not a reserved word
        assertEquals(Scanner.TOKEN.ID, scanner.scan());

        //calls getTokenBufferString() to see if it correctly got the ID score123
        assertEquals("score123", scanner.getTokenBufferString());

        //calls to check end of file token correctly returned
        assertEquals(Scanner.TOKEN.SCANEOF, scanner.scan());
    }

    @Test
    void testInitializeScore() throws IOException {
        PushbackReader pbr = new PushbackReader(new StringReader("initialize score = 600"));
        Scanner scanner = new Scanner(pbr);

        //calls to check first scan returns initialize reservedWord
        assertEquals(Scanner.TOKEN.INITIALIZE, scanner.scan());

        //checks that second scan is IF
        assertEquals(Scanner.TOKEN.ID, scanner.scan());

        //getTokenBufferString method to get the ID string which should be score
        assertEquals("score", scanner.getTokenBufferString());

        //checks next token is equals
        assertEquals(Scanner.TOKEN.EQUALS, scanner.scan());

        //checks next token is an Integer Constant
        assertEquals(Scanner.TOKEN.INTCONST, scanner.scan());

        //calls getTokenBufferString to get the value of the Integer COnstant should be 600
        assertEquals("600", scanner.getTokenBufferString());

        assertEquals(Scanner.TOKEN.SCANEOF, scanner.scan());
    }

    @Test
    void testCalcNewSalary() throws IOException{
        PushbackReader pbr = new PushbackReader(new StringReader("calc newsalary = originalsalary + raise"));
        Scanner scanner = new Scanner(pbr);

        //check for reverseword calc
        assertEquals(Scanner.TOKEN.CALC, scanner.scan());

        //check for ID value of newsalary
        assertEquals(Scanner.TOKEN.ID, scanner.scan());
        assertEquals("newsalary", scanner.getTokenBufferString());

        //check for equals
        assertEquals(Scanner.TOKEN.EQUALS, scanner.scan());

        //check for ID of original salary
        assertEquals(Scanner.TOKEN.ID, scanner.scan());
        assertEquals("originalsalary", scanner.getTokenBufferString());

        //check for plus
        assertEquals(Scanner.TOKEN.PLUS, scanner.scan());

        //check for ID of raise
        assertEquals(Scanner.TOKEN.ID, scanner.scan());
        assertEquals("raise", scanner.getTokenBufferString());

        //check for end of file
        assertEquals(Scanner.TOKEN.SCANEOF, scanner.scan());
    }

    @Test
    void testPrintSalary() throws IOException{
        PushbackReader pbr = new PushbackReader(new StringReader("print salary"));
        Scanner scanner = new Scanner(pbr);

        //check for reservedWord print
        assertEquals(Scanner.TOKEN.PRINT, scanner.scan());

        //check for ID salary
        assertEquals(Scanner.TOKEN.ID, scanner.scan());
        assertEquals("salary", scanner.getTokenBufferString());

        //check for end of file
        assertEquals(Scanner.TOKEN.SCANEOF, scanner.scan());
    }

    @Test
    void ifxEqualsY() throws IOException{
        PushbackReader pbr = new PushbackReader(new StringReader("if x = y then endif"));
        Scanner scanner = new Scanner(pbr);

        //check for reservedWord if
        assertEquals(Scanner.TOKEN.IF, scanner.scan());

        //check for ID x
        assertEquals(Scanner.TOKEN.ID, scanner.scan());
        assertEquals("x", scanner.getTokenBufferString());

        //check for equals
        assertEquals(Scanner.TOKEN.EQUALS, scanner.scan());

        //check for id y
        assertEquals(Scanner.TOKEN.ID, scanner.scan());
        assertEquals("y", scanner.getTokenBufferString());

        //check for reserved word THEN
        assertEquals(Scanner.TOKEN.THEN, scanner.scan());

        //check for reservedWord ENDIF
        assertEquals(Scanner.TOKEN.ENDIF, scanner.scan());

        //check for end of file
        assertEquals(Scanner.TOKEN.SCANEOF, scanner.scan());
    }

    @Test
    void ifxEqualsY2() throws IOException{
        PushbackReader pbr = new PushbackReader(new StringReader("if x = y then print x endif"));
        Scanner scanner = new Scanner(pbr);

        //check for reservedWord if
        assertEquals(Scanner.TOKEN.IF, scanner.scan());

        //check for ID x
        assertEquals(Scanner.TOKEN.ID, scanner.scan());
        assertEquals("x", scanner.getTokenBufferString());

        //check for equals
        assertEquals(Scanner.TOKEN.EQUALS, scanner.scan());

        //check for id y
        assertEquals(Scanner.TOKEN.ID, scanner.scan());
        assertEquals("y", scanner.getTokenBufferString());

        //check for reserved word THEN
        assertEquals(Scanner.TOKEN.THEN, scanner.scan());

        //check for reservedWord print
        assertEquals(Scanner.TOKEN.PRINT, scanner.scan());

        //check for ID x
        assertEquals(Scanner.TOKEN.ID, scanner.scan());
        assertEquals("x", scanner.getTokenBufferString());

        //check for reservedWord ENDIF
        assertEquals(Scanner.TOKEN.ENDIF, scanner.scan());

        //check for end of file
        assertEquals(Scanner.TOKEN.SCANEOF, scanner.scan());
    }

    @Test
    void loopX() throws IOException{
        PushbackReader pbr = new PushbackReader(new StringReader("loop x != y do calc x = x + 1 endloop"));
        Scanner scanner = new Scanner(pbr);

        //check if reservedWord LOOP
        assertEquals(Scanner.TOKEN.LOOP, scanner.scan());

        //check for ID x
        assertEquals(Scanner.TOKEN.ID, scanner.scan());
        assertEquals("x", scanner.getTokenBufferString());

        //check for reservedWord NOTEQUALS
        assertEquals(Scanner.TOKEN.NOTEQUALS, scanner.scan());

        //check for id y
        assertEquals(Scanner.TOKEN.ID, scanner.scan());
        assertEquals("y", scanner.getTokenBufferString());

        //check for reservedWord DO
        assertEquals(Scanner.TOKEN.DO, scanner.scan());

        //check for reverseword calc
        assertEquals(Scanner.TOKEN.CALC, scanner.scan());

        //check for ID x
        assertEquals(Scanner.TOKEN.ID, scanner.scan());
        assertEquals("x", scanner.getTokenBufferString());

        //check for equals
        assertEquals(Scanner.TOKEN.EQUALS, scanner.scan());

        //check for ID x
        assertEquals(Scanner.TOKEN.ID, scanner.scan());
        assertEquals("x", scanner.getTokenBufferString());

        //check for plus
        assertEquals(Scanner.TOKEN.PLUS, scanner.scan());

        //check for ID 1
        assertEquals(Scanner.TOKEN.INTCONST, scanner.scan());
        assertEquals("1", scanner.getTokenBufferString());

        //check for reservedWord ENDLOOP
        assertEquals(Scanner.TOKEN.ENDLOOP, scanner.scan());

        //check end of File
        assertEquals(Scanner.TOKEN.SCANEOF, scanner.scan());
    }

    @Test
    void empty() throws IOException{

        PushbackReader pbr = new PushbackReader(new StringReader(""));
        Scanner scanner = new Scanner(pbr);

        assertEquals(Scanner.TOKEN.SCANEOF, scanner.scan());
    }
}