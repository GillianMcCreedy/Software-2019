/*

 */

import javafx.scene.*;



public class LinearLayout implements LayoutState {
//    private MediaManager person;

    public LinearLayout(){

    }

    public Node draw(MediaManager person) {
        //draw a regular AVB
        Node layout = person.getCurrentAlbum().createAVB(person);
        return layout;
    }
}
