package LLD.level1.chess.entity;

import java.util.Objects;

public class Move {
    private final Position from;
    private final Position to;
    private final Piece movedPiece;
    private final Piece capturedPiece;

    public Move(Position var1, Position var2, Piece var3, Piece var4) {
        this.from = var1;
        this.to = var2;
        this.movedPiece = var3;
        this.capturedPiece = var4;
    }

    public Position getFrom() {
        return this.from;
    }

    public Position getTo() {
        return this.to;
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    public Piece getCapturedPiece() {
        return this.capturedPiece;
    }

    public boolean isCapture() {
        return this.capturedPiece != null;
    }

    public String toString() {
        String var10000 = String.valueOf(this.from);
        return var10000 + " -> " + String.valueOf(this.to) + (this.isCapture() ? " (captured " + String.valueOf(this.capturedPiece.getType()) + ")" : "");
    }

    public boolean equals(Object var1) {
        if (this == var1) {
            return true;
        } else if (var1 != null && this.getClass() == var1.getClass()) {
            Move var2 = (Move)var1;
            return Objects.equals(this.from, var2.from) && Objects.equals(this.to, var2.to);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.from, this.to});
    }
}