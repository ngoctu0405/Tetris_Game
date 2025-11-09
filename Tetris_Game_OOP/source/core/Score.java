package core;

public class Score {
    // tổng điểm của người chơi
    private int score;
    // tổng số dòng đã xóa từ đầu game
    private int linesCleared;
    // level hiện tại (ảnh hưởng điểm)
    private int level;

    public Score() {
        this.score = 0;
        this.linesCleared = 0;
        this.level = 1; // bắt đầu ở level 1
    }

    /**
     * Gọi hàm này mỗi khi board xóa được vài dòng trong 1 lần.
     * @param lines số dòng xóa được (thường 1–4)
     */
    public void addLinesCleared(int lines) {
        // nếu không xóa dòng nào thì không cần tính
        if (lines <= 0) return;

        // cộng vào tổng số dòng đã xóa từ đầu game
        this.linesCleared += lines;

        // điểm cơ bản theo số dòng xóa
        int base;
        switch (lines) {
            case 1: base = 100; break;
            case 2: base = 300; break;
            case 3: base = 500; break;
            case 4: base = 800; break;   // xóa 4 dòng một lúc
            default: base = lines * 100; break; // phòng hờ
        }

        // điểm thực nhận = điểm cơ bản * level hiện tại
        int gained = base * this.level;
        this.score += gained;

        // sau khi xóa thêm dòng thì xem có lên level không
        updateLevel();
    }

    // tính lại level dựa trên tổng số dòng đã xóa
    private void updateLevel() {
        // cứ 10 dòng thì tăng 1 level
        this.level = this.linesCleared / 10 + 1;
    }

    // trả về tổng điểm
    public int getScore() {
        return score;
    }

    // trả về tổng dòng đã xóa
    public int getLinesCleared() {
        return linesCleared;
    }

    // trả về level hiện tại
    public int getLevel() {
        return level;
    }

    // đưa về trạng thái ban đầu
    public void reset() {
        this.score = 0;
        this.linesCleared = 0;
        this.level = 1;
    }
}
