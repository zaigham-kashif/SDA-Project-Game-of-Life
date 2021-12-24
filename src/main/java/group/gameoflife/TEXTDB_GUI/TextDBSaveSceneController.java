package group.gameoflife.TEXTDB_GUI;

import group.gameoflife.DB.SQL_DB;
import group.gameoflife.DB.textDB;
import group.gameoflife.main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TextDBSaveSceneController {
    private Stage stage;
    private  GridPane Grid_;
    private Graphical_UI GUI;
    private textDB TextDatabase;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField Stage_text;
    @FXML
    private Label Status;

    public void loadGUI(Graphical_UI GUI) //load GUI object for GO-BACK implentation
    {
        this.GUI=GUI;
        int[] gridSize= GUI.getGridSize();
        gridSize = this.GUI.getGridSize();
        Grid_ = new GridPane();
        Grid_.setPadding(new Insets(5));
        Grid_.setHgap(5);
        Grid_.setVgap(5);
        Grid_.setScaleX(0.5);
        Grid_.setScaleY(0.5);
        Grid_.setStyle("-fx-background-color: #090510;");
        for (int r = 0; r < gridSize[0]; r++) {
            for (int c = 0; c < gridSize[1]; c++) {

                Button button = new Button(String.valueOf("  "));
                button.setId(Integer.toString(r)+":"+Integer.toString(c));
                if (GUI.isCellAlive(r,c)) {
                    button.setStyle("-fx-background-color: #FFFFFF; -fx-border-color:#55031F ; -fx-border-width:2; ");
                }
                else{
                    button.setStyle("-fx-background-color: #51515A; -fx-border-color:#55031F ; -fx-border-width:2;");
                }
                Grid_.add(button, c, r);
            }
        }
        this.scrollPane.setContent(Grid_);


    }
    public void load_TextDB(textDB DB) //Load Text Database
    {
        TextDatabase=DB;
    }

    public void save (ActionEvent e)
    {
        //Text File Database
        //...
        String name;
        name = Stage_text.getText();

        if (name != null)
        {
            int check = TextDatabase.saveGame(name);
            if (check==-1)
            {
                Status.setText("Same Stage Name already exists !");
            }
            else{

                Status.setText("Game Saved !!");
            }
        }
        //...
        //Ends Here


    }

    public void back (ActionEvent e) throws IOException
    {
        //Text File Database
        //...
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("MainScene_TEXTDB.fxml"));
        MainSceneController Controller = new MainSceneController();
        fxmlLoader.setController(Controller);
        Scene scene = new Scene(fxmlLoader.load(), 970, 730);
        scene.getStylesheets().add(main.class.getResource("Stylesheet.css").toExternalForm());
        stage.setTitle("Game Of Life");
        stage.setScene(scene);
        stage.show();
        Controller.setGamefromGUI(GUI);
        Controller.loadTextDB(TextDatabase);
        //...
        //Ends Here


    }
}
