package core.blocks;

import core.Position;
import java.awt.Color; // <-- Nhập Color từ java.awt

public class BlockL extends Block {
    private int rot=0; // Biến lưu trạng thái xoay (0-3)
    
    public BlockL() {
        color = Color.ORANGE; // <-- Đặt màu cam cho khối L
        // Khởi tạo vị trí ban đầu của 4 ô vuông
        tiles = new Position[] { new Position(-1,5), new Position(0,3), new Position(0,4), new Position(0,5) };
    }
    
    // Hàm xoay khối L theo chiều kim đồng hồ
    @Override public void rotate() {
        Position c = tiles[2].copy(); // Lấy vị trí trung tâm xoay
        if (rot==0) tiles = new Position[] { new Position(c.row-1,c.col), new Position(c.row,c.col), new Position(c.row+1,c.col), new Position(c.row+1,c.col+1) };
        else if (rot==1) tiles = new Position[] { new Position(c.row,c.col+1), new Position(c.row,c.col), new Position(c.row,c.col-1), new Position(c.row-1,c.col-1) };
        else if (rot==2) tiles = new Position[] { new Position(c.row+1,c.col), new Position(c.row,c.col), new Position(c.row-1,c.col), new Position(c.row-1,c.col-1) };
        else tiles = new Position[] { new Position(c.row,c.col-1), new Position(c.row,c.col), new Position(c.row,c.col+1), new Position(c.row+1,c.col+1) };
        rot = (rot+1)%4; // Tăng trạng thái xoay (0->1->2->3->0)
    }

    // Hàm sao chép khối L với tất cả trạng thái
    @Override
    public Block copy() {
        BlockL newBlock = new BlockL(); // Tạo khối L mới
        newBlock.copyStateFrom(this); // Sao chép trạng thái từ khối hiện tại
        newBlock.rot = this.rot; // Sao chép trạng thái xoay
        return newBlock;
    }
}