package dynamic_programming;

import java.util.Arrays;

public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {1, 5, 7, 10};
        int target = 12;
        int ans = getMinimumCoins(coins, target);
        System.out.println(ans);
    }

    public static int getMinimumCoins(int[] coins, int target) {
        Arrays.sort(coins);
        int n = coins.length;
        int coin = getCoins(0, n, coins, target);
        return coin;
    }

    public static int getCoins(int i, int n, int[] coins, int target) {
        if(target == 0) {
            return 0;
        }
        if(i == n) {
            return Integer.MAX_VALUE;
        }
        if(target < coins[i]) {
            return Integer.MAX_VALUE;
        }
        int move = getCoins(i+1, n, coins, target);
        int collect = getCoins(i, n, coins, target - coins[i]);
        if(collect != Integer.MAX_VALUE) {
            collect += 1;
        }
        return Math.min(move, collect);
    }
}
