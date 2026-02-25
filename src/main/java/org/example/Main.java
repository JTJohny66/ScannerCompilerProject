package org.example;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

public class Main {
    static void main(String[] args) {

        //I added this just for fun to see it output everything
        PushbackReader pbr = new PushbackReader(new StringReader(" loop x != y do calc x = x + 1 endloop\n"));
        Scanner scanner = new Scanner(pbr);

        while (true) {
            try {
                Scanner.TOKEN token = scanner.scan();

                if (token == Scanner.TOKEN.SCANEOF) {
                    break;
                }

                System.out.println(token + ": " + scanner.getTokenBufferString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
