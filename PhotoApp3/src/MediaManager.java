/*  MediaManager Handles the creation of new albums and updating the window with new media additions

    Author: Gillian McCreedy <gillianmccreedy@gmail.com>

    Details:
    - creates new albums
    - handles displaying albums on click?
 */

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class MediaManager {
    //list of all albums (should always have MainLibrary)
    private Album mainAlbum;
    private ArrayList<Album> albums = new ArrayList<>();
    private Album currentAlbum;
    private Photo currentPhoto;
    private Main mainWindow;

    public MediaManager(Main window) {
        this.mainWindow = window;
        this.mainAlbum = new Album();
        //attaches observer to main album
        this.mainAlbum.Attach(mainWindow);
        this.mainAlbum.setAlbumName("Main");
    }


    public ArrayList<Album> getAlbumsList() {
        return this.albums;
    }


    public Album getMain() { return mainAlbum; }

    public void setMain(Album newMain) {
        mainAlbum = newMain;
        mainAlbum.Attach(mainWindow);

    }

    public Main getMainWindow() {
        return mainWindow;
    }

    public void addNewAlbum(Album newAlbum) {
        this.albums.add(newAlbum);
//        int index = this.masterListOfAlbums.indexOf(new);
        //new.setIndex(this.masterListOfAlbums.indexOf(new));
    }

    public Album getAlbumFromList(Album getThisAlbum) {
        return this.albums.get(this.albums.indexOf(getThisAlbum));
    }

    public Album delAlbum(Album deleteThis) {
        mainAlbum.removeLeaf(deleteThis);
//        int index = this.mainAlbum.getLeaves().indexOf(deleteThis);
//        this.mainAlbum.getLeaves().remove(index);
        return deleteThis;
    }


    public Album getCurrentAlbum() {
        return this.currentAlbum;
    }

    public void setCurrentAlbum(Album setThis) {
        this.currentAlbum = setThis;
    }

    public void setCurrentPhoto(Photo photo) {
        this.currentPhoto = photo;
    }

    public Photo getCurrentPhoto() {
        return this.currentPhoto;
    }


    public VBox generateAlbumPane() {
        //reset buttons
        ArrayList<Button> albumButtons = new ArrayList<Button>();

        //generate buttons and add to array albumButtons
        //this will only access the albums that belong to our master album, Main
        ArrayList<Album> leaves = mainAlbum.getLeaves();

        for (int i=0; i< leaves.size(); i++) {
            Album selectedAlbum = leaves.get(i);
            Button newAlbumButton = new Button(leaves.get(i).getAlbumName());
            newAlbumButton.setOnAction(e -> {
                //sets current album to itself
                currentAlbum = selectedAlbum;
                //button action tells karen to create a new album vbox for it and set it to the mainScrollPane
                mainWindow.setMainSP(currentAlbum.createAVB(MediaManager.this));
                //need to set the above albumVBox to the mainScrollPane in Main class
            });
            albumButtons.add(newAlbumButton);
            System.out.println("Created new button for " + selectedAlbum.getAlbumName());
        }
        //create the new VBox
        VBox albumBox = new VBox(5);
        Label albumLabel = new Label("Albums");
        albumBox.getChildren().add(albumLabel);

        //add buttons to VBox
        System.out.println("Number of buttons before: " + albumButtons.size());
//        for (int i = 0; i < albumButtons.size(); i++) {
//
//            albumBox.getChildren().addAll(albumButtons.get(i));
//            System.out.println("Added a button for " + albumButtons.get(i).getText());
//        }
        albumBox.getChildren().addAll(albumButtons);
        System.out.println("Number of buttons after: " + albumButtons.size());

        return albumBox;
    }
}
