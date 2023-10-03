package encryptdecrypt;

//import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        String plainPad = allChars(32);
        String cryptPad;
        String outStr;
        //String eMode = scanner.nextLine();  // get encrypt or decrypt
        String eMode = getArg(args, "-mode", "enc");
        //String inText = scanner.nextLine();  // get text to be encrypted
        String inText = getArg(args, "-data", "");
        //int rotBy = scanner.nextInt();  // get rotation step value
        int rotBy = Integer.parseInt(getArg(args, "-key", "0"));
        int maxRot = plainPad.length();  // fold the rotation count
        if (rotBy > maxRot || rotBy < -maxRot) {
            rotBy %= maxRot;
        }
        cryptPad = rotateString(plainPad, rotBy);  //form encrypt and decrypt pads
        outStr = switch (eMode) {
            case "enc" -> encStr(inText, plainPad, cryptPad);
            case "dec" -> decStr(inText, plainPad, cryptPad);
            default -> "?";
        };
        System.out.println(outStr);
    }

    public static String getArg(String[] argList, String argName, String argDefault) {
        boolean keyword = false;
        String argVal = new String(argDefault);
        if (argList.length > 1) {
            for (int i = 0; i < argList.length - 1; i++) {
                if (argList[i].equals(argName)) {
                    argVal = argList[i+1];
                    break;
                }
            }
        }
        return argVal;
    }

    public static String allChars(int firstCode) {
        int cLen = 256 - firstCode;
        char[] c = new char[cLen];
        for (int i = firstCode; i < cLen; i++) {
            c[i] = (char)i;
        }
        return new String(c);
    }

    public static String rotateString(String inStr, int r) {
        int strLen = inStr.length();
        r %= strLen;  // limit bounds
        if (r < 0) { r += strLen; }
        char[] outChar = new char[strLen];  // build rotated char array
        for (int i = 0; i < strLen; i++) {
            outChar[i] = inStr.charAt((i+r)%strLen);
        }
        return new String(outChar);
    }

    // encrypt a plaintext string
    public static String encStr(String pStr, String pPad, String ePad) {
        char[] outChar = pStr.toCharArray();
        int index;
        for (int i = 0; i < pStr.length(); i++) {
            index = pPad.indexOf(pStr.charAt(i));
            if (index != -1) {
                outChar[i] = ePad.charAt(index);
            }
        }
        return new String(outChar);
    }

    // decrypt a cyphertext string returning plaintext
    public static String decStr(String cStr, String pPad, String ePad) {
        char[] outChar = cStr.toCharArray();
        int index;
        for (int i = 0; i < cStr.length(); i++) {
            index = ePad.indexOf(cStr.charAt(i));
            if (index != -1) {
                outChar[i] = pPad.charAt(index);
            }
        }
        return new String(outChar);
    }
}
