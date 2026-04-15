package LLD.level1.chess;

import LLD.level1.chess.entity.Position;
import LLD.level1.chess.enums.Color;
import LLD.level1.chess.service.BoardService;
import LLD.level1.chess.service.MoveValidator;

import java.io.PrintStream;

public class ChessGame {
    private BoardService boardService = new BoardService();

    public void displayGameState() {
        this.boardService.display();
        System.out.println("Current Turn: " + String.valueOf(this.boardService.getCurrentTurn()));
        if (MoveValidator.isKingInCheck(this.boardService, this.boardService.getCurrentTurn())) {
            System.out.println(String.valueOf(this.boardService.getCurrentTurn()) + " King is in CHECK!");
        }

    }

    public boolean movePiece(Position var1, Position var2) {
        if (!MoveValidator.isLegalMove(this.boardService, var1, var2)) {
            System.out.println("Invalid move!");
            return false;
        } else {
            this.boardService.makeMove(var1, var2);
            Color var3 = this.boardService.getCurrentTurn();
            if (MoveValidator.isCheckmate(this.boardService, var3)) {
                PrintStream var10000 = System.out;
                String var10001 = String.valueOf(var3);
                var10000.println(var10001 + " is in CHECKMATE! " + (this.boardService.getCurrentTurn() == Color.WHITE ? "BLACK" : "WHITE") + " wins!");
                return true;
            } else if (MoveValidator.isStalemate(this.boardService, var3)) {
                System.out.println("STALEMATE! The game is a draw.");
                return true;
            } else {
                this.boardService.switchTurn();
                return true;
            }
        }
    }

    public void reset() {
        this.boardService.reset();
    }

    public BoardService getBoard() {
        return this.boardService;
    }

    public static void main(String[] var0) {
        ChessGame var1 = new ChessGame();
        System.out.println("=== Chess Game ===");
        var1.displayGameState();
        System.out.println("\nWhite moves Pawn e2 to e4");
        var1.movePiece(new Position(6, 4), new Position(4, 4));
        var1.displayGameState();
        System.out.println("\nBlack moves Pawn e7 to e5");
        var1.movePiece(new Position(1, 4), new Position(3, 4));
        var1.displayGameState();
        System.out.println("\nWhite moves Knight g1 to f3");
        var1.movePiece(new Position(7, 6), new Position(5, 5));
        var1.displayGameState();
    }
}