package LLD.standard.splitwise;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        User u1 = UserFactory.createUser("normal", "Alice");
        User u2 = UserFactory.createUser("premium", "Bob");
        User u3 = UserFactory.createUser("normal", "Charlie");

        Split s1 = new Split(u1);
        Split s2 = new Split(u2);
        Split s3 = new Split(u3);

        List<Split> splits = new ArrayList<>();
        splits.add(s1);
        splits.add(s2);
        splits.add(s3);

        SplitStrategy splitStrategy = new EqualSplitStrategy();

        Expense expense = new Expense(UUID.randomUUID().toString(), u1, 300, splits);

        splitStrategy.splitExpense(expense, Collections.emptyList());

        BalanceSheet balanceSheet = new BalanceSheet();

        for(Split split : splits) {
            if(expense.getLender().getId().equals(split.getUser().getId())) {
                continue;
            }
            balanceSheet.updateExpense(split.getSplitAmount(), expense.getLender(), split.getUser());
        }

        System.out.println("Balance Sheet: \n");
        balanceSheet.showBalance(u1);
        balanceSheet.showBalance(u2);
        balanceSheet.showBalance(u3);
    }
}
