/*  This class pops up an alert box that helps you add a new photo and its caption

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
import java.net.URL;

public class AddPicAlertBox {
    private static String filePath, name, caption;
    private static URL link;

    public static void display(Album currentAlbum) {


        Photo addedPhoto = new Photo();

        Stage alertWindow = new Stage();

        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle("So you want a new picture huh?");
        alertWindow.setMinWidth(300);

        Label addCapLabel = new Label();
        addCapLabel.setText("Add caption below!");

        //File Chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files","*.jpg","*.png","*.jpeg");
        fileChooser.getExtensionFilters().add(filter);


        //Button
        Button addButton = new Button("Pick a Pic");
        addButton.setOnAction(e -> {

            File file = fileChooser.showOpenDialog(null);
            addCapLabel.setText(file.getName());
            filePath = file.toURI().toString();
            name = file.getName();

            System.out.println(file.toURI());

        });


        //Caption Text field
        TextField captionArea = new TextField();
        captionArea.setPromptText("Write a caption");


//        //set fields of the photo
//        addedPhoto.setCaption(captionArea.getText());
//        addedPhoto.setFilePath(filePath);
//        addedPhoto.setName(name);
//
//        //add to album
//        currentAlbum.addPhotoToAlbum(addedPhoto);
//        System.out.println("Added new photo to: " + currentAlbum.getAlbumName());


        Button doneButton = new Button("Done!");
        doneButton.setOnAction(e -> {
            //set fields of the photo
            addedPhoto.setCaption(captionArea.getText());
            addedPhoto.setFilePath(filePath);
            addedPhoto.setName(name);

            //add to album
            currentAlbum.addPhotoToAlbum(addedPhoto);
            System.out.println("Added new photo to: " + currentAlbum.getAlbumName());

            currentAlbum.Notify();
            alertWindow.close();
        });

        VBox alertLayout = new VBox(10);
        alertLayout.getChildren().addAll(addButton,addCapLabel,captionArea,doneButton);
        alertLayout.setAlignment(Pos.CENTER);

        Scene alertScene = new Scene(alertLayout);
        alertWindow.setScene(alertScene);
        alertWindow.showAndWait();
    }
}
