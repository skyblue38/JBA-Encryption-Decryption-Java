package encryptdecrypt;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String plainPad = "abcdefghijklmnopqrstuvwxyz";
        String cryptPad;
        String inText = scanner.nextLine();  // get text to be encrypted
        //String inText = "we found a treasure!";
        int rotBy = scanner.nextInt();  // get rotation step value
        int maxRot = plainPad.length();
        if (rotBy > maxRot || rotBy < -maxRot) { rotBy %= maxRot; }
        cryptPad = rotateString(plainPad, rotBy);
        char[] outText = inText.toCharArray();

        int index;
        for (int i = 0; i < inText.length(); i++) {
            index = plainPad.indexOf(inText.charAt(i));
            if (index != -1) {
                outText[i] = cryptPad.charAt(index);
            }
        }
        System.out.println(outText);
    }

    public static String rotateString(String inStr, int r) {
        int strLen = inStr.length();
        r %= strLen;  // limit bounds
        if (r < 0) { r += strLen; }
        char[] outChar = new char[strLen];  // build rotated char array
        for (int i = 0; i < strLen; i++) {
            outChar[i] = inStr.charAt((i+r)%strLen);
        }
        String dummy = "";
        return dummy.copyValueOf(outChar, 0, strLen);
    }
}
