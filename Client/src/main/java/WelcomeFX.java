import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.function.Consumer;

public class WelcomeFX {

    public static void display(Stage primaryStage) {
        Welcome welcome = new Welcome();
        // Create a title
        Text title = new Text("Welcome to Three Card Poker");
        Font PokerKingsT = Font.loadFont(Welcome.class.getResourceAsStream("PokerKings-Regular.ttf"), 50);
        title.setFont(PokerKingsT);

        Text subtitle = new Text("Please enter IP address and port number");
        Font PokerKingsS = Font.loadFont(Welcome.class.getResourceAsStream("PokerKings-Regular.ttf"), 20);
        subtitle.setFont(PokerKingsS);

        // Create three cards fanned out
        //InputStream cardStream = new FileInputStream("src/main/java/card.png");
        //Image cardImage = new ImageView(new Image(getClass().getResourceAsStream("card.png")));
        //Image cardImage = new Image(cardStream);
        Image ASImage = new Image("Q_S.png", 200, 300, true, true);
        Image KSImage = new Image("K_S.png", 200, 300, true, true);
        Image QSImage = new Image("A_S.png", 200, 300, true, true);
        ImageView card1 = new ImageView(ASImage);
        ImageView card2 = new ImageView(KSImage);
        ImageView card3 = new ImageView(QSImage);
        HBox cards = new HBox(card1, card2, card3);
        cards.setSpacing(-150);
        cards.setAlignment(Pos.CENTER);
        card1.setRotate(-10);
        card1.setTranslateY(25);
        card2.setRotate(0);
        card2.setTranslateY(0);
        card3.setRotate(10);
        card3.setTranslateY(25);

        // Create a text field for IP address entry
        Text ipLabel = new Text("IP Address:");
        TextField ipTextField = new TextField();
        ipTextField.setPromptText("Enter IP address");
        ipLabel.setFont(Font.font("Cambria"));
        ipTextField.setFont(Font.font("Cambria"));
        HBox ipBox = new HBox(ipLabel, ipTextField);
        ipBox.setAlignment(Pos.CENTER);
        ipBox.setSpacing(20);
        ipTextField.setFocusTraversable(false);

        // Create a text field for port entry
        Text portLabel = new Text("Port:");
        TextField portTextField = new TextField();
        portTextField.setPromptText("Enter port number");
        portLabel.setFont(Font.font("Cambria"));
        portTextField.setFont(Font.font("Cambria"));
        HBox portBox = new HBox(portLabel, portTextField);
        portBox.setTranslateX(17);
        portBox.setAlignment(Pos.CENTER);
        portBox.setSpacing(20);
        portTextField.setFocusTraversable(false);

        Label statusLabel = new Label();
        statusLabel.setFont(Font.font("Cambria"));

        // Create a connect button
        Button connectButton = new Button("Join Game");
        //connectButton.requestFocus();
        connectButton.setTranslateX(2);
        connectButton.setOnAction(event -> {
            try {
                welcome.setIP(ipTextField.getText()); //doesn't work with '.', need event handler
                welcome.setPort(Integer.parseInt(portTextField.getText()));
                ClientThread client = new ClientThread(callback, welcome.getIP(), welcome.getPort());
                statusLabel.setText("Connected!");
                FXML_Control fxml = new FXML_Control();
                fxml.client(client);
                //connectToServer();
                //joinGame();
                // start button action
                SceneControl sc = new SceneControl();
                sc.game(primaryStage);

            } catch (NumberFormatException e){
                // Handle the case where the input is not a valid integer
                // Display an error message
                statusLabel.setText("Invalid input. Please enter integers only.");
                // Clear the text fields
                ipTextField.setText("");
                portTextField.setText("");
            } //catch ()
        });
		/*connectButton.setOnAction(event -> {
			connectToServer();
			joinGame();
		});*/

        // Create a VBox to hold all the elements
        VBox vbox = new VBox(subtitle, ipBox, portBox, connectButton, statusLabel);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(20));

        //HBox hbox = new HBox(cards, vbox);
        //hbox.setPadding(new Insets(30));
        //hbox.setSpacing(10);

        // Create a StackPane to hold the VBox and center it in the scene
        // StackPane root = new StackPane(fscene);
        BorderPane root = new BorderPane();
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setMargin(title, new Insets(30, 0, 0, 0));
        root.setCenter(cards);
        root.setRight(vbox);

        // Create a scene with a CSS style sheet
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("style.css");

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.setWidth(825);
        primaryStage.setHeight(625);
        primaryStage.setTitle("Three Card Poker");

        root.setOnMouseClicked(event -> {
            Node node = event.getPickResult().getIntersectedNode();
            if (node instanceof TextField) {
                // Mouse click was inside a text field, do nothing
            } else {
                // Mouse click was outside a text field, remove focus from all text fields
                root.requestFocus();
            }
        });

        primaryStage.show();

    }
    private static Consumer<Serializable> callback = data -> {
        // Handle received data as needed
        System.out.println("Received data: " + data);
    };

}
