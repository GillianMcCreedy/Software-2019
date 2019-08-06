import javax.print.attribute.standard.Media;
import javafx.scene.*;

public class Layout {

    private LayoutState layoutState;
    private Boolean isGrid;

    public Layout(Boolean boo) {
        this.isGrid = boo;
        this.setState(isGrid);
    }

    public void setState(Boolean isGrid) {
        if (isGrid == false) {
            //new AVB
            layoutState = new LinearLayout();
        }
        else {
            //new grid
            layoutState = new GridLayout();
        }
    }


    public Node draw(MediaManager person){
        return layoutState.draw(person);
    }
}
