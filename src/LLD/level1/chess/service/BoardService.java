package LLD.level1.chess.service;

import LLD.level1.chess.entity.*;
import LLD.level1.chess.enums.Color;
import LLD.level1.chess.enums.PieceType;

public class BoardService {
    private static final int SIZE = 8;
    private Piece[][] board = new Piece[8][8];
    private Color currentTurn;

    public BoardService() {
        this.currentTurn = Color.WHITE;
        this.initializeBoard();
    }

    private void initializeBoard() {
        for(int var1 = 0; var1 < 8; ++var1) {
            for(int var2 = 0; var2 < 8; ++var2) {
                this.board[var1][var2] = null;
            }
        }

        this.board[0][0] = new Rook(Color.BLACK);
        this.board[0][1] = new Knight(Color.BLACK);
        this.board[0][2] = new Bishop(Color.BLACK);
        this.board[0][3] = new Queen(Color.BLACK);
        this.board[0][4] = new King(Color.BLACK);
        this.board[0][5] = new Bishop(Color.BLACK);
        this.board[0][6] = new Knight(Color.BLACK);
        this.board[0][7] = new Rook(Color.BLACK);

        for(int var3 = 0; var3 < 8; ++var3) {
            this.board[1][var3] = new Pawn(Color.BLACK);
        }

        for(int var4 = 0; var4 < 8; ++var4) {
            this.board[6][var4] = new Pawn(Color.WHITE);
        }

        this.board[7][0] = new Rook(Color.WHITE);
        this.board[7][1] = new Knight(Color.WHITE);
        this.board[7][2] = new Bishop(Color.WHITE);
        this.board[7][3] = new Queen(Color.WHITE);
        this.board[7][4] = new King(Color.WHITE);
        this.board[7][5] = new Bishop(Color.WHITE);
        this.board[7][6] = new Knight(Color.WHITE);
        this.board[7][7] = new Rook(Color.WHITE);
    }

    public Piece getPiece(Position var1) {
        return !this.isValid(var1) ? null : this.board[var1.getRow()][var1.getCol()];
    }

    public void setPiece(Position var1, Piece var2) {
        if (this.isValid(var1)) {
            this.board[var1.getRow()][var1.getCol()] = var2;
        }

    }

    public boolean isValid(Position var1) {
        return var1 != null && var1.getRow() >= 0 && var1.getRow() < 8 && var1.getCol() >= 0 && var1.getCol() < 8;
    }

    public boolean isEmpty(Position var1) {
        return this.isValid(var1) && this.getPiece(var1) == null;
    }

    public boolean isEnemyPiece(Position var1, Color var2) {
        if (!this.isValid(var1)) {
            return false;
        } else {
            Piece var3 = this.getPiece(var1);
            return var3 != null && var3.getColor() != var2;
        }
    }

    public boolean isFriendlyPiece(Position var1, Color var2) {
        if (!this.isValid(var1)) {
            return false;
        } else {
            Piece var3 = this.getPiece(var1);
            return var3 != null && var3.getColor() == var2;
        }
    }

    public boolean isPathClear(Position var1, Position var2) {
        int var3 = var2.getRow() - var1.getRow();
        int var4 = var2.getCol() - var1.getCol();
        int var5 = 0;
        int var6 = 0;
        if (var3 != 0) {
            var5 = var3 / Math.abs(var3);
        }

        if (var4 != 0) {
            var6 = var4 / Math.abs(var4);
        }

        int var7 = var1.getRow() + var5;

        for(int var8 = var1.getCol() + var6; var7 != var2.getRow() || var8 != var2.getCol(); var8 += var6) {
            if (!this.isEmpty(new Position(var7, var8))) {
                return false;
            }

            var7 += var5;
        }

        return true;
    }

    public Position findKing(Color var1) {
        for(int var2 = 0; var2 < 8; ++var2) {
            for(int var3 = 0; var3 < 8; ++var3) {
                Piece var4 = this.board[var2][var3];
                if (var4 != null && var4.getType() == PieceType.KING && var4.getColor() == var1) {
                    return new Position(var2, var3);
                }
            }
        }

        return null;
    }

    public Color getCurrentTurn() {
        return this.currentTurn;
    }

    public void switchTurn() {
        this.currentTurn = this.currentTurn == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public Piece makeMove(Position var1, Position var2) {
        Piece var3 = this.getPiece(var1);
        Piece var4 = this.getPiece(var2);
        if (var3 == null) {
            throw new IllegalArgumentException("No piece at position: " + String.valueOf(var1));
        } else {
            this.setPiece(var2, var3);
            this.setPiece(var1, (Piece)null);
            var3.setMoved(true);
            return var4;
        }
    }

    public void undoMove(Position var1, Position var2, Piece var3, Piece var4) {
        this.setPiece(var1, var3);
        this.setPiece(var2, var4);
        var3.setMoved(false);
    }

    public BoardService copy() {
        BoardService var1 = new BoardService();
        var1.board = new Piece[8][8];
        var1.currentTurn = this.currentTurn;

        for(int var2 = 0; var2 < 8; ++var2) {
            for(int var3 = 0; var3 < 8; ++var3) {
                if (this.board[var2][var3] != null) {
                    var1.board[var2][var3] = this.board[var2][var3].copy();
                } else {
                    var1.board[var2][var3] = null;
                }
            }
        }

        return var1;
    }

    public void display() {
        System.out.println();
        System.out.println("  a b c d e f g h");

        for(int var1 = 0; var1 < 8; ++var1) {
            System.out.print(8 - var1 + " ");

            for(int var2 = 0; var2 < 8; ++var2) {
                Piece var3 = this.board[var1][var2];
                if (var3 == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(var3.getSymbol() + " ");
                }
            }

            System.out.println(8 - var1);
        }

        System.out.println("  a b c d e f g h");
        System.out.println();
    }

    public void reset() {
        this.currentTurn = Color.WHITE;
        this.initializeBoard();
    }
}