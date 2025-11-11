package ui;

import core.Config;
import core.HighScoreManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Màn hình Menu chính
 */
public class MenuPanel extends JPanel {

    private JLabel highScoreLabel;
    private HighScoreManager highScoreManager;

    public MenuPanel(Runnable onStartGame, HighScoreManager manager) {
        this.highScoreManager = manager;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100)); // Căn lề

        // 1. Label Điểm cao
        highScoreLabel = new JLabel();
        highScoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        highScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        updateHighScore(); // Tải và hiển thị điểm

        // 2. Nút Bắt đầu
        JButton startButton = new JButton("Bắt đầu chơi");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.addActionListener(e -> onStartGame.run());

        add(highScoreLabel, BorderLayout.NORTH);
        add(startButton, BorderLayout.CENTER);
    }

    /**
     * Tải lại và hiển thị điểm cao nhất
     */
    public void updateHighScore() {
        highScoreLabel.setText("Điểm cao nhất: " + highScoreManager.load());
    }
}