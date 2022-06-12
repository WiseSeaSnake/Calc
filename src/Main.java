import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

class Main {
    private static String[] zOper = {"\\+", "-", "\\*", "/"};
    private static String[] latN = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};
    private static int a = -1, b = -1;
    private static int typeV = 0, mode = -1;
    private static String[] retval = null;

    private static int getLat(String a) {
        int i = 0;
        int r = -1;
        while (r == -1 & i < latN.length) {
            if (latN[i].equals(a)) {
                r = i;
            } else {
                i++;
            }
        }
        if (r == -1) throw new NumberFormatException();
        return r;
    }

    private static boolean parseV() {
        try {
             a = Integer.parseInt(retval[0]);
            typeV = 1;
            try {
                b = Integer.parseInt(retval[1].trim());
            }
            catch (NumberFormatException nfe) {
                  return false;
            }
        }
        catch (NumberFormatException nfe) {
            a = getLat(retval[0]);
            if ((a > 0) & (a < 11)) {
                typeV = 2;
                b = getLat(retval[1]);
            }
        }
        if ((a > 0) & (a < 11) & (b > 0) & (b < 11))
            return true;
        else
            return false;
    }



    private static int didIt() {
        int res = 0;
        switch(mode) {
            case 0: return a + b;
            case 1: return a - b;
            case 2:  return a * b;
            case 3:  return a / b;
        }
       return -1;

    }


    public static String calc(String input) {
    try {
        typeV = 0;
        int nOp = 0;
        while (mode == -1 & nOp < 4) {
            retval = input.split(zOper[nOp]);
            if (retval.length == 2) {
                mode = nOp;
                if (parseV()) {
                    int result = didIt();
                    if (typeV == 1) return String.valueOf(result);
                    if (typeV == 2 & result>0 ) return latN[result];
                                           else   return "throws Exception";
                } else throw new NumberFormatException();
            } else {
                nOp++;
            }
        }
        if (nOp>3) throw new NumberFormatException();
        return "";
    }
    catch (NumberFormatException nfe)
    {
        return "throws Exception";
    }

    }


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input : ");
        String inStr = in.nextLine();
        inStr = inStr.replaceAll(" ", "").toUpperCase();
        System.out.println("Output : " + calc(inStr));
        //     int n1=0,n2=0;


    }
}