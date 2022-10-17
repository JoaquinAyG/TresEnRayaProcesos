public class Player extends Thread{

    int[][] board;
    int playNum;

    public Player(String name, int playNum, int[][] board){
        super(name);
        this.board = board;
        this.playNum = playNum;
    }

    @Override
    public void run(){
        if(playNum == 2){notifyAll();}
        while (!Board.isFull() || !Board.winCheck()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            move();
            System.out.println(getName() + " Moves");
            Board.print();
            notifyAll();
        }
    }

    private void move(){
        int move = (int) (Math.random() * countBlac());
        write(move);
    }

    private int countBlac() {
        int count = 0;
        for (int i = 0; i < Board.LENGTH; i++) {
            for (int j = 0; j < Board.LENGTH; j++) {
                if(board[i][j] == 0){
                    count++;
                }
            }
        }
        return count;
    }

    private void write(int num){
        int count = 0;
        for (int i = 0; i < Board.LENGTH; i++) {
            for (int j = 0; j < Board.LENGTH; j++) {
                if(board[i][j] == 0){
                    if(count == num) {
                        board[i][j] = playNum;
                        break;
                    } else {
                        count++;
                    }
                }
            }
        }
    }
}
