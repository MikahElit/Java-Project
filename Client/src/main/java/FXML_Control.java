import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class FXML_Control { // implements Initializable {
    ClientThread client;
    @FXML
    private Button drawButton;
    @FXML
    private Button playButton;
    @FXML
    private Button foldButton;
    @FXML
    private TextField balanceField;
    @FXML
    private TextField anteField;
    @FXML
    private TextField pairPlusField;
    @FXML
    private TextField playField;
    @FXML
    private Image dealerCard1;
    @FXML
    private Image dealerCard2;
    @FXML
    private Image dealerCard3;
    @FXML
    private Image playerCard1;
    @FXML
    private Image playerCard2;
    @FXML
    private Image playerCard3;
    //@FXML
    //private Label balance;
    @FXML
    private Label pairPlus;
    @FXML
    private Label ante;
    @FXML
    private Label play;
    @FXML
    private Label winnings;
    @FXML
    private static Label instructions;
    @FXML
    private Label the;
    //onAction="#handleSubmitButtonAction" need actions in fxml

    /*@Override
    public void initialize(URL location, ResourceBundle resources) {
        // initialize code here
    }*/

    private static Scene scene;
    private static BorderPane root;
    public static void display(Stage primaryStage) {
        //BorderPane root = new BorderPane();
        FXMLLoader loader = new FXMLLoader(FXML_Control.class.getResource("poker_scene1.2.fxml"));
        //Parent root = loader.load;

        try {
            //gameScene(loader);
            root = loader.load();
            FXML_Control controller = loader.getController();
            root.setMinWidth(800);
            root.setMinHeight(600);
            scene = new Scene(root);
            //scene.getStylesheets().add(FXML_Control.class.getResource("style.css").toExternalForm());
            root.setOnMouseClicked(event -> {
                Node node = event.getPickResult().getIntersectedNode();
                if (node instanceof TextField) {
                    // Mouse click was inside a text field, do nothing
                } else {
                    // Mouse click was outside a text field, remove focus from all text fields
                    root.requestFocus();
                }
            });
            //controller.instructions.setText("Enter Balance");
            //instructions.setText("Enter Balance");
            //instructions.setOpacity(0.9);

            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Scene scene = new Scene(root, 800, 600);
        //scene.getStylesheets().add("style.css");
        //primaryStage.setScene(scene);
        /*primaryStage.setWidth(800);
        primaryStage.setHeight(600);*/
        /*root.setOnMouseClicked(event -> {
            Node node = event.getPickResult().getIntersectedNode();
            if (node instanceof TextField) {
                // Mouse click was inside a text field, do nothing
            } else {
                // Mouse click was outside a text field, remove focus from all text fields
                root.requestFocus();
            }
        });*/

        primaryStage.setTitle("Three Card Poker");
        primaryStage.show();
    }
    Game game = new Game();

    public void client(ClientThread client) {
        this.client = client;
    }
    public void handleBalanceField(ActionEvent event) {
        game.setBalance(Integer.parseInt(balanceField.getText()));
        client.sendValues("MONEY#" + Integer.toString(game.getBalance()));
        balanceField.setDisable(true);
//MONEY#500
        //next instruction
    }
    @FXML
    public void handleAnteField(ActionEvent event) {
        game.setAnte(Integer.parseInt(anteField.getText()));
        client.sendValues("ANTE#" + Integer.toString(game.getAnte()));
        anteField.setDisable(true);
    }

    @FXML
    public void handlePairPlusField(ActionEvent event) {
        game.setPairPlus(Integer.parseInt(pairPlusField.getText()));
        client.sendValues("PAIR#" + Integer.toString(game.getPairPlus()));
        pairPlusField.setDisable(true);
    }

    @FXML
    public void handleDrawButton(ActionEvent event) {
        // handle button click here
        // hand out cards (opacity)
        // flip player cards (swap)
        Timeline timeline = new Timeline();
        for (int i = 1; i <= 6; i++) {
            ImageView image = new ImageView(dealerCard1);
            image.setOpacity(1);

            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(i + 1)));
        }
        timeline.play();
    }

    @FXML
    public void handleFoldButton(ActionEvent event) {
        // handle button click here
        //
    }

    @FXML
    public void handlePlayButton(ActionEvent event) {
        // handle button click here
    }

    @FXML
    public void handleFreshStartButton(ActionEvent event) {
        // handle button click here
        //reset

    }
    String[] colors = {
            "#2E8B57", // Sea Green
            "#4169E1", // Royal Blue
            "#8B008B", // Dark Magenta
            "#FFD700", // Gold
            "#FFA07A", // Light Salmon
            "#FF4500", // Orange Red
            "#DA70D6", // Orchid
            "#00BFFF", // Deep Sky Blue
            "#FF69B4", // Hot Pink
            "#ADFF2F", // Green Yellow
            "#2E8B57"  // Stock
    };

    int count = 0;
    @FXML
    public void handleNewLookButton(ActionEvent event) {
        // handle button click here
        //change background colors in array
        //change text color

        root.setStyle("-fx-background-color: " + colors[count] + ";");
        count++;
    }

    @FXML
    public void handleExitButton(ActionEvent event) {
        System.exit(0);
    }



}