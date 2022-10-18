public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.init();
        Player p1 = new Player("p1", 1, board);
        Player p2 = new Player("p2", 2, board);
        p1.start();
        p2.start();
    }
}