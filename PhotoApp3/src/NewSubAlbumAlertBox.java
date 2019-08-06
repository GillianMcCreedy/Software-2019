/*  This class pops up an alert box that lets you create a new album

    Author: Gillian McCreedy <gillianmccreedy@gmail.com>

*/

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class NewSubAlbumAlertBox {

    public static void display(Album parent, MediaManager person) {

        Album newAlbum = new Album();

        Stage alertWindow = new Stage();

        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle("We're going deeper...");
        alertWindow.setMinWidth(300);

        Label addAlbumName = new Label();
        addAlbumName.setText("Add an album name!");

        //Album name Text field
        TextField captionArea = new TextField();
        captionArea.setPromptText("Write a name");

        Button doneButton = new Button("Done!");
        doneButton.setOnAction(e -> {
            //set fields of the photo
            newAlbum.setAlbumName(captionArea.getText());

            newAlbum.setNode(parent);
            parent.addLeaf(newAlbum);

            alertWindow.close();
        });

        VBox alertLayout = new VBox(10);
        alertLayout.getChildren().addAll(addAlbumName,captionArea,doneButton);
        alertLayout.setAlignment(Pos.CENTER);

        Scene alertScene = new Scene(alertLayout);
        alertWindow.setScene(alertScene);
        alertWindow.showAndWait();
    }

}
