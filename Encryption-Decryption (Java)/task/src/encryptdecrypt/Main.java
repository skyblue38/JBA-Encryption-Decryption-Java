package encryptdecrypt;

public class Main {

    public static void main(String[] args) {
        String plainPad = "abcdefghijklmnopqrstuvwxyz";
        String cryptPad = "zyxwvutsrqponmlkjihgfedcba";
        String inText = "we found a treasure!";
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
}
