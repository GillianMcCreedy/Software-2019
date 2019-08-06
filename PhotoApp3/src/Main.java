/* This main class should run my entire Photo Album Application

    Author: Gillian McCreedy <gillianmccreedy@gmail.com>

    Details:
    - has a closeApp() method that should update persistent items when user closes the app?
 */

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;


public class Main extends Application implements Observer {

    private Stage window;
    private Scene scene;
    private BorderPane contentPane,photoPane,albumPane;
    private ScrollPane albumScroll, mainSP;
    private VBox albumBox;
    private HBox topMenu;
    private Button picButton, albumButton;
    private Button delButton, editButton;
    private MediaManager karen;
    private LayoutState layoutState;
    private Boolean isGrid;



    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //our media manager, karen. she keeps record of all albums, currentAlbum and currentPhoto
        karen = new MediaManager(this);
        deserialize();

        primaryStage.setTitle("PhotoApp");

        //initialize our layout boolean to false for nice linear views
        isGrid= false;

        window = primaryStage;
        window.setTitle("PhotoApp");


        //Photos pane
        mainSP = new ScrollPane();

        karen.setCurrentAlbum(karen.getMain());



        //Create albumScroll for viewing album items
        albumScroll = new ScrollPane();
        VBox albumPane = karen.generateAlbumPane();
        System.out.println("Album pane has: " + albumPane.getChildren().size() + " things");
        albumScroll.setContent(albumPane);



        //rightBox - for edit buttons (these will later be connected to LV cells)
        VBox rightBox = new VBox();
        rightBox.setSpacing(5);
        Label picOptions = new Label("Picture Options");
        Button expandPic = new Button("Expand Photo");
        expandPic.setOnAction(e -> {
            ExpandPicAlertBox.display(karen.getCurrentPhoto());

        });

        Button changePic = new Button("Change Picture");
        changePic.setOnAction(e -> {
            ChangePicAlertBox.display(karen.getCurrentAlbum(), karen.getCurrentPhoto());
//            mainSP.setContent(karen.getCurrentAlbum().createAVB(karen));
        });

        Button changeCap = new Button("Change Caption");
        changeCap.setOnAction(e -> {
            ChangeCapAlertBox.display(karen.getCurrentAlbum(),karen.getCurrentPhoto());
//            mainSP.setContent(karen.getCurrentAlbum().createAVB(karen));
        });

        //Delete Button
        delButton = new Button("Delete Photo");
        delButton.setOnAction(e -> {
            DelPicAlertBox.display(karen.getCurrentAlbum(),karen.getCurrentPhoto());
            System.out.println(karen.getCurrentPhoto().toString());
            //redraw the album
//            mainSP.setContent(karen.getCurrentAlbum().createAVB(karen));

        });
        //if user clicks yes in alert box, remove stuff
        //if no, close alert box
        Label albumOptions = new Label("Album Options");
        Button delAlbum = new Button("Delete Album");
        delAlbum.setOnAction(e -> {
            DelAlbumAlertBox.display(karen.getCurrentAlbum(), karen);
            karen.generateAlbumPane();
        });
        Button renameAlbum = new Button("Rename Album");
        Button changeHeadlinePic = new Button("Change Headline Photo");
        Button addAlbumToAlbum = new Button("Add Album to Album");
        addAlbumToAlbum.setOnAction(e -> {
            //gotta get this working
            NewSubAlbumAlertBox.display(karen.getCurrentAlbum(), karen);
            karen.getCurrentAlbum().createAVB(karen);
        });
        //add elements to right pane
        rightBox.getChildren().addAll(picOptions,expandPic,changePic,changeCap,delButton, albumOptions,renameAlbum,changeHeadlinePic,addAlbumToAlbum,delAlbum);



        //TOPMENU - stays the same regardless of scene
        Button picButton = new Button("Add New Photo"); //top menu
        Button albumButton = new Button("Add New Album");
        Button layoutButton = new Button("Switch Layout");

        picButton.setOnAction(e -> {
            AddPicAlertBox.display(karen.getCurrentAlbum());
            System.out.println(karen.getCurrentAlbum());
            //draw the album contents
//            mainSP.setContent(karen.getCurrentAlbum().createAVB(karen));

        });
        albumButton.setOnAction(e -> {
            System.out.println("This adds a new album");
            NewAlbumAlertBox.display(karen);
            //draw album menu
//            albumScroll.setContent(karen.generateAlbumPane());

        });

        layoutButton.setOnAction(e -> {
            if (isGrid == false) {
                isGrid = true;
            }
            else {
                isGrid = false;
            }
            Layout layout = new Layout(isGrid);
            mainSP.setContent(layout.draw(karen));
            karen.getCurrentAlbum().Notify();

        });

        //add elements to menu
        topMenu = new HBox();
        topMenu.getChildren().addAll(picButton,albumButton, layoutButton);
        topMenu.setAlignment(Pos.CENTER);
        topMenu.setSpacing(5);



        //to initialize our scene
        contentPane = new BorderPane();
        contentPane.setTop(topMenu);
        contentPane.setCenter(mainSP);
        contentPane.setRight(rightBox);
        contentPane.setLeft(albumScroll);
        scene = new Scene(contentPane,900,700);


        //initialize the window
        window.setScene(scene);
        window.show();


        //takes care of closing the window
        window.setOnCloseRequest(e -> {
            //update json with any changes made
            closeApp();
        });

    } //end of start method


    //sets the main scrollpane of our window
    public void setMainSP(VBox content) {
        mainSP.setContent(content);
    }


    public void update() {
        System.out.println("Are we updating ANYTHING????");
        //create new album menu
        albumScroll.setContent(karen.generateAlbumPane());
        //set albumview to current album contents
        mainSP.setContent(karen.getCurrentAlbum().createAVB(karen));

    }

    private void closeApp() {
        //in here we will save our changes
        serialize();
        System.out.println("Are we here yet?");
        window.close();
    }

    public void serialize() {
        System.out.println("Save changes");
        String fileName = "stuff.json";
        Gson gson = new Gson();
        String json = gson.toJson(karen.getMain()); //give serializer our main album

        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(json);
//            writer.flush();
            writer.close();
            System.out.println("hi bananas");

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(json);

    } //end serialize()


    public void deserialize() {
        System.out.println("Restore media");
//        String fileName = "stuff.json";
        Gson gson = new Gson();
        File file = new File("stuff.json");
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("stuff.json"));
                Album newMain = gson.fromJson(reader, Album.class);
                //set this to be the main album of the MediaManager
                karen.setMain(newMain);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
