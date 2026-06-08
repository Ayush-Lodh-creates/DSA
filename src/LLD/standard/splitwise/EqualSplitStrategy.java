package LLD.standard.splitwise;

import java.util.List;

public class EqualSplitStrategy implements SplitStrategy {

    @Override
    public void splitExpense(Expense expense, List<Double> splitValues) {

        List<Split> splits = expense.getSplits();
        double totalAmount = expense.getTotalAmount();
        if(totalAmount <= 0 || splits == null || splits.size() == 0) {
            throw new IllegalArgumentException("Invalid expense details");
        }
        int size = splits.size();
        for(Split split : splits) {
            split.setSplitAmount(totalAmount / size);
        }
    }
}
