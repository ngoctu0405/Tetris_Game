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
    
    private boolean isPaused = false;
    // BIẾN MỚI: Thêm trạng thái Game Over
    private boolean isGameOverScreen = false; 

    public GamePanel(Runnable onGameOverCallback) {
        this.onGameOverCallback = onGameOverCallback;
        
        setPreferredSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        setBackground(new Color(11, 11, 11));
        
        setFocusable(true); 
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // 1. Xử lý phím Dừng (P)
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    // Không cho phép Dừng khi đã Game Over
                    if (!isGameOverScreen) {
                        togglePause();
                    }
                    return; 
                }

                // 2. Xử lý phím Thoát (Escape)
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    gameLoop.stop();
                    onGameOverCallback.run(); // Quay về menu
                    return;
                }
                
                // 3. XỬ LÝ MỚI: Nếu đang ở màn hình Game Over
                if (isGameOverScreen) {
                    // Chỉ chấp nhận phím Enter
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        onGameOverCallback.run(); // Quay về menu
                    }
                    return; // Bỏ qua tất cả các phím khác
                }

                // 4. Nếu đang Dừng, bỏ qua các phím di chuyển
                if (isPaused || control == null) { // (Đã xóa control.isGameOver() khỏi đây)
                    return;
                }

                // 5. Xử lý các phím chơi game
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT: control.moveLeft(); break;
                    case KeyEvent.VK_RIGHT: control.moveRight(); break;
                    case KeyEvent.VK_DOWN: control.softDrop(); break;
                    case KeyEvent.VK_UP: control.rotate(); break;
                    case KeyEvent.VK_SPACE: control.hardDrop(); break;
                }
                repaint();
            }
        });

        // Khởi tạo Game
        resetGame();

        // Khởi tạo Game Loop (dùng Swing Timer)
        gameLoop = new Timer((int)Config.BASE_DELAY_MS, e -> {
            
            // XỬ LÝ MỚI: Kiểm tra Game Over TRƯỚC
            if (control.isGameOver()) {
                gameLoop.stop();
                isGameOverScreen = true; // Đặt trạng thái "Game Over"
                repaint(); // Vẽ lại lần cuối để hiển thị màn hình
                return; // KHÔNG gọi onGameOverCallback.run() ở đây nữa
            }
            
            // Logic game bình thường
            control.tick();
            
            int currentLevel = control.getScore().getLevel();
            int newDelay = (int) Math.max(Config.BASE_DELAY_MS - (currentLevel * 50), 100);
            gameLoop.setDelay(newDelay);
            
            repaint();
        });
    }
    
    /**
     * Dừng hoặc tiếp tục game
     */
    public void togglePause() {
        if (control.isGameOver()) return;

        isPaused = !isPaused;
        if (isPaused) {
            gameLoop.stop();
        } else {
            gameLoop.start();
        }
        repaint();
    }

    /**
     * Bắt đầu game
     */
    public void startGame() {
        isPaused = false;
        isGameOverScreen = false; // Đảm bảo màn hình game over tắt
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
            gameLoop.setDelay((int)Config.BASE_DELAY_MS);
        }
        isPaused = false;
        isGameOverScreen = false; // CẬP NHẬT: Reset trạng thái Game Over
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
        super.paintComponent(g);

        if (control == null) return; 

        Board board = control.getBoard();
        Block current = control.getCurrent();

        // 1. Vẽ Lưới và các khối đã "chết"
        // (Code vẽ lưới của bạn...)
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

        // 2. Vẽ khối gạch đang rơi
        // (Code vẽ khối gạch 'current' của bạn...)
        if (current != null) {
            g.setColor(current.getColor());
            for (var p : current.getTiles()) {
                if (p.row < 0) continue;
                g.fillRect(p.col * Config.TILE, p.row * Config.TILE, Config.TILE - 1, Config.TILE - 1);
            }
        }
        
        // 3. Vẽ HUD
        // (Code vẽ HUD 'Score', 'Level', 'Lines' của bạn...)
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        
        int hudX = (Config.COLS * Config.TILE) + 20; 
        g.drawString("Score: " + control.getScore().getScore(), hudX, 50);
        g.drawString("Level: " + control.getScore().getLevel(), hudX, 80);
        g.drawString("Lines: " + control.getScore().getLinesCleared(), hudX, 110);
        
        // 4. Vẽ màn hình "PAUSED" (nếu đang dừng)
        if (isPaused) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, Config.COLS * Config.TILE, Config.ROWS * Config.TILE);
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            String pauseText = "ĐÃ DỪNG";
            int textWidth = g.getFontMetrics().stringWidth(pauseText);
            int textX = ((Config.COLS * Config.TILE) / 2) - (textWidth / 2);
            int textY = (Config.ROWS * Config.TILE) / 2;
            g.drawString(pauseText, textX, textY);
        }
        
        // 5. VẼ MỚI: Hiển thị màn hình "GAME OVER"
        if (isGameOverScreen) {
            // Vẽ một lớp phủ mờ (che cả game và HUD)
            g.setColor(new Color(0, 0, 0, 200)); // Mờ hơn
            g.fillRect(0, 0, getWidth(), getHeight()); // Che toàn bộ panel
            
            // Vẽ chữ "GAME OVER"
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            String gameOverText = "GAME OVER";
            int textWidth = g.getFontMetrics().stringWidth(gameOverText);
            int textX = (getWidth() / 2) - (textWidth / 2);
            int textY = getHeight() / 2 - 40;
            g.drawString(gameOverText, textX, textY);

            // Vẽ "Nút" (chữ hướng dẫn)
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            String continueText = "Nhấn [Enter] để quay về Menu";
            textWidth = g.getFontMetrics().stringWidth(continueText);
            textX = (getWidth() / 2) - (textWidth / 2);
            g.drawString(continueText, textX, textY + 60);
        }
    }
}