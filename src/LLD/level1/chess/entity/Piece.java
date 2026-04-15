package LLD.level1.chess.entity;

import LLD.level1.chess.service.BoardService;
import LLD.level1.chess.enums.Color;
import LLD.level1.chess.enums.PieceType;

public abstract class Piece implements Cloneable {
    protected Color color;
    protected PieceType type;
    protected boolean moved;

    public Piece(Color var1, PieceType var2) {
        this.color = var1;
        this.type = var2;
        this.moved = false;
    }

    public Color getColor() {
        return this.color;
    }

    public PieceType getType() {
        return this.type;
    }

    public boolean hasMoved() {
        return this.moved;
    }

    public void setMoved(boolean var1) {
        this.moved = var1;
    }

    public abstract String getSymbol();

    public abstract boolean isValidMove(BoardService var1, Position var2, Position var3);

    public Piece copy() {
        try {
            Piece var1 = (Piece)super.clone();
            var1.moved = this.moved;
            return var1;
        } catch (CloneNotSupportedException var2) {
            throw new RuntimeException("Clone not supported", var2);
        }
    }
}
