import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * Main
 */
public class Main extends Application {

    private Point2D point;
    private List<Point2D> pointers = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 640, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                System.out.println("Mouse clicked X: "+click.getX()+" Y: "+click.getY());
                point = new Point2D(click.getX(), click.getY());
                pointers.add(point);
                for (int i = 0; i < pointers.size()-1; i++) {
                    Line line = new Line(pointers.get(i).getX(), pointers.get(i).getY(), pointers.get(i+1).getX(), pointers.get(i+1).getY());
                    line.setStroke(Color.BLUE);
                    root.getChildren().add(line);
                    if (pointers.get(0).equals(pointers.get(pointers.size()-1))){
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setContentText("Cerrado el punto");
                        alert.showAndWait();
                        System.exit(1);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}