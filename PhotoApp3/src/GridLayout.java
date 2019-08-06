/*

 */

import javafx.scene.*;

public class GridLayout implements LayoutState {
//    private MediaManager person;

    public GridLayout(){

    }

    public Node draw(MediaManager person) {
        //draw a gridpane
        Node layout = person.getCurrentAlbum().createGridPane(person);
//        Node layout = person.getCurrentAlbum().createAVB(person);
        return layout;
    }
}
