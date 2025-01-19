public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка выхода за доску
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Проверка того, что фигура не ходит в точку, в которой она сейчас находится
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверка хода буквой "Г"
        if ((Math.abs(toLine - line) == 2 && Math.abs(toColumn - column) == 1) || (Math.abs(toLine - line) == 1 && Math.abs(toColumn - column) == 2)) {
            // Проверка того, что на конечной позиции нет фигуры того же цвета
            if (chessBoard.board[toLine][toColumn] != null && chessBoard.board[toLine][toColumn].getColor().equals(getColor())) {
                return false;
            }
            return true;
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
