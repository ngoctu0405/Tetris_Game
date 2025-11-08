package core.blocks;

import core.Position;
import javafx.scene.paint.Color;

/*
 * BlockJ - khối hình chữ J trong Tetris
 * - rot: trạng thái quay (0..3)
 * - tiles: mảng 4 vị trí của các ô thuộc khối
 * Ghi chú: pivot (tọa độ quay) được lấy là tiles[2]
 */
public class BlockJ extends Block {
    // trạng thái quay hiện tại (0..3)
    private int rot = 0;

    public BlockJ() {
        // màu sắc của khối J
        color = Color.DARKBLUE;
        // vị trí ban đầu của 4 ô (dùng hệ tọa độ row, col)
        tiles = new Position[] { new Position(-1, 3), new Position(0, 3), new Position(0, 4), new Position(0, 5) };
    }

    @Override
    public void rotate() {
        // lấy bản sao pivot (ô trung tâm dùng để quay)
        Position c = tiles[2].copy();
        // các trường hợp quay theo trạng thái rot (các vị trí tính theo pivot)
        if (rot == 0)
            tiles = new Position[] {
                new Position(c.row - 1, c.col),
                new Position(c.row - 1, c.col + 1),
                new Position(c.row, c.col + 1),
                new Position(c.row + 1, c.col + 1)
            };
        else if (rot == 1)
            tiles = new Position[] {
                new Position(c.row, c.col + 1),
                new Position(c.row + 1, c.col + 1),
                new Position(c.row + 1, c.col),
                new Position(c.row + 1, c.col - 1)
            };
        else if (rot == 2)
            tiles = new Position[] {
                new Position(c.row + 1, c.col),
                new Position(c.row + 1, c.col - 1),
                new Position(c.row, c.col - 1),
                new Position(c.row - 1, c.col - 1)
            };
        else
            tiles = new Position[] {
                new Position(c.row, c.col - 1),
                new Position(c.row - 1, c.col - 1),
                new Position(c.row - 1, c.col),
                new Position(c.row - 1, c.col + 1)
            };
        // cập nhật trạng thái quay (vòng về 0 sau 3)
        rot = (rot + 1) % 4;
    }

    @Override
    public Block copy() {
        // tạo bản sao của khối hiện tại, bao gồm trạng thái rot
        BlockJ newBlock = new BlockJ();
        newBlock.copyStateFrom(this);
        newBlock.rot = this.rot;
        return newBlock;
    }
}