package recursion;

public class Pow {

    public static void main(String[] args) {
        double val = 1.1;
        int pow = 10000;
        double power = pow(val, pow);
        System.out.println(power);
    }

    public static double pow(double val, int pow) {
        if(pow == 0) {
            return 1;
        }
        if(pow % 2 == 0) {
            return pow(val * val, pow / 2);
        }
        return val * pow(val, pow - 1);
    }
}
