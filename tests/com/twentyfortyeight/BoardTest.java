package com.twentyfortyeight;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void initialisation() {
        Assert.assertFalse(board.isFull());
        Assert.assertFalse(board.hasEqualNeighbour());
        Assert.assertFalse(board.checkIfOver());
    }

    @Test
    void fillBoard() {
        while(!board.isFull()){
            board.newTile();
        }
        Assert.assertTrue(board.hasEqualNeighbour());
    }

    @Test
    void moveSouth() {
        board.getTiles()[3][3] = new Tile(8);
        board.getTiles()[0][3] = new Tile(8);
        board.move(Board.Direction.DOWN);
        Assert.assertEquals(board.getTiles()[3][3].getValue(), 16);
    }

    @Test
    void moveRight() {
        board.getTiles()[0][0] = new Tile(2);
        board.getTiles()[0][2] = new Tile(2);
        board.move(Board.Direction.RIGHT);
        Assert.assertEquals(board.getTiles()[0][3].getValue(), 4);
    }

    @Test
    void differentValues() {
        board.getTiles()[0][0] = new Tile(2);
        board.getTiles()[0][2] = new Tile(8);
        board.move(Board.Direction.RIGHT);
        Assert.assertEquals(board.getTiles()[0][3].getValue(), 8);
    }
}