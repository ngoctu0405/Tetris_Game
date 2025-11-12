package ui;

import core.*;
import core.blocks.Block;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Panel chính của game, thay thế Window.java và Render.java
 * Nó tự vẽ (paintComponent) và tự lắng nghe phím (KeyListener)
 */
public class GamePanel extends JPanel {

    private gameControl control;
    private final Runnable onGameOverCallback;
    private Timer gameLoop;

    public GamePanel(Runnable onGameOverCallback) {
        this.onGameOverCallback = onGameOverCallback;
        
        // Cài đặt Panel
        setPreferredSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        setBackground(new Color(11, 11, 11)); // Màu nền (#0b0b0b)
        
        // Thiết lập lắng nghe phím
        setFocusable(true); 
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (control == null || control.isGameOver()) return;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT: control.moveLeft(); break;
                    case KeyEvent.VK_RIGHT: control.moveRight(); break;
                    case KeyEvent.VK_DOWN: control.softDrop(); break;
                    case KeyEvent.VK_UP: control.rotate(); break;
                    case KeyEvent.VK_SPACE: control.hardDrop(); break;
                    case KeyEvent.VK_P: /* pause */ break;
                }
                repaint();
            }
        });

        // Khởi tạo Game
        resetGame();

        // Khởi tạo Game Loop (dùng Swing Timer)
        gameLoop = new Timer((int)Config.BASE_DELAY_MS, e -> {
            if (control.isGameOver()) {
                gameLoop.stop();
                onGameOverCallback.run();
                return;
            }
            
            control.tick();
            
            // 1. Lấy level hiện tại
            int currentLevel = control.getScore().getLevel();
            
            // 2. SỬA LỖI LOGIC: Đổi 500 thành 50 để tăng tốc từ từ
            int newDelay = (int) Math.max(Config.BASE_DELAY_MS - (currentLevel * 50), 100);
            
            // 3. Cập nhật tốc độ mới cho Timer
            gameLoop.setDelay(newDelay);
            
            repaint(); // Yêu cầu vẽ lại
        });
    }

    /**
     * Bắt đầu game
     */
    public void startGame() {
        requestFocusInWindow(); 
        gameLoop.start();
    }

    /**
     * Reset game về ban đầu
     */
    public void resetGame() {
        this.control = new gameControl(new DefaultBlockFactory());
        if (gameLoop != null) {
            gameLoop.stop();
            // SỬA LỖI LOGIC: Reset tốc độ Timer về ban đầu
            gameLoop.setDelay((int)Config.BASE_DELAY_MS);
        }
        repaint();
    }

    /**
     * Lấy điểm số cuối cùng
     */
    public int getScore() {
        return control.getScore().getScore();
    }


    /**
     * Hàm vẽ chính
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Xóa màn hình với màu nền

        if (control == null) return; 

        Board board = control.getBoard();
        Block current = control.getCurrent();

        // 1. Vẽ lưới (Grid) và các khối đã "chết"
        var grid = board.getGrid();
        for (int r = 0; r < Config.ROWS; r++) {
            for (int c = 0; c < Config.COLS; c++) {
                if (grid[r][c] != null) {
                    g.setColor(grid[r][c].color);
                    g.fillRect(c * Config.TILE, r * Config.TILE, Config.TILE - 1, Config.TILE - 1);
                } else {
                    g.setColor(new Color(34, 34, 34)); // Màu xám (#222)
                    g.drawRect(c * Config.TILE, r * Config.TILE, Config.TILE, Config.TILE);
                }
            }
        }

        // 2. Vẽ khối gạch đang rơi (current)
        if (current != null) {
            g.setColor(current.getColor());
            for (var p : current.getTiles()) {
                if (p.row < 0) continue;
                g.fillRect(p.col * Config.TILE, p.row * Config.TILE, Config.TILE - 1, Config.TILE - 1);
            }
        }
        
        // 3. Vẽ HUD (Điểm, Level, Lines)
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        
        int hudX = (Config.COLS * Config.TILE) + 20; // Vị trí bên phải
        g.drawString("Score: " + control.getScore().getScore(), hudX, 50);
        g.drawString("Level: " + control.getScore().getLevel(), hudX, 80);
        g.drawString("Lines: " + control.getScore().getLinesCleared(), hudX, 110);
    }
}