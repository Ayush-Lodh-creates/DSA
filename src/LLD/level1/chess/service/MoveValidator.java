package LLD.level1.chess.service;

import LLD.level1.chess.entity.Piece;
import LLD.level1.chess.entity.Position;
import LLD.level1.chess.enums.Color;

import java.util.ArrayList;
import java.util.List;

public class MoveValidator {
    public static boolean isLegalMove(BoardService var0, Position var1, Position var2) {
        if (var0.isValid(var1) && var0.isValid(var2)) {
            Piece var3 = var0.getPiece(var1);
            if (var3 == null) {
                return false;
            } else if (var3.getColor() != var0.getCurrentTurn()) {
                return false;
            } else if (var1.equals(var2)) {
                return false;
            } else if (!var3.isValidMove(var0, var1, var2)) {
                return false;
            } else {
                return !wouldLeaveKingInCheck(var0, var1, var2);
            }
        } else {
            return false;
        }
    }

    private static boolean wouldLeaveKingInCheck(BoardService var0, Position var1, Position var2) {
        BoardService var3 = var0.copy();
        Piece var4 = var3.getPiece(var1);
        Piece var5 = var3.getPiece(var2);
        var3.setPiece(var2, var4);
        var3.setPiece(var1, (Piece)null);
        var4.setMoved(true);
        boolean var6 = isKingInCheck(var3, var4.getColor());
        var3.setPiece(var1, var4);
        var3.setPiece(var2, var5);
        var4.setMoved(false);
        return var6;
    }

    public static boolean isKingInCheck(BoardService var0, Color var1) {
        Position var2 = var0.findKing(var1);
        if (var2 == null) {
            return false;
        } else {
            Color var3 = var1 == Color.WHITE ? Color.BLACK : Color.WHITE;

            for(int var4 = 0; var4 < 8; ++var4) {
                for(int var5 = 0; var5 < 8; ++var5) {
                    Position var6 = new Position(var4, var5);
                    Piece var7 = var0.getPiece(var6);
                    if (var7 != null && var7.getColor() == var3 && var7.isValidMove(var0, var6, var2)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public static boolean isCheckmate(BoardService var0, Color var1) {
        if (!isKingInCheck(var0, var1)) {
            return false;
        } else {
            return !hasLegalMoves(var0, var1);
        }
    }

    public static boolean isStalemate(BoardService var0, Color var1) {
        if (isKingInCheck(var0, var1)) {
            return false;
        } else {
            return !hasLegalMoves(var0, var1);
        }
    }

    public static boolean hasLegalMoves(BoardService var0, Color var1) {
        for(int var2 = 0; var2 < 8; ++var2) {
            for(int var3 = 0; var3 < 8; ++var3) {
                Position var4 = new Position(var2, var3);
                Piece var5 = var0.getPiece(var4);
                if (var5 != null && var5.getColor() == var1) {
                    for(int var6 = 0; var6 < 8; ++var6) {
                        for(int var7 = 0; var7 < 8; ++var7) {
                            Position var8 = new Position(var6, var7);
                            if (isLegalMove(var0, var4, var8)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public static List<Position> getLegalMovesForPiece(BoardService var0, Position var1) {
        ArrayList var2 = new ArrayList();
        Piece var3 = var0.getPiece(var1);
        if (var3 != null && var3.getColor() == var0.getCurrentTurn()) {
            for(int var4 = 0; var4 < 8; ++var4) {
                for(int var5 = 0; var5 < 8; ++var5) {
                    Position var6 = new Position(var4, var5);
                    if (isLegalMove(var0, var1, var6)) {
                        var2.add(var6);
                    }
                }
            }

            return var2;
        } else {
            return var2;
        }
    }
}