/*  This class pops up an alert box that lets you create a new album

    Author: Gillian McCreedy <gillianmccreedy@gmail.com>

*/

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;


public class DisplaySubAlbum {

    public static void display(Album subAlbum, MediaManager person) {

        Stage alertWindow = new Stage();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(subAlbum.getAlbumName());
//        alertWindow.setFullScreen(true);
        alertWindow.setMinWidth(700);

        Label caption = new Label();
        caption.setText(subAlbum.getAlbumName() + " Contents");

    //create our contents
        VBox subAlbumVBox = subAlbum.createAVB(person);


        Button doneButton = new Button("Done!");
        doneButton.setOnAction(e -> {
            alertWindow.close();
            person.setCurrentAlbum(subAlbum.getNode());
            System.out.println("Current album: " + subAlbum.getAlbumName());
        });

        VBox alertLayout = new VBox(10);
        alertLayout.getChildren().addAll(subAlbumVBox,doneButton);
        alertLayout.setAlignment(Pos.CENTER);

        Scene alertScene = new Scene(alertLayout);
        alertWindow.setScene(alertScene);
        alertWindow.showAndWait();
    }

}
