package dynamic_programming;

import java.util.Arrays;

public class CountPalindromicSubsequences {

    public static void main(String[] args) {
        String s = "geeksforgeeks";
        int ans = countPalindromicSubsequences(s);
        System.out.println(ans);
    }

    public static int countPalindromicSubsequences(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i=0; i<n; i++) {
            Arrays.fill(dp[i], -1);
        }
        int count = countSubsequences(0, n-1, s, n, dp);
        return count;
    }

    public static int countSubsequences(int i, int j, String s1, int n, int[][] dp) {
        if(i > j) {
            return 0;
        }
        if(i == j) {
            return 1;
        }
        if(dp[i][j] != -1) {
            return dp[i][j];
        }
        if(s1.charAt(i) == s1.charAt(j)) {
            return dp[i][j] = countSubsequences(i+1, j, s1, n, dp) + countSubsequences(i, j-1, s1, n, dp) + 1;
        }
        return dp[i][j] = countSubsequences(i+1, j, s1, n, dp) + countSubsequences(i, j-1, s1, n, dp) - countSubsequences(i+1, j-1, s1, n, dp);
    }
}
