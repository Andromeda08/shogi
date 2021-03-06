package io.shogi.pieces;

import io.shogi.core.Board;
import io.shogi.core.Field;
import io.shogi.core.Piece;
import io.shogi.core.PieceType;

/**
 * Bástya
 */
public class Rook extends Piece {
    /**
     * Bástya constructor
     * @param owner Tulajdonos
     */
    public Rook(int owner) {
        super(owner);
        setSymbol("R");
        setType(PieceType.HISHA);
    }

    /**
     * Rook movement code
     * @param current A mező, ahol az egység tartózkodik.
     * @param target A mező, ahova szeretnénk lépni.
     * @param board A játéktábla.
     * @return Tud-e lépni az egység.
     */
    @Override
    public boolean canMove(Field current, Field target, Board board) {
        if (promoted)
            if ((Math.abs(current.col() - target.col()) <= 1) && (Math.abs(current.row() - target.row()) <= 1))
                return true;

        if (target.getPiece() != null)
            if (current.getPiece().getOwner() == target.getPiece().getOwner())
                return false;

        if (current.col() != target.col() && current.row() != target.row())
            return false;

        // Left
        if (current.col() - target.col() > 0)
            for (int i = current.col() - 1; i > target.col(); i--)
                if (board.getField(current.row(), i).getPiece() != null)
                    return false;

        // Right
        if (current.col() - target.col() < 0)
            for (int i = current.col() + 1; i < target.col(); i++)
                if (board.getField(current.row(), i).getPiece() != null)
                    return false;

        // Up
        if (current.row() - target.row() > 0)
            for (int i = current.row() - 1; i > target.row(); i--)
                if (board.getField(i, current.col()).getPiece() != null)
                    return false;

        // Down
        if (current.row() - target.row() < 0)
            for (int i = current.row() + 1; i < target.row(); i++)
                if (board.getField(i, current.col()).getPiece() != null)
                    return false;

        return true;
    }
}
