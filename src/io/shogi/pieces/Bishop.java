package io.shogi.pieces;

import io.shogi.core.Board;
import io.shogi.core.Field;
import io.shogi.core.Piece;
import io.shogi.core.PieceType;

/**
 * Futó
 */
public class Bishop extends Piece {
    /**
     * Futó constructor
     * @param owner Tulajdonos
     */
    public Bishop(int owner) {
        super(owner);
        setSymbol("B");
        setType(PieceType.KAKUGYO);
    }

    /**
     * Bishop movement code
     * @param current A mező, ahol az egység tartózkodik.
     * @param target A mező, ahova szeretnénk lépni.
     * @param board A játéktábla.
     * @return Léphet-e a célpontra.
     */
    @Override
    public boolean canMove(Field current, Field target, Board board) {
        if (promoted) {
            if ((Math.abs(current.col() - target.col()) <= 1) && (Math.abs(current.row() - target.row()) <= 1))
                return true;
        }
        if (Math.abs(current.row() - target.row()) == Math.abs(current.col() - target.col())) {
            // Decide direction
            int rowDir = target.row() > current.row() ? 1 : -1;
            int colDir = target.col() > current.col() ? 1 : -1;

            for (int i = 1; i < Math.abs(target.col() - current.col()); i++) {
                if (board.getField(current.row() + i * rowDir, current.col() + i * colDir).getPiece() != null) {
                    return false;
                }
            }

            if (target.getPiece() != null) {
                if (current.getPiece().getOwner() == target.getPiece().getOwner()) {
                    return false;
                }
            }

            return true;

        }
        return false;
    }
}
