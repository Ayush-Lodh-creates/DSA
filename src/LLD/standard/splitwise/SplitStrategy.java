package LLD.standard.splitwise;

import java.util.List;

public interface SplitStrategy {

    void splitExpense(Expense expense, List<Double> splitValues);
}
