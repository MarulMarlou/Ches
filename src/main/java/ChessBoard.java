import java.util.Scanner;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public static ChessBoard buildBoard() {
        ChessBoard board = new ChessBoard("White");

        board.board[0][0] = new Rook("White");
        board.board[0][1] = new Horse("White");
        board.board[0][2] = new Bishop("White");
        board.board[0][3] = new Queen("White");
        board.board[0][4] = new King("White");
        board.board[0][5] = new Bishop("White");
        board.board[0][6] = new Horse("White");
        board.board[0][7] = new Rook("White");
        board.board[1][0] = new Pawn("White");
        board.board[1][1] = new Pawn("White");
        board.board[1][2] = new Pawn("White");
        board.board[1][3] = new Pawn("White");
        board.board[1][4] = new Pawn("White");
        board.board[1][5] = new Pawn("White");
        board.board[1][6] = new Pawn("White");
        board.board[1][7] = new Pawn("White");

        board.board[7][0] = new Rook("Black");
        board.board[7][1] = new Horse("Black");
        board.board[7][2] = new Bishop("Black");
        board.board[7][3] = new Queen("Black");
        board.board[7][4] = new King("Black");
        board.board[7][5] = new Bishop("Black");
        board.board[7][6] = new Horse("Black");
        board.board[7][7] = new Rook("Black");
        board.board[6][0] = new Pawn("Black");
        board.board[6][1] = new Pawn("Black");
        board.board[6][2] = new Pawn("Black");
        board.board[6][3] = new Pawn("Black");
        board.board[6][4] = new Pawn("Black");
        board.board[6][5] = new Pawn("Black");
        board.board[6][6] = new Pawn("Black");
        board.board[6][7] = new Pawn("Black");
        return board;
    }

    public static void main(String[] args) {

        ChessBoard board = buildBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
               Чтобы проверить игру надо вводить команды:
               'exit' - для выхода
               'replay' - для перезапуска игры
               'castling0' или 'castling7' - для рокировки по соответствующей линии
               'move 1 1 2 3' - для передвижения фигуры с позиции 1 1 на 2 3(поле это двумерный массив от 0 до 7)
               Проверьте могут ли фигуры ходить друг сквозь друга, корректно ли съедают друг друга, можно ли поставить шах и сделать рокировку?""");
        System.out.println();
        board.printBoard();
        while (true) {
            String s = scanner.nextLine();
            if (s.equals("exit")) break;
            else if (s.equals("replay")) {
                System.out.println("Заново");
                board = buildBoard();
                board.printBoard();
            } else {
                if (s.equals("castling0")) {
                    if (board.castling0()) {
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.equals("castling7")) {
                    if (board.castling7()) {
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.contains("move")) {
                    String[] a = s.split(" ");
                    try {
                        int line = Integer.parseInt(a[1]);
                        int column = Integer.parseInt(a[2]);
                        int toLine = Integer.parseInt(a[3]);
                        int toColumn = Integer.parseInt(a[4]);
                        if (board.moveToPosition(line, column, toLine, toColumn)) {
                            System.out.println("Успешно передвинулись");
                            board.printBoard();
                        } else System.out.println("Передвижение не удалось");
                    } catch (Exception e) {
                        System.out.println("Вы что-то ввели не так, попробуйте ещё раз");
                    }

                }
            }
        }
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (!checkPos(startLine) || !checkPos(startColumn) || !checkPos(endLine) || !checkPos(endColumn)) {
            return false;
        }
        if (board[startLine][startColumn] == null) {
            return false;
        }

        if (!nowPlayer.equals(board[startLine][startColumn].getColor())) {
            return false;
        }

        if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
            board[endLine][endColumn] = board[startLine][startColumn];
            board[startLine][startColumn] = null;
            nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
            return true;
        }
        System.out.println("Attempting to move from (" + startLine + "," + startColumn + ") to (" + endLine + "," + endColumn + ")");
        return false;
    }


    public void printBoard() {
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        int kingLine = nowPlayer.equals("White") ? 0 : 7;
        int rookLine = kingLine;
        int rookColumn = 0;


        if (board[kingLine][4] instanceof King && board[kingLine][4].isCheck() &&
                board[rookLine][rookColumn] instanceof Rook && board[rookLine][rookColumn].isCheck()) {
            for (int i = 1; i < 4; i++) {
                if (board[kingLine][i] != null) {
                    return false;
                }
            }

            board[kingLine][2] = board[kingLine][4];
            board[kingLine][4] = null;
            board[kingLine][3] = board[kingLine][0];
            board[kingLine][0] = null;

            board[kingLine][2].setCheck(false);
            board[kingLine][3].setCheck(false);

            this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

            return true;
        }

        return false;
    }

    public boolean castling7() {
        int kingLine = nowPlayer.equals("White") ? 0 : 7;
        int rookLine = kingLine;
        int rookColumn = 7;

        if (board[kingLine][4] instanceof King && board[kingLine][4].isCheck() &&
                board[rookLine][rookColumn] instanceof Rook && board[rookLine][rookColumn].isCheck()) {
            for (int i = 5; i < 7; i++) {
                if (board[kingLine][i] != null) {
                    return false;
                }
            }

            board[kingLine][6] = board[kingLine][4];
            board[kingLine][4] = null;
            board[kingLine][5] = board[kingLine][7];
            board[kingLine][7] = null;

            board[kingLine][6].setCheck(false);
            board[kingLine][5].setCheck(false);

            this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

            return true;
        }

        return false;
    }

}