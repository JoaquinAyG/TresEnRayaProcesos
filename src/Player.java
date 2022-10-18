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
        board.waitTurn(playNum);
        while (board.turn < 9 && !board.winCheck()) {
            int move = board.move();
            board.write(playNum, move);
            board.print();
            try {
                sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            board.changeTurn();
            board.waitTurn(playNum);

        }
        board.endGame();
        board.printWinner();
    }
}
