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
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Snowman extends Application {

	 public static final double W = 700; // canvas dimensions.
	 public static final double H = 500;

	 public static final double D = 75;  // diameter of sun
	 
	 public static final double Z = 300; //distance of horizon from top
	 
	 public static final double S = 100; //diameter of snowman's body
	    
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		DoubleProperty x  = new SimpleDoubleProperty();
        DoubleProperty y  = new SimpleDoubleProperty();

        Timeline timeline = new Timeline( //code for sunrise/sunset
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
            )
        );
        
        timeline.setCycleCount(Timeline.INDEFINITE); //

        final Canvas canvas2 = new Canvas(W, H);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                GraphicsContext gc = canvas2.getGraphicsContext2D();
                if(timeline.getCurrentTime().lessThan(Duration.seconds(2.1)) 
                		|| timeline.getCurrentTime().greaterThan(Duration.seconds(7.5))) {
                	gc.setFill(Color.BLACK);
                	gc.fillRect(0, 0, W, H); //when the sun is down, it is night time
                } else {
                	gc.setFill(Color.WHITE);
                	gc.fillRect(0, 0, W, H); //when the sun comes out, it is day time
                }
                gc.setFill(Color.YELLOW); //the sun will only show up when it is above the horizon
                gc.fillOval(x.doubleValue(),y.doubleValue(),D,D);
                gc.setFill(Color.GREEN); //the ground/horizon
                gc.fillRect(0, Z, W, H);
                gc.setFill(Color.RED); //color of snowman
                double feetX = W*.53;
                double bodyX = W*.545;
                double headX = W*.555;
                double feetY = Z*1.2;
                double bodyY = Z*1.03;
                double headY = Z*.9;
                gc.fillOval(feetX, feetY, S, S);  //snowman's "feet"
                gc.fillOval(bodyX, bodyY, S*.75, S*.75); //body
                gc.fillOval(headX, headY, S*.6, S*.6); //head
                gc.setFill(Color.BLACK); //color of eyes/nose/mouth/hands
                gc.fillOval(headX*1.04, headY*1.05, S*.07, S*.07); //left eye
                gc.fillOval(headX*1.09, headY*1.05, S*.07, S*.07); //right eye
                gc.fillOval(headX*1.07, headY*1.1, S*.04, S*.04); //nose
                gc.strokeArc(headX*1.045, headY*1.11, S*.25, S*.11, -200, 210, ArcType.OPEN); //mouth
                
                
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
