package LLD.level1.chess.entity;

import LLD.level1.chess.service.BoardService;
import LLD.level1.chess.enums.Color;
import LLD.level1.chess.enums.PieceType;

public class Rook extends Piece {
    public Rook(Color var1) {
        super(var1, PieceType.ROOK);
    }

    public String getSymbol() {
        return this.color == Color.WHITE ? "♖" : "♜";
    }

    public boolean isValidMove(BoardService var1, Position var2, Position var3) {
        if (var2.equals(var3)) {
            return false;
        } else {
            int var4 = var3.getRow() - var2.getRow();
            int var5 = var3.getCol() - var2.getCol();
            if (var4 != 0 && var5 != 0) {
                return false;
            } else if (var4 == 0 && var5 == 0) {
                return false;
            } else {
                return var1.isFriendlyPiece(var3, this.color) ? false : var1.isPathClear(var2, var3);
            }
        }
    }
}