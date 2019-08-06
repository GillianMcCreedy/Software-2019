/*  This class pops up an alert box that allows you to change the current selected photo and its caption

    Author: Gillian McCreedy <gillianmccreedy@gmail.com>

*/


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class ChangePicAlertBox {
    private static String newName, newCap, newPath;

    public static void display(Album currentAlbum, Photo currentPhoto) {


        Photo changedPhoto = new Photo();

        Stage alertWindow = new Stage();

        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle("So you want to change a picture huh?");
        alertWindow.setMinWidth(350);

        Label changePicLabel = new Label();
        changePicLabel.setText("Pick a different photo!");

        //File Chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files","*.jpg","*.png","*.jpeg");
        fileChooser.getExtensionFilters().add(filter);


        //Button
        Button addButton = new Button("Pick a new Pic");
        addButton.setOnAction(e -> {

            File file = fileChooser.showOpenDialog(null);
            changePicLabel.setText(file.getName());
            newPath = file.toURI().toString();
            newName = file.getName();

            System.out.println(file.toURI());

        });

        //Caption Text field
        TextField captionArea = new TextField();
        captionArea.setPromptText("Write a new caption");

        Button doneButton = new Button("Done!");
        doneButton.setOnAction(e -> {
            //set fields of the photo
            changedPhoto.setCaption(captionArea.getText());
            changedPhoto.setFilePath(newPath);
            changedPhoto.setName(newName);

            //add to album
            currentAlbum.changePhoto(currentPhoto,changedPhoto);

            //refresh
            alertWindow.close();
        });

        VBox alertLayout = new VBox(10);
        alertLayout.getChildren().addAll(addButton,captionArea,changePicLabel,doneButton);
        alertLayout.setAlignment(Pos.CENTER);

        Scene alertScene = new Scene(alertLayout);
        alertWindow.setScene(alertScene);
        alertWindow.showAndWait();
    }
}
