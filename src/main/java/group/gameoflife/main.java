package group.gameoflife;

import group.gameoflife.BL.grid;
import group.gameoflife.UI.Graphical_UI;
import group.gameoflife.UI.MainSceneController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        int grid_xSize=50;
        int grid_ySize=70;




        grid game = new grid(grid_xSize,grid_ySize);
        Graphical_UI GUI = new Graphical_UI(game);
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("MainScene.fxml"));

        MainSceneController Controller = new MainSceneController();
        fxmlLoader.setController(Controller);
        Controller.loadGUI(GUI);
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Game Of Life");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}