package recursion;

import java.util.*;

public class StringEquationPermutations {

    public void getAllStringPermutations(List<String> nums, Set<String> ans, String current, int val) {
        if(nums.size() == 1 && Integer.parseInt(nums.get(0)) == val) {
            ans.add(current);
            return;
        }
        for(int i=1; i<nums.size(); i++) {
            String num1 = nums.get(i-1);
            String num2 = nums.get(i);
            int n1 = Integer.parseInt(num1);
            int n2 = Integer.parseInt(num2);
            List<String> leftSublist = nums.subList(0, i-1);
            List<String> rightSubList = nums.subList(i+1, nums.size());
            List<String> listAfterAdd = new ArrayList<>(leftSublist);
            listAfterAdd.add(String.valueOf(n1+n2));
            listAfterAdd.addAll(rightSubList);
            getAllStringPermutations(listAfterAdd, ans, current.isEmpty() ? num1 + "+" + num2 : current + "+" + num2, val);
            List<String> listAfterMultiply = new ArrayList<>(leftSublist);
            listAfterMultiply.add(String.valueOf(n1*n2));
            listAfterMultiply.addAll(rightSubList);
            getAllStringPermutations(listAfterMultiply, ans, current.isEmpty() ? num1 + "*" + num2 : current + "*" + num2, val);
            List<String> listAfterConcat = new ArrayList<>(leftSublist);
            listAfterConcat.add(num1+num2);
            listAfterConcat.addAll(rightSubList);
            getAllStringPermutations(listAfterConcat, ans, current.isEmpty() ? num1 + "." + num2 : current + "." + num2, val);
        }
    }

    public List<String> getAllStringEquations(String s, int val) {
        List<String> nums = Arrays.asList(s.split(""));
        Set<String> ans = new HashSet<>();
        getAllStringPermutations(nums, ans, "", val);
        return new ArrayList<>(ans);
    }

    public static void main(String[] args) {
        StringEquationPermutations stringEquationPermutations = new StringEquationPermutations();
        List<String> ans = stringEquationPermutations.getAllStringEquations("123", 6);
        System.out.println(ans);
    }
}
