package com.twentyfortyeight;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    public enum Direction{UP, RIGHT, DOWN, LEFT}

    private final int SIZE = 4;

    public Tile[][] getTiles() {
        return tiles;
    }

    private final Tile[][] tiles = new Tile[SIZE][SIZE];

    public Board() {
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                tiles[i][j] = new Tile();
            }
        }
    }

    public void newTile() {
        if(isFull()) {
            return;
        }

        while(true) {
            int x = randomNumber(3);
            int y = randomNumber(3);

            if(tiles[x][y].getValue() == 0) {
                tiles[x][y] = randomNumber(1) == 0 ? new Tile(2) : new Tile(4);
                return;
            }
        }
    }

    public boolean checkIfOver() {
        return isFull() && !hasEqualNeighbour();
    }

    public boolean move(Direction direction) {
        boolean hasMoved = false;
        for(int i = 0; i < SIZE; i++) {
            Tile[] tilesToMove = new Tile[SIZE];
            for(int j = 0; j < SIZE; j++) {
                switch (direction) {
                    case UP: tilesToMove[j] = tiles[j][i]; break;
                    case RIGHT: tilesToMove[j] = tiles[i][(SIZE - 1) - j]; break;
                    case DOWN: tilesToMove[j] = tiles[(SIZE - 1) - j][i]; break;
                    case LEFT: tilesToMove[j] = tiles[i][j]; break;
                    default: break;
                }
            }
            if(slide(tilesToMove)) {
                hasMoved = true;
            }
        }
        return hasMoved;
    }

    private boolean slide(Tile[] movingTiles) {
        boolean slidToSide = slideToSide(movingTiles);
        boolean merged = merge(movingTiles);
        return (slidToSide || merged);
    }

    private boolean slideToSide(Tile[] movingTiles) {
        boolean hasSlid = false;
        for (int j = 0; j < movingTiles.length; j++) {
            if(restArrayZero(movingTiles, j)) {
                break;
            }
            while(movingTiles[j].getValue() == 0) {
                slideToPos(movingTiles, j);
                if(!hasSlid) {
                    hasSlid = true;
                }
            }
        }
        return hasSlid;
    }

    private void slideToPos(Tile[] movingTiles, int pos) {
        for(int k = pos; k < movingTiles.length - 1; k++) {
            movingTiles[k].setValue(movingTiles[k+1].getValue());
        }
        movingTiles[movingTiles.length-1].setValue(0);
    }

    private boolean merge(Tile[] movingTiles) {
        boolean hasMerged = false;
        for(int i = 0; i < movingTiles.length - 1; i++) {
            if(movingTiles[i].getValue() != 0 && movingTiles[i].equals(movingTiles[i+1])) {
                mergeTiles(movingTiles[i], movingTiles[i+1]);
                slideToPos(movingTiles, i+1);
                hasMerged = true;
            }
        }
        return hasMerged;
    }

    private boolean restArrayZero(Tile[] tileArray, int pos) {
        for(; pos < SIZE; pos++) {
            if(tileArray[pos].getValue() != 0) {
                return false;
            }
        }
        return true;
    }

    private void mergeTiles(Tile tile1, Tile tile2) {
        tile1.merge(tile2);
        tile2.setValue(0);
    }

    boolean hasEqualNeighbour() {
        for(int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(((j < SIZE-1 && tiles[i][j].equals(tiles[i][j+1]))
                        || (i < SIZE-1 && tiles[i][j].equals(tiles[i+1][j])))
                        && tiles[i][j].getValue() != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean isFull() {
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if(tiles[i][j].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    void printBoard() {
        StringBuilder board = new StringBuilder();
        for(int i = 0; i < SIZE; i++) {
            StringBuilder row = new StringBuilder("|");
            for(int j = 0; j < SIZE; j++) {
                String tileValue = (tiles[i][j].getValue() == 0) ? " " : Integer.toString(tiles[i][j].getValue());
                row.append(IntStream.range(0, (4 - tileValue.length())).mapToObj(k -> " ").collect(Collectors.joining("")));
                row.append(tileValue).append("|");
            }
            row.append("\n");
            board.append(row);
        }
        System.out.println(board);
    }

    private static int randomNumber(int max) {
        return ThreadLocalRandom.current().nextInt(0, max + 1);
    }
}