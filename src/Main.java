import dynamic_programming.JumpGameV;

public class Main {

    public static void main(String[] args) {
        int[] arr = {2,4,4,1,2,3,6,1};
        JumpGameV jumpGameV = new JumpGameV();
        System.out.println(jumpGameV.minCostToCrossArray(arr, arr.length));
    }
}
