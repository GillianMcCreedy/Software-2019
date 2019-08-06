/*  This class pops up an alert box that asks if you really want to delete a photo

    Author: Gillian McCreedy <gillianmccreedy@gmail.com>

*/

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DelPicAlertBox {

    public static void display(Album currentAlbum, Photo currentPhoto) {
        Stage alertWindow = new Stage();

        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle("You really want to delete a picture?");
        alertWindow.setMinWidth(250);

        Label alertLabel = new Label();
        alertLabel.setText("Are you sure about deleting " + currentPhoto.getName() + "?");
        Button yeahButton = new Button("Hell yeah");
        yeahButton.setOnAction(e -> {
            //delete the object
            currentAlbum.delPhotoFromAlbum(currentPhoto);
            alertWindow.close();
        });

        Button nahButton = new Button("Nah man");
        nahButton.setOnAction(e -> alertWindow.close());

        VBox alertLayout = new VBox(10);
        alertLayout.getChildren().addAll(alertLabel,yeahButton, nahButton);
        alertLayout.setAlignment(Pos.CENTER);

        Scene alertScene = new Scene(alertLayout);
        alertWindow.setScene(alertScene);
        alertWindow.showAndWait();
    }
}
