package LLD.level1.chess.entity;

import java.util.Objects;

public class Position {
    private final int row;
    private final int col;

    public Position(int var1, int var2) {
        if (var1 >= 0 && var1 <= 7 && var2 >= 0 && var2 <= 7) {
            this.row = var1;
            this.col = var2;
        } else {
            throw new IllegalArgumentException("Invalid position: row and col must be between 0 and 7");
        }
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public boolean isValid() {
        return this.row >= 0 && this.row <= 7 && this.col >= 0 && this.col <= 7;
    }

    public Position copy() {
        return new Position(this.row, this.col);
    }

    public boolean equals(Object var1) {
        if (this == var1) {
            return true;
        } else if (var1 != null && this.getClass() == var1.getClass()) {
            Position var2 = (Position)var1;
            return this.row == var2.row && this.col == var2.col;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.row, this.col});
    }

    public String toString() {
        char var1 = (char)(97 + this.col);
        int var2 = 8 - this.row;
        return "" + var1 + var2;
    }
}