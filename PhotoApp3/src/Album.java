/*  This Album class creates an album object

    Author: Gillian McCreedy <gillianmccreedy@gmail.com>

    Details:
    - has an arraylist of the photos it contains
    - will eventually have a sort of collection of sub-albums
    - has a field (string) with the Album Name
    - has a delete method?

 */

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.util.ArrayList;


public class Album implements Subject {
    private String albumName;
    private ArrayList<Photo> photos; //the photos contained in the album

    //make GSon ignore this?
    private transient ArrayList<Observer> observers;

    private ArrayList<Album> nodes;
    private ArrayList<Album> leaves;



//Constructor
    Album() {
        this.photos = new ArrayList<Photo>();
        this.leaves = new ArrayList<Album>();
        observers = new ArrayList<>();
        nodes = null;

    }


    //---------------------------------------------
    //Getters and setters
    //---------------------------------------------
    public void setNode(Album parent) {
      //  if (parent.getAlbumName() == "Main") {
            this.nodes.add(parent);
        //}
    }

    public Album getNode() {
        return nodes.get(0);
    }


    public String getAlbumName() {
        return this.albumName;
    }
    public void setAlbumName(String name) {
        this.albumName = name;
    }


    public ArrayList<Album> getLeaves() {
        return this.leaves;
    }
    public void setLeaves() {
        this.leaves = leaves;
    }


    public ArrayList<Photo> getPhotos() {
        ArrayList<Photo> photos = this.photos;
        return photos;
    }
    public void setPhotos() {
        this.photos = photos;
    }




    //---------------------------------------------
    //Observer/subject methods
    //---------------------------------------------

    public void Attach(Observer o) {
        observers.add(o);
        System.out.println("Attached an observer");

    }

    public void Detach(Observer o) {
        observers.remove(o);
    }

    public void Notify() {
        System.out.println("About to notify " + this.observers.size() + " observers.");
        for (int i=0; i < observers.size(); i++) {
            observers.get(i).update();
        }
        System.out.println("Hi are we actually listening?");
    }





    //---------------------------------------------
    //Add/remove/change methods
    //---------------------------------------------
    public void addLeaf(Album child) {
        this.leaves.add(child);
        Notify();
    }


    public Album removeLeaf(Album childToRemove) {
        this.leaves.remove(childToRemove);
        Notify();
        return childToRemove;
    }

    /*
        Adds new photo to the photos array
     */
    public void addPhotoToAlbum(Photo newPhoto) {
        //add photo to Album's array of photos
        System.out.println("About to add a photo...");
        this.photos.add(newPhoto);
        System.out.println("About to call notify");
        Notify();
        System.out.println("Should have just called notify");
    }

    public void changePhoto(Photo swapThis, Photo addThis) {
        int index = this.photos.indexOf(swapThis);
        this.photos.set(index, addThis);
        Notify();
    }
    /*
        Deletes photo from the Photos arraylist
     */
    public int delPhotoFromAlbum(Photo deleteThis) {
        int index = photos.indexOf(deleteThis);
        photos.remove(index);
        Notify();
        return index;
    }


    //create Album VBox that contains mini gridpanes that hold photos & their captions
    public VBox createAVB(MediaManager person) {
        //oh boy
        //oh fuck
        //only god or hell can help me now

        VBox albumVB = new VBox();

        //for album in leaves
        for (int i=0; i < leaves.size(); i++) {
            Album selectedAlbum = leaves.get(i);
            Button subAlbum = new Button(selectedAlbum.getAlbumName());
            subAlbum.setOnAction(e -> {
                person.setCurrentAlbum(selectedAlbum);
                System.out.println("Current album: " + selectedAlbum.getAlbumName());
                DisplaySubAlbum.display(selectedAlbum,person);
            });
        }

        System.out.println("Drawing an album with " + this.photos.size() + " pictures!");

        //for photo in album (arraylist of photos)
        for (int i=0; i < photos.size(); i++) {
            System.out.println(photos.size());
            System.out.println("AAAAAA");
            if (photos.size() > 0) {
                //create new imageview
                ImageView iView = new ImageView();
                System.out.println("BBBBBBB");
                //get filepath for current photo in photos
                String path = photos.get(i).getFilePath();
                //create the new image from path
                System.out.println("CCCCCCCC");
                System.out.println("\"" + path + "\"");
                if (path != null && path.length() > 0) {
                    Image image = new Image(path);
                    //set the imageview to the new image
                    iView.setImage(image);
                    iView.setPreserveRatio(true);
                    iView.setFitWidth(400);
                    System.out.println("PicPicPic");

                    Photo pic = photos.get(i);
                    //here I set the action to select the correct photo?
                    iView.setOnMouseClicked((actionEvent) -> {
                        person.setCurrentPhoto(pic);
                    });


                    //create new gridpane
                    GridPane newPicGrid = new GridPane();
                    //make text from pic caption
                    Text caption = new Text(photos.get(i).getCaption());
                    //add both to mini gridpane
                    newPicGrid.add(iView, 1, 0);
                    newPicGrid.add(caption, 2, 0);

                    albumVB.getChildren().add(newPicGrid);
                    System.out.println("Added a pic to the grid!");
                }
            }
            else {
                System.out.println("No photos yet");
            }
        }

        return albumVB;
    }




