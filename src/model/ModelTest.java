package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ModelTest {
	Board s = new Board();

	@Test
	public void test() {
		s.initBoard();

		assertEquals(Piece.ROOK, s.getPiece(0, 0, 1).getType());
		assertEquals(Piece.KNIGHT, s.getPiece(0, 1, 1).getType());
		assertEquals(Piece.BISHOP, s.getPiece(0, 2, 1).getType());
		assertEquals(Piece.QUEEN, s.getPiece(0, 3, 1).getType());
		assertEquals(Piece.KING, s.getPiece(0, 4, 1).getType()); // PEZZI
																	// BIANCHI
		assertEquals(Piece.BISHOP, s.getPiece(0, 5, 1).getType());
		assertEquals(Piece.KNIGHT, s.getPiece(0, 6, 1).getType());
		assertEquals(Piece.ROOK, s.getPiece(0, 7, 1).getType());
		assertEquals(Piece.PAWN, s.getPiece(1, 0, 1).getType());
		assertEquals(Piece.PAWN, s.getPiece(1, 1, 1).getType());
		assertEquals(Piece.PAWN, s.getPiece(1, 2, 1).getType());
		assertEquals(Piece.PAWN, s.getPiece(1, 3, 1).getType());
		assertEquals(Piece.PAWN, s.getPiece(1, 4, 1).getType());
		assertEquals(Piece.PAWN, s.getPiece(1, 5, 1).getType());
		assertEquals(Piece.PAWN, s.getPiece(1, 6, 1).getType());
		assertEquals(Piece.PAWN, s.getPiece(1, 7, 1).getType());

		assertEquals(Piece.ROOK, s.getPiece(7, 0, 0).getType());
		assertEquals(Piece.KNIGHT, s.getPiece(7, 1, 0).getType());
		assertEquals(Piece.BISHOP, s.getPiece(7, 2, 0).getType());
		assertEquals(Piece.QUEEN, s.getPiece(7, 3, 0).getType());
		assertEquals(Piece.KING, s.getPiece(7, 4, 0).getType()); // PEZZI NERI
		assertEquals(Piece.BISHOP, s.getPiece(7, 5, 0).getType());
		assertEquals(Piece.KNIGHT, s.getPiece(7, 6, 0).getType());
		assertEquals(Piece.ROOK, s.getPiece(7, 7, 0).getType());
		assertEquals(Piece.PAWN, s.getPiece(6, 0, 0).getType());
		assertEquals(Piece.PAWN, s.getPiece(6, 1, 0).getType());
		assertEquals(Piece.PAWN, s.getPiece(6, 2, 0).getType());
		assertEquals(Piece.PAWN, s.getPiece(6, 3, 0).getType());
		assertEquals(Piece.PAWN, s.getPiece(6, 4, 0).getType());
		assertEquals(Piece.PAWN, s.getPiece(6, 5, 0).getType());
		assertEquals(Piece.PAWN, s.getPiece(6, 6, 0).getType());
		assertEquals(Piece.PAWN, s.getPiece(6, 7, 0).getType());

	}

}