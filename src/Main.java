import java.util.Scanner;

public class Main {

    private static int brackets, wholeLength, numOfOpened, symbolsLeft;
    private static byte[] output;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        wholeLength = sc.nextInt() * 2;

        if (wholeLength == 0)
            return;

        numOfOpened = 0;
        symbolsLeft = wholeLength;
        output = new byte[wholeLength + 1];
        output[wholeLength] = '\n';
        placeBracket();
        System.out.flush();
    }

    private static void placeBracket () {
        if (symbolsLeft == 1) {
            for (int i = 0; i < wholeLength; i++) {
                output[i] = (byte)(((brackets & (1 << i)) >> i) == 1 ? '(' : ')');
            }
            System.out.write(output, 0, wholeLength + 1);
            return;
        }
        if (numOfOpened != symbolsLeft) {
            brackets = brackets | (1 << (wholeLength - symbolsLeft)); // '(' or true
            numOfOpened += 1;
            symbolsLeft -= 1;
            placeBracket();
            numOfOpened -= 1;
            symbolsLeft += 1;
        }
        if (numOfOpened > 0 || numOfOpened == symbolsLeft) {
            brackets = brackets & ~(1 << (wholeLength - symbolsLeft)); // ')' or false
            numOfOpened -= 1;
            symbolsLeft -= 1;
            placeBracket();
            numOfOpened += 1;
            symbolsLeft += 1;
        }
    }
}