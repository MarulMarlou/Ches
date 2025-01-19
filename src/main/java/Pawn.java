public class Pawn extends ChessPiece {
    public Pawn(String color) {
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

        // Определяем направление движения пешки
        int direction = getColor().equals("White") ? 1 : -1;

        // Обычный ход пешки на одно поле
        if (toLine == line + direction && column == toColumn) {
            // Проверяет, что целевая клетка пуста
            if (chessBoard.board[toLine][toColumn] == null) {
                return true;
            }
        }

        // Двойной ход пешки
        if (getColor().equals("White") && line == 6 && toLine == 4 && column == toColumn) {
            // Проверяет, что обе клетки (целевой и первая) пусты
            if (chessBoard.board[5][column] == null && chessBoard.board[4][column] == null) {
                return true;
            }
        }

        if (getColor().equals("Black") && line == 1 && toLine == 3 && column == toColumn) {
            // Проверяет, что обе клетки (целевой и первая) пусты
            if (chessBoard.board[2][column] == null && chessBoard.board[3][column] == null) {
                return true;
            }
        }

        // Ход на диагональ (поедание фигуры)
        if (Math.abs(toColumn - column) == 1 && toLine == line + direction) {
            // Проверяет, что клетка не пуста и находится фигура противника
            if (chessBoard.board[toLine][toColumn] != null && !chessBoard.board[toLine][toColumn].getColor().equals(getColor())) {
                return true;
            }
        }

        return false;
    }


    @Override
    public String getSymbol() {
        return "P";
    }
}
