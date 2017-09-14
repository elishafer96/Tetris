package model;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Piece {

    enum Orientation {NORTH, EAST, SOUTH, WEST}

    enum Tetromino {
        I(new int[][]{{-2, -1}, {-1, -1}, {0, -1}, {1, -1}}),
        J(new int[][]{{-2, -1}, {-1, -1}, {0, -1}, {0, 0}}),
        L(new int[][]{{-1, 0}, {-1, -1}, {0, -1}, {1, -1}}),
        O(new int[][]{{-1, 0}, {-1, -1}, {0, -1}, {0, 0}}),
        S(new int[][]{{-1, 0}, {0, 0}, {0, -1}, {1, -1}}),
        T(new int[][]{{-1, -1}, {0, 0}, {0, -1}, {1, -1}}),
        Z(new int[][]{{-1, -1}, {0, -1}, {0, 0}, {1, 0}});

        private int[][] coordinates;

        Tetromino(int[][] points) {
            this.coordinates = points;
        }
    }

    private final Tetromino tetromino;
    private Orientation orientation;
    private Point position;

    public Piece(Tetromino t) {
        this.tetromino = t;
        this.orientation = Orientation.NORTH;
        this.position = new Point(5, 0);
    }

    public void setOrientation(Orientation o) {
        this.orientation = o;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public final Point getPosition() {
        return position;
    }

    public final void rotateClockwise() {
        System.out.println("Original orientation: " + orientation.toString());
        switch (orientation) {
            case NORTH:
                orientation = Orientation.EAST;
            break;
            case EAST:
                orientation = Orientation.SOUTH;
            break;
            case SOUTH:
                orientation = Orientation.WEST;
            break;
            case WEST:
                orientation = Orientation.NORTH;
            break;
        }
        System.out.println("New orientation: " + orientation.toString());
        System.out.println();
    }

    public final void rotateCounterClockwise() {
        switch (orientation) {
            case NORTH:
                orientation = Orientation.WEST;
                break;
            case EAST:
                orientation = Orientation.NORTH;
                break;
            case SOUTH:
                orientation = Orientation.EAST;
                break;
            case WEST:
                orientation = Orientation.SOUTH;
                break;
        }
    }

    public static Piece getRandomPiece() {
        Random random = new Random();
        int ordinal = random.nextInt(7);
        Tetromino[] values = Tetromino.values();
        Piece randomPiece = new Piece((values[ordinal]));
        return randomPiece;
    }

    public final List<Point> getPoints() {
//        int[][] coordinates = tetromino.points;
        List<Point> points = new ArrayList<>();
        int x = -5, y = 5;

        for (int[] coordinate : tetromino.coordinates) {
            switch (orientation) {
                case NORTH:
                    x = coordinate[0];
                    y = coordinate[1];
                    break;
                case EAST:
                    x = - coordinate[1] - 1;
                    y = coordinate[0];
                    break;
                case SOUTH:
                    x = coordinate[0];
                    y = - coordinate[1] - 1;
                    break;
                case WEST:
                    x = coordinate[1];
                    y = - coordinate[0] - 1;
                    break;
            }
//            coordinate[0] = x;
//            coordinate[1]= y;
            points.add(new Point(x, y));
        }

        return points;
    }

    public void moveLeft() {
        int leftMostX = 10;
        for (int[] coordinate : tetromino.coordinates)
            leftMostX = Math.min(leftMostX, coordinate[0]);
        if (leftMostX + position.getX() > 0)
            position.setLocation(position.getX() - 1, position.getY());
    }

    public void moveRight() {
        int rightMostX = 0;
        for (int[] coordinate : tetromino.coordinates)
            rightMostX = Math.max(rightMostX, coordinate[0]);
        if (rightMostX + position.getX() < 19)
            position.setLocation(position.getX() + 1, position.getY());
    }

//    public boolean canFall() {
//        int bottomY = 0;
//        for (int[] coordinate : tetromino.coordinates)
//            bottomY = Math.max(bottomY, coordinate[1]);
//        return bottomY + position.getY() < 19;
//    }

    public void moveDown() {
        double newY = position.getY() + 1;
        position.setLocation(position.getX(), newY);
    }

    public void drop() {
        int bottomY = 0, fallDistance;
        for (int[] coordinate : tetromino.coordinates)
            bottomY = Math.max(bottomY, coordinate[1]);
        fallDistance = 19 - bottomY;
        position.setLocation(position.getX(), position.getY() + fallDistance);
    }

    @Override
    public String toString() {
        String result;
        result = tetromino.toString() +  ": " + orientation.toString() + ", ("
                + position.getX() + ", " + position.getY() + ")";
        return result;
    }

    public static void main(String args[]) {
        for (Tetromino t : Tetromino.values()) {
            System.out.println(t.toString() + ":");
//            t.getPoints();
        }
    }
}
