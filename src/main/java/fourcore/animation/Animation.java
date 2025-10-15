package fourcore.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Animation extends Label{

	public Animation () {};
	public void scaleUp(Label lbl) {
    	Timeline animation = new Timeline(
                new KeyFrame(Duration.ZERO,
                    new KeyValue(lbl.translateYProperty(), 0),
                    new KeyValue(lbl.scaleXProperty(), 1.0),
                    new KeyValue(lbl.scaleYProperty(), 1.0)
                ),
                new KeyFrame(Duration.millis(200),
                    new KeyValue(lbl.translateYProperty(), -24),
                    new KeyValue(lbl.scaleXProperty(), 0.8),
                    new KeyValue(lbl.scaleYProperty(), 0.8)	
                )
            );
    	animation.play();
    }
	public void scaleDown(Label lbl) {
		Timeline animation = new Timeline(
                new KeyFrame(Duration.ZERO,
                    new KeyValue(lbl.translateYProperty(), -24),
                    new KeyValue(lbl.scaleXProperty(), 0.8),
                    new KeyValue(lbl.scaleYProperty(), 0.8)
                ),
                new KeyFrame(Duration.millis(200),
                    new KeyValue(lbl.translateYProperty(), 0),
                    new KeyValue(lbl.scaleXProperty(), 1.0),
                    new KeyValue(lbl.scaleYProperty(), 1.0)
                )
            );
            animation.play();
	}
	public ImageView taoImgGhe(String duongDan) {
		ImageView img = new ImageView(new Image(getClass().getResource(duongDan).toExternalForm()));
		img.setFitHeight(40);
		img.setFitWidth(40);
		return img;
	}
	
}
