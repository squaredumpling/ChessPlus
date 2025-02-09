package all;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import all.GamePanel.PColor;
import all.GamePanel.PType;

public abstract class Piece {

    int row; 
    int col;
    PType type;
    PColor color;
    BufferedImage image;

    public Piece(int row, int col, PType type, PColor color) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.color = color;
        fetchImage();
    }

    // returns true if the move is on the board
    protected boolean validBounds(int row, int col) {
        return ((0 <= row) && (row < GamePanel.BOARDSIZE) && 
                (0 <= col) && (col < GamePanel.BOARDSIZE));
    }

    // returns true if the piece can do that move
    protected abstract boolean validMove(int row, int col);

    // if the emove is valid, it moves the piece
    public void move(int row, int col) {
        SidePanel.print(validBounds(row, col) + "v" + validMove(row, col));
        if (validBounds(row, col) && validMove(row, col)) {
            this.row = row;
            this.col = col;
        } else {
            SidePanel.print(" invalid ");
        }
    }

    // private void fetchImage() {
    //     try {
    //         this.image = ImageIO.read(.class.getResourceAsStream(imagePath));
    //     } catch (IOException exception) {
    //         SidePanel.print("error: image fetching\n");
    //     }
    // }


    private void fetchImage() {
        try {
            if (color == PColor.white) {
                image = switch (type) {
                    case pawn -> ImageIO.read(Piece.class.getResourceAsStream("/pawn_white.jpg"));
                    case horse -> ImageIO.read(Piece.class.getResourceAsStream("/horse_white.jpg"));
                    case bishop -> ImageIO.read(Piece.class.getResourceAsStream("/bishop_white.jpg"));
                    case rook -> ImageIO.read(Piece.class.getResourceAsStream("/rook_white.jpg"));
                    case queen -> ImageIO.read(Piece.class.getResourceAsStream("/queen_white.jpg"));
                    case king -> ImageIO.read(Piece.class.getResourceAsStream("/king_white.jpg"));
                    default -> ImageIO.read(Piece.class.getResourceAsStream("/pawn_white.jpg"));
                };
            }
            else {
                image = switch (type) {
                    case pawn -> ImageIO.read(Piece.class.getResourceAsStream("/pawn_black.jpg"));
                    case horse -> ImageIO.read(Piece.class.getResourceAsStream("/horse_black.jpg"));
                    case bishop -> ImageIO.read(Piece.class.getResourceAsStream("/bishop_black.jpg"));
                    case rook -> ImageIO.read(Piece.class.getResourceAsStream("/rook_black.jpg"));
                    case queen -> ImageIO.read(Piece.class.getResourceAsStream("/queen_black.jpg"));
                    case king -> ImageIO.read(Piece.class.getResourceAsStream("/king_black.jpg"));
                    default -> ImageIO.read(Piece.class.getResourceAsStream("/pawn_black.jpg"));
                };
            }
        } catch (IOException exception){
            // handle
        }
    }
    
}