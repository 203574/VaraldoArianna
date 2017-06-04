package it.polito.tdp.ariannavaraldo.aircapmgt;
	
import it.polito.tdp.ariannavaraldo.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AirCapMgt.fxml"));
			BorderPane root = (BorderPane) loader.load();
			Model m = new Model();
			AirCapMgtController contr = loader.getController();
			contr.setModel(m);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
