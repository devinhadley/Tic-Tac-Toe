package com.company;

import java.util.Scanner;

public class Game {

    private final int[][] board;
    private int current_player;
    private boolean winner;
    private boolean remains;
    private boolean error;

    public Game() {

        board = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        current_player = 1;
        winner = false;
        remains = true;
        error = false;

    }

    public void renderBoard() {

        System.out.println("---------");
        for (int[] arr : board){
            for (int current_val : arr){
                switch (current_val) {
                    case 0:
                        System.out.print(" | ");
                        break;
                    case 1:
                        System.out.print(" X ");
                        break;
                    case 2:
                        System.out.print(" O ");
                        break;
                }
            }
            System.out.print("\n");
            System.out.println("---------");
        } // Rendering board


    }

    public void checkHorizontalWinners() {
        for (int i = 0; i < board.length; i++) { // Checks for horizontal victories.
            int[] current_num = board[i];
            if (current_num[0] != 0 && (current_num[0] == current_num[1]) && (current_num[0] == current_num[2])) {
                winner = true;
                System.out.print("Player " + current_player + " is the winner by horizontal allignment!");
                break;
            }
        }
        for (int i = 0; i < board.length; i++) {
            int[] current_num = board[i];
            if (board[0][i] != 0 && (board[0][i] == board[1][i]) && board[0][i] == board[2][i]) { // Checks for vertical allignment.
                System.out.print("Player " + current_player + " is the winner by vertical allignment!");
                winner = true;
            }
        }
    }

    public void checkDiagonalWinners() {

        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            System.out.print("Player " + current_player + " is the winner by diagonal allignment!");
            winner = true;
        } else if (board[0][2] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {

            System.out.print("Player " + current_player + " is the winner by diagonal allignment!");
            winner = true;
        }
    }

    public void checkStalemate() {
        remains = false;
        for (int i = 0; i < board.length; i++) { // Checks if any empty positions on the board exist.

            for (int j = 0; j < board[i].length; j++) {

                if (board[i][j] == 0) {
                    remains = true;
                }
            }
        }
    }

    public void switchPlayer() {
        if (current_player == 1) {
            current_player = 2;
        } else {
            current_player = 1;
        }
        if (!remains) {
            System.out.print("Game has reached stalemate!");
        }
    }

    public void playGame() {
        Scanner scan = new Scanner(System.in);
        while (!winner && remains) {
            // Clear the console.
            System.out.print("\033[H\033[2J");
            System.out.flush();
            if (error) {
                System.out.println("Slot taken or invalid, try again!");
                error = false;
            }
            renderBoard();
            System.out.println("Player " + current_player + " please enter your move.");
            // Get user input.
            int gridX = scan.nextInt(); // index in the 2d array.
            int gridY = scan.nextInt(); // Which 2d array.

            // Validate the input.
            if (gridY <= 3 && gridX <= 3 && board[gridY - 1][gridX - 1] == 0) {
                board[gridY - 1][gridX - 1] = current_player;
                checkHorizontalWinners();
                checkDiagonalWinners();
                checkStalemate();
                switchPlayer();
            } else {
                error = true;
            }
        }


    }
}
