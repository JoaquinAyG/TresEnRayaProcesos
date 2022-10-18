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
        while (board.turn < 8 && !board.winCheck()) {
            board.waitTurn(playNum);
            if (board.winCheck()) {
                board.endGame();
                return;
            }
            int move = board.move();
            board.write(playNum, move);
            board.print();
            try {
                sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            board.changeTurn();

        }
        board.printWinner();
    }
}
