package game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    final int xDimension = 8; // creating a 6x6 maps
    final int yDimension = 4;
    final int scale = 100; // Scale everything by 90. You can experiment here.
    Pane root;
    Scene scene;
    GameBoard gameBoard = GameBoard.getInstance(xDimension, yDimension);
    int[][] gameMap;

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = new AnchorPane();
        scene = new Scene(root, 1000, 450);
        //Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
        primaryStage.setTitle("Agile Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        gameMap = gameBoard.getMap();
        drawMap();
        gameBoard.displayMap();
    }

    public void drawMap() {

        for(int x = 0; x < xDimension; x++){
            for(int y = 0; y < yDimension; y++){
                Rectangle rect = new Rectangle(x*scale,y*scale,scale,scale);
                rect.setStroke(Color.BLACK); // We want the black outline
                if(x == 0 && y == 0){
                    rect.setFill(Color.GREEN); // fills the board with random colors
                }
                else if(x == 7 && y == 3){
                    rect.setFill(Color.RED); // fills the board with random colors
                }
                else{
                    rect.setFill(Color.color(Math.random(), Math.random(), Math.random())); // fills the board with random colors
                }
                root.getChildren().add(rect); // Add to the node tree in the pane
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
