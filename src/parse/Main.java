package parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        try {
            String value, token;
            int line, pos;
            boolean found;
            List<Token> list = new ArrayList<>();
            EnumSet<TokenType> tokens = EnumSet.allOf(TokenType.class);

            String filePath = "./test2.txt";

            Scanner s = new Scanner(new File(filePath));
            while (s.hasNext()) {
                String str = s.nextLine();
                StringTokenizer st = new StringTokenizer(str);

                line = Integer.parseInt(st.nextToken());
                pos = Integer.parseInt(st.nextToken());

                token = st.nextToken();
                value = "";
                while (st.hasMoreTokens()) {
                    value += st.nextToken() + " ";
                }
                found = false;

                TokenType tokenType = TokenType.valueOf(token);
                if (tokens.contains(tokenType)) {
                    found = true;
                    list.add(new Token(tokenType, value, line, pos));
                }

                if (!found) {
                    throw new Exception("Token not found: '" + token + "'");
                }
            }

            Parser parser = new Parser(list);
            parser.printAST(parser.parse());

            System.out.println("\n\nBinary tree: ");
            String filePath2 = "./text2.txt";
            Scanner s2 = new Scanner(new File(filePath2));
            while (s2.hasNext()) {
                String str1 = s2.nextLine();
                System.out.println(str1);
            }
        } catch (FileNotFoundException e) {
            error(-1, -1, "Exception : " + e.getMessage());
        } catch (Exception e) {
            error(-1, -1, "Exception: " + e.getMessage());
        }
    }

    public static void error(int line, int pos, String msg) {
        if (line > 0 && pos > 0) {
            System.out.printf("%s in line %d, pos %d\n", msg, line, pos);
        } else {
            System.out.println(msg);
        }

        System.exit(1);
    }

}
