/*  This class pops up an alert box that asks if you really want to delete an album

    Author: Gillian McCreedy <gillianmccreedy@gmail.com>

*/

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DelAlbumAlertBox {

    public static void display(Album currentAlbum, MediaManager person) {
        Stage alertWindow = new Stage();

        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle("Delete this album? Really?");
        alertWindow.setMinWidth(250);

        Label alertLabel = new Label();
        String message = "Are you sure about deleting " + currentAlbum.getAlbumName() + " album?";
        alertLabel.setText(message);
        Button yeahButton = new Button("Hell yeah");
        yeahButton.setOnAction(e -> {
            //delete the object
            person.delAlbum(currentAlbum);
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
