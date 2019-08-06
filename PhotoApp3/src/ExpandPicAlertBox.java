/*  This class pops up an alert box that lets you create a new album

    Author: Gillian McCreedy <gillianmccreedy@gmail.com>

*/

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;


public class ExpandPicAlertBox {

    public static void display(Photo selectedPhoto) {

        Stage alertWindow = new Stage();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(selectedPhoto.getCaption());
        alertWindow.setFullScreen(true);

        Label caption = new Label();
        caption.setText(selectedPhoto.getCaption());


        //recreate our image
        ImageView iView = new ImageView();
        Image biggerPic = new Image(selectedPhoto.getFilePath());
        iView.setImage(biggerPic);

        iView.setPreserveRatio(true);
        if (iView.getX() > iView.getY()) {
            iView.setFitWidth(screenWidth);
        }
        else if (iView.getY() > iView.getX()) {
            iView.setFitHeight(screenHeight);
        }
        else {
            iView.setFitWidth(screenHeight-40);
        }



        Button doneButton = new Button("Done!");
        doneButton.setOnAction(e -> {
            alertWindow.close();
        });

        VBox alertLayout = new VBox(10);
        alertLayout.getChildren().addAll(iView);
        alertLayout.setAlignment(Pos.CENTER);

        Scene alertScene = new Scene(alertLayout);
        alertWindow.setScene(alertScene);
        alertWindow.showAndWait();
    }

}
