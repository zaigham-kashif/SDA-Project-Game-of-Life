package group.gameoflife.TEXTDB_GUI;

import group.gameoflife.BL.cell;
import group.gameoflife.DB.SQL_DB;
import group.gameoflife.DB.textDB;
import group.gameoflife.main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class TextDBLoadSceneController {
    private Stage stage;
    private Graphical_UI GUI;
    private textDB TextDatabase;
    private String StageName;
    @FXML
    private Pane pane;

    public void loadGUI(Graphical_UI GUI) //Saves previous GUI for GO-BACK Implementation
    {
        this.GUI=GUI;
    }
    public void back (ActionEvent e) throws IOException //Go back Implementation
    {
        //TEXTFILE DATABASE
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
        //ENDS HERE


    }
    public void load_TextDB(textDB DB) //Load Text Database
    {
        TextDatabase=DB;
        setList();
    }


    private void setList() //get load games from Text File Database
    {

        String[] savedGames;
        int[] noOfSavedGames=new int[1];
        savedGames=TextDatabase.savedGamesName(noOfSavedGames);

        ListView<String> list_=new ListView<String>(); //List view will be added to the pane

        if (noOfSavedGames[0]!=0)
        {
            for(int i=0;i<noOfSavedGames[0];i++) {
                list_.getItems().add(savedGames[i]);
            }

        }
        //whenever selects a game set StageName
        list_.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<?extends String> observableValue, String o, String t1) {
                StageName=list_.getSelectionModel().getSelectedItem();
            }
        });
        pane.getChildren().add(list_);
    }


    public void load(ActionEvent e) throws IOException
    {
        if (StageName!=null)
        {
            //Text File Database
            //...
            int size[]=new int[2];
            cell loaded_grid[][];
            loaded_grid = TextDatabase.loadGame(StageName,size);

            GUI.clearGrid();
            GUI.setSize(size);
            GUI.setGrid(loaded_grid);


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
            //ENDS HERE

        }
    }

    public void delete(ActionEvent e) throws IOException
    {
        if (StageName!=null)
        {
            //Text File Database
            //...
            TextDatabase.deleteGame(StageName);
            String[] savedGames;
            int[] noOfSavedGames=new int[1];
            savedGames=TextDatabase.savedGamesName(noOfSavedGames);


            ListView<String> list_=new ListView<String>();
            if (noOfSavedGames[0]!=0)
            {
                for(int i=0;i<noOfSavedGames[0];i++) {
                    list_.getItems().add(savedGames[i]);
                }


            }
            list_.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<?extends String> observableValue, String o, String t1) {
                    StageName=list_.getSelectionModel().getSelectedItem();
                }
            });
            pane.getChildren().removeAll();
            pane.getChildren().add(list_);
            StageName=null;
            //...
            //ENDS HERE

        }
    }

}
