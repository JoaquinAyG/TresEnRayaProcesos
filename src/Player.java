/*
Author: Joaquin Ayllon Garcia
GitHub: JoaquinAyG
 */
public class Player extends Thread {

    Board board;
    int playNum;

    public Player(String name, int playNum, Board board) {
        super(name);
        this.board = board;
        this.playNum = playNum;
    }

    @Override
    public void run() {
        while (board.turn < 8) {
            board.waitTurn(playNum);
            if (board.winCheck()) {
                return;
            }
            int move = board.move();
            board.write(playNum, move);
            board.print();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            board.changeTurn();

        }
        board.printWinner();
    }
}
