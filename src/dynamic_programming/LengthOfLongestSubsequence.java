package dynamic_programming;

public class LengthOfLongestSubsequence {

    public static void main(String[] args) {
        String x = "a";
        String y = "a";
        int ans = findLengthOfLongestSubsequence(x, y);
        System.out.println(ans);
    }

    public static int findLengthOfLongestSubsequence(String x, String y) {
        int m = x.length(), n = y.length(), ans = Integer.MIN_VALUE;
        for(int i=0; i<n; i++) {
            int length = getMaxLength(x, y, 0, i, m, n);
            ans = Math.max(ans, length);
        }
        return ans;
    }

    public static int getMaxLength(String x, String y, int i, int j, int m, int n) {
        if(i >= m || j >= n) {
            return 0;
        }
        if(x.charAt(i) != y.charAt(j)) {
            return getMaxLength(x, y, i+1, j, m, n);
        }
        return getMaxLength(x, y, i+1, j+1, m, n) + 1;
    }
}
