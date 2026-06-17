package dynamic_programming;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        String s = "cabdba";
        String longestPalindrom = getLongestPalindrom(s);
        System.out.println(longestPalindrom);
    }

    public static String getLongestPalindrom(String s) {
        int n = s.length();
        int maxLength = 0;
        String ans = "";
        for(int i=0; i<n; i++) {
            String palindrom = checkPalindrom(i, n, s);
            if(palindrom.length() > maxLength) {
                ans = palindrom;
                maxLength = palindrom.length();
            }
        }
        return ans;
    }

    public static String checkPalindrom(int i, int n, String s) {
        String ans = "" + s.charAt(i);
        int low = i-1, high = i+1;
        while(low >= 0 && high < n && s.charAt(low) == s.charAt(high)) {
            ans = s.charAt(low) + ans + s.charAt(high);
            low--;
            high++;
        }
        return ans;
    }
}
