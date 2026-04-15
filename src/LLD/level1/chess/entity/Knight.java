package LLD.level1.chess.entity;

import LLD.level1.chess.service.BoardService;
import LLD.level1.chess.enums.Color;
import LLD.level1.chess.enums.PieceType;

public class Knight extends Piece {
    public Knight(Color var1) {
        super(var1, PieceType.KNIGHT);
    }

    public String getSymbol() {
        return this.color == Color.WHITE ? "♘" : "♞";
    }

    public boolean isValidMove(BoardService var1, Position var2, Position var3) {
        if (var2.equals(var3)) {
            return false;
        } else {
            int var4 = Math.abs(var3.getRow() - var2.getRow());
            int var5 = Math.abs(var3.getCol() - var2.getCol());
            boolean var6 = var4 == 2 && var5 == 1 || var4 == 1 && var5 == 2;
            if (!var6) {
                return false;
            } else {
                return !var1.isFriendlyPiece(var3, this.color);
            }
        }
    }
}