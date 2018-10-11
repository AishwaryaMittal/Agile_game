package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    final int xDimension = 8; // creating a 6x6 maps
    final int yDimension = 4;
    final int scale = 100; // Scale everything by 90. You can experiment here.
    final int playerScale = 50;
    Pane root;
    Scene scene;
    GameBoard gameBoard = GameBoard.getInstance(xDimension, yDimension);
    int[][] gameMap;
    Image pOneImage, pTwoImage;
    ImageView pOneImageView, pTwoImageView;
    Player playerOne, playerTwo;
    Button diceRoll;
    Label diceLabel;


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

        // adds a button to dice roll player 1
        diceRoll = new Button("Roll Player 1");
        diceRoll.setTranslateX(805);
        diceRoll.setTranslateY(100);
        root.getChildren().add(diceRoll);

        // adds a text box for dice player 1
        diceLabel = new Label("Roll: ");
        diceLabel.setTranslateX(850);
        diceLabel.setTranslateY(150);
        root.getChildren().add(diceLabel);


        // places the players images
        placePlayerOne();
        placePlayerTwo();

        // loads images
        loadPlayerOneImage();
        loadPlayerTwoImage();

        // set current images (ImageView) to the Pane
        root.getChildren().add(pOneImageView);
        root.getChildren().add(pTwoImageView);

        //rolls dice if the button is pushed
        diceRoll.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e) {
                rollDice();
            }
        });




    }

    public int rollDice(){
        Random rand = new Random();
        int randInt = rand.nextInt(6) + 1;

        diceLabel.setText("Roll: " + randInt);

        return randInt;

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

    public void placePlayerOne(){
        playerOne = new Player();
    }
    public void placePlayerTwo(){
        playerTwo = new Player();
    }

    public void loadPlayerOneImage(){
        pOneImage = new Image("coolEmoji.png", playerScale, playerScale, true, true);
        pOneImageView = new ImageView(pOneImage);
        pOneImageView.setX(playerOne.getPlayerLocation().x + playerScale/2);
        pOneImageView.setY(playerOne.getPlayerLocation().y + 0);
    }

    public void loadPlayerTwoImage(){
        pTwoImage = new Image("hungryEmoji.png", playerScale, playerScale, true, true);
        pTwoImageView = new ImageView(pTwoImage);
        pTwoImageView.setX(playerTwo.getPlayerLocation().x + playerScale/2);
        pTwoImageView.setY(playerTwo.getPlayerLocation().y + playerScale);
    }


    public static void main(String[] args) {
        launch(args);
    }
}