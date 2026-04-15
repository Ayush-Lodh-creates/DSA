package LLD.level1.chess.entity;

import LLD.level1.chess.service.BoardService;
import LLD.level1.chess.enums.Color;
import LLD.level1.chess.enums.PieceType;

public class Pawn extends Piece {
    public Pawn(Color var1) {
        super(var1, PieceType.PAWN);
    }

    public String getSymbol() {
        return this.color == Color.WHITE ? "♙" : "♟";
    }

    public boolean isValidMove(BoardService var1, Position var2, Position var3) {
        if (var2.equals(var3)) {
            return false;
        } else {
            int var4 = var3.getRow() - var2.getRow();
            int var5 = Math.abs(var3.getCol() - var2.getCol());
            int var6 = this.color == Color.WHITE ? -1 : 1;
            if (var4 != var6 && var4 != 2 * var6) {
                return false;
            } else if (Math.abs(var4) != 2) {
                if (var5 == 0) {
                    return var1.isEmpty(var3);
                } else {
                    return var5 == 1 && Math.abs(var4) == 1 ? var1.isEnemyPiece(var3, this.color) : false;
                }
            } else if (!this.moved && var5 == 0) {
                return var1.isEmpty(new Position(var2.getRow() + var6, var2.getCol())) && var1.isEmpty(var3);
            } else {
                return false;
            }
        }
    }
}