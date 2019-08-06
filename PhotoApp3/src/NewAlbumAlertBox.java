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


public class NewAlbumAlertBox {

    public static void display(MediaManager person) {

        Album newAlbum = new Album();

        Stage alertWindow = new Stage();

        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle("Let's make a new album!");
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
            System.out.println("Just set the album name");
            //Attach observer to new album
            newAlbum.Attach(person.getMainWindow());
            System.out.println("Just attached main as the observer");
//            newAlbum.setNode(person.getMain());
//            System.out.println("Just set parent to: " + person.getMain().getAlbumName());
            person.getMain().addLeaf(newAlbum);
            System.out.println("Just created " + newAlbum.getAlbumName() + " and added it as a leaf to main");

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
