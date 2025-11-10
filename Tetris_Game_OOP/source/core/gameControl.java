package core;

import core.blocks.Block;

// Lớp điều khiển chính của trò chơi
public class gameControl {

    private final BlockFactory blockFactory;    // Factory để tạo các khối
    private final Board board = new Board();    // Bảng trò chơi
    private Block current;                      // Khối đang rơi hiện tại
    private Block next;                         // Khối tiếp theo
    private final Score score = new Score();    // Điểm số

    // Khởi tạo điều khiển trò chơi
    public gameControl(BlockFactory blockFactory) {
        this.blockFactory = blockFactory;
        spawn();
    }

    // Tạo khối mới
    private void spawn() {
        current = (next != null) ? next : blockFactory.createRandom();
        next = blockFactory.createRandom();
    }

    // Xử lý mỗi bước trong game
    public void tick() {
        if (current == null) {
            return;
        }
        Block moved = current.copy();
        moved.moveDown();
        if (board.canPlace(moved)) {
            current = moved;
        } else {
            board.place(current);
            int cleared = board.clearLines();    // Xóa các hàng đã đầy
            score.addLinesCleared(cleared);             // Cập nhật điểm
            spawn();

            if (!board.canPlace(current)) {
                current = null;                  // Game over nếu không đặt được khối mới
            }
        }
    }

    // Các phương thức getter
    public Board getBoard() {
        return board;
    }

    public Block getCurrent() {
        return current;
    }

    public Block getNext() {
        return next;
    }

    public Score getScore() {
        return score;
    }

    // Di chuyển khối sang trái
    public void moveLeft() {
        if (current == null) {
            return;
        }
        Block m = current.copy();
        m.moveLeft();
        if (board.canPlace(m)) {
            current = m;
        }
    }

    // Di chuyển khối sang phải
    public void moveRight() {
        if (current == null) {
            return;
        }
        Block m = current.copy();
        m.moveRight();
        if (board.canPlace(m)) {
            current = m;
        }
    }

    // Thả khối nhanh hơn một chút (soft drop)
    public void softDrop() {
        if (current == null) {
            return;
        }
        Block m = current.copy();
        m.moveDown();
        if (board.canPlace(m)) {
            current = m;
        }
    }

    // Thả khối xuống đáy ngay lập tức (hard drop)
    public void hardDrop() {
        if (current == null) {
            return;
        }
        while (true) {
            Block m = current.copy();
            m.moveDown();
            if (board.canPlace(m)) {
                current = m;
            } else {
                break;
            }
        }
        tick();
    }

    // Xoay khối
    public void rotate() {
        if (current == null) {
            return;
        }
        Block m = current.copy();
        m.rotate();
        if (board.canPlace(m)) {
            current = m;
        }
    }

    // Kiểm tra game over
    public boolean isGameOver() {
        return current == null;
    }

    // Khởi động lại trò chơi
    public void reset() {
        board.reset();
        score.reset();
        spawn();
    }
}
