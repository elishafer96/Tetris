package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public final class Tetris extends Application {

    public static final int WIDTH = 300, HEIGHT = 600;
    private static String up = "\u2191";
    private static String left = "\u2190";
    private static String right = "\u2192";
    private static String down = "\u2193";

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Tetris");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);

        // Creates the menu
        VBox menu = new VBox();

        Button newGameBtn = new Button("New Game");
        newGameBtn.setOnAction(event -> System.out.println("New Game..."));

        Button loadGameBtn = new Button("Load Game");
        loadGameBtn.setOnAction(event -> System.out.println("Load Game..."));

        Button exitGameBtn = new Button("Exit Game");
        exitGameBtn.setOnAction(event -> System.out.println("Exit Game..."));

        menu.getChildren().addAll(newGameBtn, loadGameBtn, exitGameBtn);

        // Creates control menu
        VBox controls = new VBox();

        Button upCtrlBtn = new Button("Up: " + up);
        Button downCtrlBtn = new Button("Down: " + down);
        Button leftCtrlBtn = new Button("Left: " + left);
        Button rightCtrlBtn = new Button("Right: " + right);

        controls.getChildren().addAll(upCtrlBtn, downCtrlBtn, leftCtrlBtn, rightCtrlBtn);


        // Creates the board
//        Canvas tetrisBoard = new Canvas(WIDTH, HEIGHT);
//        GraphicsContext gc = tetrisBoard.getGraphicsContext2D();
//
//        gc.fillRect(0, 0, WIDTH, HEIGHT);
//
//        gc.setStroke(Color.WHITE);
//        for (int i = 0; i < WIDTH; i += WIDTH / 10)
//            gc.strokeLine(i, 0, i, HEIGHT);
//        for (int i = 0; i < HEIGHT; i += HEIGHT / 20)
//            gc.strokeLine(0, i, WIDTH, i);


        root.setCenter(new Board());
        root.setLeft(menu);
        root.setRight(controls);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}