    //create Album VBox that contains mini gridpanes that hold photos & their captions
    public GridPane createGridPane(MediaManager person) {
        //oh boiiiiiiiiiii

        GridPane albumGP = new GridPane();
        albumGP.setPadding(new Insets(10,10,10,10));
        albumGP.setVgap(10);
        albumGP.setHgap(10);


//        Label gridLabel = new Label("Grid Layout Works!");
//        albumGP.getChildren().add(gridLabel);

        int col = 0;
        int row = 0;

        //for album in leaves
//        for (int i=0; i < leaves.size(); i++) {
//            if (col < 4) {
//                Album selectedAlbum = leaves.get(i);
//                Button subAlbum = new Button(selectedAlbum.getAlbumName());
//                subAlbum.setOnAction(e -> {
//                    person.setCurrentAlbum(selectedAlbum);
//                    System.out.println("Current album: " + selectedAlbum.getAlbumName());
//                    DisplaySubAlbum.display(selectedAlbum,person);
//                });
//                albumGP.setConstraints(subAlbum,col,row);
//                col++;
//            }
//            else {
//                col = 0;
//                row++;
//                Album selectedAlbum = leaves.get(i);
//                Button subAlbum = new Button(selectedAlbum.getAlbumName());
//                subAlbum.setOnAction(e -> {
//                    person.setCurrentAlbum(selectedAlbum);
//                    System.out.println("Current album: " + selectedAlbum.getAlbumName());
//                    DisplaySubAlbum.display(selectedAlbum,person);
//                });
//                albumGP.setConstraints(subAlbum,col,row);
//            }
//
//        }

//        System.out.println("Drawing an album with " + this.photos.size() + " pictures!");

        //for photo in album (arraylist of photos)
        for (int i=0; i < photos.size(); i++) {
             if (col < 4) {
                 System.out.println("Photos array size: " + photos.size());
                 if (photos.size() > 0) {
                     //create new imageview
                     ImageView iView = new ImageView();
                     System.out.println("BBBBBBB");
                     //get filepath for current photo in photos
                     String path = photos.get(i).getFilePath();
                     //create the new image from path
                     System.out.println("CCCCCCCC");
                     System.out.println("\"" + path + "\"");
                     if (path != null && path.length() > 0) {
                         Image image = new Image(path);
                         //set the imageview to the new image
                         iView.setImage(image);
                         iView.setPreserveRatio(true);
//                         iView.setFitWidth(100);
                         iView.setFitHeight(100);
                         System.out.println("PicPicPic");

                         Photo pic = photos.get(i);
                         //here I set the action to select the correct photo?
                         iView.setOnMouseClicked((actionEvent) -> {
                             person.setCurrentPhoto(pic);
                         });


                         //create new gridpane
                         GridPane newPicGrid = new GridPane();
                         //make text from pic caption
                         Text caption = new Text(photos.get(i).getCaption());
                         //add both to mini gridpane
                         newPicGrid.add(iView, 1, 0);
                         newPicGrid.add(caption, 1, 1);

//                         albumGP.getChildren().add(newPicGrid);
//                         albumGP.setConstraints(newPicGrid,col,row);
                         albumGP.add(newPicGrid,col,row);
                         col++;

                         System.out.println("Added a pic to the grid!");
                     }
                 } else {
                     System.out.println("No photos yet");
                 }
             } else {
                 col = 0;
                 row++;
                 System.out.println("Photos array size: " + photos.size());
                 if (photos.size() > 0) {
                     //create new imageview
                     ImageView iView = new ImageView();
                     System.out.println("BBBBBBB");
                     //get filepath for current photo in photos
                     String path = photos.get(i).getFilePath();
                     //create the new image from path
                     System.out.println("CCCCCCCC");
                     System.out.println("\"" + path + "\"");
                     if (path != null && path.length() > 0) {
                         Image image = new Image(path);
                         //set the imageview to the new image
                         iView.setImage(image);
                         iView.setPreserveRatio(true);
//                         iView.setFitWidth(100);
                         iView.setFitHeight(100);
                         System.out.println("PicPicPic");

                         Photo pic = photos.get(i);
                         //here I set the action to select the correct photo?
                         iView.setOnMouseClicked((actionEvent) -> {
                             person.setCurrentPhoto(pic);
                         });


                         //create new gridpane
                         GridPane newPicGrid = new GridPane();
                         //make text from pic caption
                         Text caption = new Text(photos.get(i).getCaption());
                         //add both to mini gridpane
                         newPicGrid.add(iView, 1, 0);
                         newPicGrid.add(caption, 1, 1);

//                         albumGP.getChildren().add(newPicGrid);
//                         albumGP.setConstraints(newPicGrid,col,row);
                         albumGP.add(newPicGrid,col,row);
                         col++;
                         System.out.println("Added a pic to the grid!");
                     }
                 } else {
                     System.out.println("No photos yet");
                 }
             }
        }

        return albumGP;
    }
}
