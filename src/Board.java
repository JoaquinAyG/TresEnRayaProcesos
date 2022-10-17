import java.util.Scanner;

public class Board {

    final static int LENGTH = 3;
    static int[][] board;
    Player p1;
    Player p2;

    public void play(){
        initBoard();
        initPlayers();
        p1.start();
        p2.start();
        /*if(p1.getState() == Thread.State.WAITING){
            p2.start();
        } else {
            try {
                p1.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            p2.start();
        }*/
    }

    public static boolean winCheck() {
        //Cols
        boolean end;
        int num;
        for (int i = 0; i < LENGTH; i++) {
            end = true;
            num = board[i][0];
            for (int j = 0; j < LENGTH; j++) {
                if(board [i][j] != num){
                    end = false;
                    break;
                }
            }
            if(end){
                return true;
            }
        }

        //Rows
        for (int i = 0; i < LENGTH; i++) {
            end = true;
            num = board[0][i];
            for (int j = 0; j < LENGTH; j++) {
                if(board [j][i] != num){
                    end = false;
                    break;
                }
            }
            if(end){
                return true;
            }
        }

        //Diagonal
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2]){
            return true;
        }
        return board[2][0] == board[1][1] && board[1][1] == board[0][2];
    }

    public static boolean isFull() {
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if(board[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    private void initPlayers() {

        p1 = new Player(getName(1), 1, board);
        p2 = new Player(getName(2), 2 , board);

    }

    private void initBoard(){
        board = new int[LENGTH][LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                board[i][j] = 0;
            }
        }
    }

    private String getName(int num){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el nombre del jugador: " + num);
        return sc.nextLine();
    }

    public static void print(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {

            for (int j = 0; j < LENGTH; j++) {
                str.append(board[i][j]).append(" | ");
            }
            str.append("\n").append("---------------").append("\n");
        }
        System.out.println(str.toString());
    }
}
