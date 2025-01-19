public abstract class ChessPiece {
    private String color;
    private boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    // Геттеры и сеттеры
    public String getColor() {
        return color;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    // Абстрактные методы
    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();
}
