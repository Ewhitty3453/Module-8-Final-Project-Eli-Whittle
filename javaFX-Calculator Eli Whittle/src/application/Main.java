package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private MenuBar menuBar;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("JavaFX Application");

        menuBar = createMenuBar();
        mainView();
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");

        MenuItem closeItem = new MenuItem("Close");
        closeItem.setOnAction(event -> System.exit(0));

        menu.getItems().add(closeItem);
        menuBar.getMenus().add(menu);

        if (System.getProperty("os.name").startsWith("Mac")) {
            menuBar.useSystemMenuBarProperty().set(true);
        }

        return menuBar;
    }

    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane content = loader.load();

            BorderPane root = new BorderPane();
            root.setTop(menuBar);
            root.setCenter(content);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            showError("Failed to load view: " + fxmlPath);
        }
    }

    public void mainView() {
        loadView("/view/mainView.fxml");
    }

    public void newView() {
        loadView("/view/NewView.fxml");
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
