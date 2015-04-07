import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Snowman extends Application {

	 public static final double W = 700; // canvas dimensions.
	 public static final double H = 500;

	 public static final double D = 75;  // diameter of sun
	 
	 public static final double Z = 300; //distance of horizon from top
	    
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		DoubleProperty x  = new SimpleDoubleProperty();
        DoubleProperty y  = new SimpleDoubleProperty();

        Timeline timeline = new Timeline(
        	new KeyFrame(Duration.seconds(0),
                    new KeyValue(x,0),
                    new KeyValue(y, H - D)
            ),
        	new KeyFrame(Duration.seconds(3),
                    new KeyValue(x, W/4),
                    new KeyValue(y, H*.3)
            ),
            new KeyFrame(Duration.seconds(4.5),
            		new KeyValue(x, W/2),
                    new KeyValue(y, H*.2)
            ),
            new KeyFrame(Duration.seconds(6),
            		new KeyValue(x, W*3/4),
                    new KeyValue(y, H*.3)
            ),
            new KeyFrame(Duration.seconds(9),
            		new KeyValue(x, W - D),
                    new KeyValue(y, H - D)
            ),
            new KeyFrame(Duration.seconds(12),
            		new KeyValue(x,0),
                    new KeyValue(y, H - D)
            )
        ); //this above code makes sun rise/set
        
        timeline.setCycleCount(Timeline.INDEFINITE); //

        final Canvas canvas2 = new Canvas(W, H);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                GraphicsContext gc = canvas2.getGraphicsContext2D();
                if(timeline.getCurrentTime().lessThan(Duration.seconds(2.5)) 
                		|| timeline.getCurrentTime().greaterThan(Duration.seconds(7.5))) {
                	gc.setFill(Color.BLACK);
                	gc.fillRect(0, 0, W, H); //when the sun is down, it is night time
                } else {
                	gc.setFill(Color.WHITE);
                	gc.fillRect(0, 0, W, H); //when the sun comes out, it is day time
                }
                gc.setFill(Color.YELLOW); //the sun will only show up when it is above the horizon
                gc.fillOval(x.doubleValue(),y.doubleValue(),D,D);
                gc.setFill(Color.GREEN);
                gc.fillRect(0, Z, W, H);
                
                //code to draw snowman here!
                
                
                
            }
        };

        primaryStage.setScene(
            new Scene(
                new Group(
                    canvas2
                )
            )
        );
        primaryStage.show();

        timer.start();
        timeline.play();
    }
		
		
		//root.getChildren()addAll(canvas,btn);
		//primaryStage.setScene(scene);
		//primaryStage.show();
}
