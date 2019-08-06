/*  This class pops up an alert box that helps you add a new photo and caption

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


public class ChangeCapAlertBox {
    private static String caption;

    public static void display(Album currentAlbum, Photo currentPhoto) {

        Stage alertWindow = new Stage();

        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle("So you want a new caption huh?");
        alertWindow.setMinWidth(300);

        Label addCapLabel = new Label();
        addCapLabel.setText("Think up a new caption, quick!");


        //Caption Text field
        TextField captionArea = new TextField();
        captionArea.setPromptText("Write a caption");

        Button doneButton = new Button("Done-zo!");
        doneButton.setOnAction(e -> {
            currentPhoto.setCaption(captionArea.getText());
            currentAlbum.Notify();
            alertWindow.close();
        });

        VBox alertLayout = new VBox(10);
        alertLayout.getChildren().addAll(addCapLabel,captionArea,doneButton);
        alertLayout.setAlignment(Pos.CENTER);

        Scene alertScene = new Scene(alertLayout);
        alertWindow.setScene(alertScene);
        alertWindow.showAndWait();
    }
}
