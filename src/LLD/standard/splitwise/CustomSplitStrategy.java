package LLD.standard.splitwise;

import java.util.List;

public class CustomSplitStrategy implements SplitStrategy {

    @Override
    public void splitExpense(Expense expense, List<Double> splitValues) {
        double totalAmount = 0;
        for(Double amount : splitValues) {
            totalAmount += amount;
        }
        if(totalAmount != expense.getTotalAmount() || expense.getSplits() == null ||
                expense.getSplits().isEmpty() || expense.getSplits().size() != splitValues.size()) {
            throw new IllegalArgumentException("Invalid expense details");
        }
        for(int i=0; i<expense.getSplits().size(); i++) {
            Split split = expense.getSplits().get(i);
            split.setSplitAmount(splitValues.get(i));
        }
    }
}
