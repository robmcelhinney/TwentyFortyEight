package com.twentyfortyeight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class TwentyFortyEight {

    private static final String GAME_OVER = "GAME OVER";
    private static final String COMMAND1 = "1. Enter \"w\" to move up,  \"a\" to move left, \"s\" to move down, or \"d\" to move right.";
    private static final String COMMAND2 = "2. Enter \"q\" to exit the game.";

    public static void main(String[] args) {
        Board board = startGame();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        try {
            input = br.readLine();
            boolean possibleToMove;
            outerloop:
            while (input != null){
                switch(input) {
                    case "w": possibleToMove = board.move(Board.Direction.UP); break;
                    case "d": possibleToMove = board.move(Board.Direction.RIGHT); break;
                    case "s": possibleToMove = board.move(Board.Direction.DOWN); break;
                    case "a": possibleToMove = board.move(Board.Direction.LEFT); break;
                    case "q": break outerloop;
                    default:
                        System.out.println("Invalid input! Enter move again.");
                        board.printBoard();
                        input = br.readLine();
                        continue;
                }

                if(!board.checkIfOver() && possibleToMove) {
                    board.newTile();
                }

//                if(!board.checkIfOver()) {
//                    if(possibleToMove) {
//                        board.newTile();
//                    }
//                }
//                else {
//                    gameOver();
//                    return;
//                }
                board.printBoard();
                if(board.checkIfOver()) {
                    gameOver();
                    String continueQ = br.readLine();
                    gameOverLoop:
                    while(true){
                        System.out.println(continueQ);
                        switch (continueQ) {
                            case "y":
                                board = startGame();
                                break gameOverLoop;
                            case "n":
                                return;
                            default:
                                System.out.println("y/n?");
                                continueQ = br.readLine();
                                break;
                        }
                    }
                }
                input = br.readLine();
            }
            board.printBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Board startGame() {
        Board board = new Board();
        board.newTile();

        System.out.println(COMMAND1);
        System.out.println(COMMAND2);
        System.out.println();

        board.printBoard();
        return board;
    }

    private static void gameOver() {
        System.out.println(GAME_OVER);
        System.out.println("CONTINUE?\ny/n");
    }
}