package dynamic_programming;

public class DecodeWays {

    public static void main(String[] args) {
        String s = "226";
        int ans = decodeWays(s);
        System.out.println(ans);
    }

    public static int decodeWays(String s) {
        int n = s.length();
        return dfs(0, n, s);
    }

    public static int dfs(int i, int n, String s) {
        if(i == n) {
            return 1;
        }
        if(s.charAt(i) == '0') {
            return 0;
        }
        int take = dfs(i+1, n, s);
        if(i != n-1) {
            String numberOfLengthTwo = s.substring(i, i + 2);
            if (possibleNumber(numberOfLengthTwo)) {
                take += dfs(i + 2, n, s);
            }
        }
        return take;
    }

    public static boolean possibleNumber(String number) {
        int num = Integer.parseInt(number);
        return num >= 10 && num <= 26;
    }
}
