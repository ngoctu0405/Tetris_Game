package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Một class riêng chỉ để quản lý việc đọc và ghi
 * một file điểm cao duy nhất (highscore.txt).
 * File này là Java thuần túy, nó hoạt động với cả JavaFX và JavaSwing.
 */
public class HighScoreManager {
    
    // Tên file để lưu điểm
    private final File file = new File("highscore.txt");

    /**
     * Tải điểm cao nhất từ file.
     * @return Điểm cao nhất đã lưu, hoặc 0 nếu file không tồn tại/bị lỗi.
     */
    public int load() {
        // Nếu file không tồn tại, trả về 0
        if (!file.exists()) {
            return 0;
        }
        
        // Dùng try-with-resources để tự động đóng file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line != null && !line.isEmpty()) {
                // Chuyển dòng chữ đọc được thành con số
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            // Nếu file lỗi hoặc không đọc được số, coi như điểm là 0
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    /**
     * Lưu điểm cao nhất mới vào file.
     * @param score Điểm cao nhất mới để ghi đè.
     */
    public void save(int score) {
        // Dùng try-with-resources để tự động đóng file
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            // Ghi con số (điểm) vào file
            pw.println(score);
        } catch (IOException e) {
            e.printStackTrace(); // In lỗi ra console nếu không lưu được
        }
    }
}