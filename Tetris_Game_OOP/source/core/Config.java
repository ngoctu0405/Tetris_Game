package core;

public class Config {
    // Số hàng của bảng Tetris
    public static final int ROWS = 20;
    // Số cột của bảng Tetris
    public static final int COLS = 10;
    // Kích thước của mỗi ô vuông (pixel)
    public static final int TILE = 30;
    // Chiều rộng của thanh bên (pixel)
    public static final int SIDE_WIDTH = 200;
    // Chiều rộng cửa sổ game = số cột * kích thước ô + độ rộng thanh bên
    public static final int WINDOW_WIDTH = COLS * TILE + SIDE_WIDTH;
    // Chiều cao cửa sổ game = số hàng * kích thước ô + 40px (cho thanh tiêu đề)
    public static final int WINDOW_HEIGHT = ROWS * TILE + 40;
    // Cấp độ bắt đầu của game
    public static final int START_LEVEL = 1;
    // Thời gian trễ cơ bản giữa các lần di chuyển khối (mili giây)
    public static final long BASE_DELAY_MS = 700;
}
