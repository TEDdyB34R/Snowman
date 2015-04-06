import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Snowman extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, 400, 400);
		
		Button btn = new Button("Hello");
		
		root.getChildren().add(btn);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	

}
