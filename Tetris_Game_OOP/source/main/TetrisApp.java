package main;

import core.HighScoreManager;
import ui.GamePanel;
import ui.MenuPanel;

import javax.swing.JFrame; // <-- Dùng JFrame (cửa sổ Swing)
import javax.swing.JPanel;
import java.awt.CardLayout; // <-- Dùng CardLayout để "tráo" màn hình

/**
 * Lớp chính của ứng dụng, sử dụng JavaSwing (JFrame).
 * Quản lý việc chuyển đổi giữa các màn hình (Menu và Game)
 */
public class TetrisApp extends JFrame {

    // Tên của các "thẻ" (màn hình)
    public static final String MENU_PANEL = "menu";
    public static final String GAME_PANEL = "game";

    private CardLayout cardLayout;
    private JPanel mainPanel; // Panel chính chứa các "thẻ"
    private GamePanel gamePanel; // Panel màn hình chơi game
    private MenuPanel menuPanel; // Panel màn hình menu
    private HighScoreManager highScoreManager;

    public TetrisApp() {
        // 1. Khởi tạo Trình quản lý điểm
        highScoreManager = new HighScoreManager();

        // 2. Thiết lập Frame (Cửa sổ chính)
        setTitle("Tetris Game OOP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Thoát khi nhấn X
        setResizable(false);

        // 3. Thiết lập CardLayout để chuyển đổi màn hình
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // 4. Tạo các màn hình (Panels)
        
        // Đây là "hành động" (callback) sẽ được gọi khi game kết thúc
        Runnable onGameOver = () -> {
            int finalScore = gamePanel.getScore();
            if (finalScore > highScoreManager.load()) {
                highScoreManager.save(finalScore);
            }
            menuPanel.updateHighScore(); // Cập nhật điểm cao trên menu
            cardLayout.show(mainPanel, MENU_PANEL); // Quay về menu
        };

        // Đây là "hành động" sẽ được gọi khi nhấn "Bắt đầu chơi"
        Runnable onStartGame = () -> {
            gamePanel.resetGame(); // Reset game
            cardLayout.show(mainPanel, GAME_PANEL); // Chuyển sang màn hình game
            gamePanel.startGame(); // Bắt đầu game
        };

        // Tạo 2 màn hình và truyền các "hành động" vào
        gamePanel = new GamePanel(onGameOver);
        menuPanel = new MenuPanel(onStartGame, highScoreManager);

        // 5. Thêm các màn hình vào CardLayout
        mainPanel.add(menuPanel, MENU_PANEL);
        mainPanel.add(gamePanel, GAME_PANEL);

        // 6. Thêm panel chính vào Frame và hiển thị
        add(mainPanel);
        pack(); // Tự động điều chỉnh kích thước cửa sổ
        setLocationRelativeTo(null); // Căn giữa màn hình
    }

    /**
     * Hàm main() để chạy ứng dụng Swing
     */
    public static void main(String[] args) {
        // Đảm bảo code UI chạy trên luồng của Swing (cách làm chuẩn)
        javax.swing.SwingUtilities.invokeLater(() -> {
            new TetrisApp().setVisible(true);
        });
    }
}