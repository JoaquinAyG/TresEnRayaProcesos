/*
Author: Joaquin Ayllon Garcia
GitHub: JoaquinAyG
This class acts as the main controller for the Player class
 */
public class Board {

    int turn = 0;
    final static int LENGTH = 3;
    static int[][] board;

    public synchronized void waitTurn(int playNum) {
        if (playNum % 2 == turn % 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("No le da la gana esperar");
            }
        }
    }

    public synchronized void changeTurn() {
        turn++;
        notifyAll();
    }

    public void init() {
        board = new int[LENGTH][LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                board[i][j] = 0;
            }
        }
    }

    public boolean winCheck() {
        //Cols
        boolean end;
        int num;
        for (int i = 0; i < LENGTH; i++) {
            end = true;
            num = board[i][0];
            if (num != 0) {
                for (int j = 0; j < LENGTH; j++) {
                    if (board[i][j] != num) {
                        end = false;
                        break;
                    }
                }
            } else {
                end = false;
            }
            if (end) {
                return true;
            }

        }

        //Rows
        for (int i = 0; i < LENGTH; i++) {
            end = true;
            num = board[0][i];
            if (num != 0) {
                for (int j = 0; j < LENGTH; j++) {
                    if (board[j][i] != num) {
                        end = false;
                        break;
                    }
                }
            } else {
                end = false;
            }
            if (end) {
                return true;
            }
        }

        //Diagonal
        if (board[1][1] != 0) {
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                return true;
            }
            return board[2][0] == board[1][1] && board[1][1] == board[0][2];
        }
        return false;
    }

    public void print() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {

            for (int j = 0; j < LENGTH; j++) {
                str.append(board[i][j]).append(" | ");
            }
            str.append("\n").append("---------------").append("\n");
        }
        System.out.println(str);
    }

    public int move() {
        int blanc = countBlac();
        if(blanc == 9){
            return 4;
        }
        return (int) (Math.random() * blanc);
    }

    private int countBlac() {
        int count = 0;
        for (int i = 0; i < Board.LENGTH; i++) {
            for (int j = 0; j < Board.LENGTH; j++) {
                if (board[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public void write(int playNum, int num) {
        int count = 0;
        for (int i = 0; i < Board.LENGTH; i++) {
            for (int j = 0; j < Board.LENGTH; j++) {
                if (board[i][j] == 0) {
                    if (count == num) {
                        board[i][j] = playNum;
                        return;
                    } else {
                        count++;
                    }
                }
            }
        }
    }

    public void printWinner() {

        if (turn < 9 && !winCheck()) {
            System.out.println("Thers a draw!");
            return;
        }

        String winner = (turn % 2 == 1) ? "P1 won" : "P2 won";

        System.out.println(winner);
    }

    public synchronized void endGame() {
        notifyAll();
    }
}
