package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static view.Tetris.HEIGHT;
import static view.Tetris.WIDTH;

public class Board extends Canvas {

    public static final int BOARD_WIDTH = 10, BOARD_HEIGHT = 20;

    private List<Piece> pieces;
    private Piece currentPiece;
    private Timer timer;

    public Board() {
        super(WIDTH, HEIGHT);
        init();
        pieces = new ArrayList<>();
        currentPiece = Piece.getRandomPiece();
        timer = new Timer();
        startGame();
    }

    private void init() {
        GraphicsContext gc = getGraphicsContext2D();

        gc.fillRect(0, 0, Tetris.WIDTH, Tetris.HEIGHT);

//        gc.setStroke(Color.WHITE);
//        for (int i = 0; i < Tetris.WIDTH; i += Tetris.WIDTH / 10)
//            gc.strokeLine(i, 0, i, Tetris.HEIGHT);
//        for (int i = 0; i < Tetris.HEIGHT; i += Tetris.HEIGHT / 20)
//            gc.strokeLine(0, i, Tetris.WIDTH, i);
    }

    public void startGame() {
        timer.scheduleAtFixedRate(new TetrisTimerTask(), 1000, 1000);
    }

    private void update() {
        GraphicsContext gc = getGraphicsContext2D();
        if (pieceCanDrop()) {
            gc.setFill(Color.BLACK);
            for (Point point : currentPiece.getPoints()) {
                gc.fillRect((point.getX() + currentPiece.getPosition().getX()
                ) * 30, (point.getY() + currentPiece.getPosition().getY()) *
                        30, 30, 30);
            }
            currentPiece.moveDown();
        } else {
            pieces.add(currentPiece);
            currentPiece = Piece.getRandomPiece();

        }

        gc.setFill(Color.WHITE);

        for (Piece piece : pieces) {
            Point placement = piece.getPosition();
            for (Point point : piece.getPoints()) {
                gc.fillRect((point.getX() + placement.getX()) * 30,
                        (point.getY() + placement.getY()) * 30, 30, 30);
            }
        }

        for (Point point : currentPiece.getPoints()) {
            Point placement = currentPiece.getPosition();
            gc.fillRect((point.getX() + placement.getX()) * 30, (point.getY()
                    + placement.getY()) * 30, 30, 30);
        }
    }

    private boolean pieceCanDrop() {
        int lowestY = 0;
        List<Point> currentPoints = new ArrayList<>();
        for (Point p : currentPiece.getPoints()) {
            currentPoints.add(new Point(
                    (int) (p.getX() + currentPiece.getPosition().getX()),
                    (int) (p.getY() + currentPiece.getPosition().getY())));
        }

        for (Point currentPoint : currentPoints) {
            lowestY = Math.max(lowestY, (int) currentPoint.getY());
            for (Piece placedPiece : pieces) {
                for (Point placedPoint : placedPiece.getPoints()) {
                    if (placedPoint.getX() + placedPiece.getPosition().getX()
                    == currentPoint.getX() &&
                            placedPoint.getY() + placedPiece.getPosition().getY()
                                    == currentPoint.getY() + 1) {
                        return false;
                    }
                }
            }
        }

        if (lowestY >= 19)
            return false;

        return true;
    }

    private final class TetrisTimerTask extends TimerTask {

        // TODO: needs work
        @Override
        public void run() {
//            if (pieceCanDrop())
//                currentPiece.moveDown();
//            else {
//                pieces.add(currentPiece);
//                currentPiece = Piece.getRandomPiece();
//            }

                //currentPiece = Piece.getRandomPiece();
            update();
        }
    }
}
