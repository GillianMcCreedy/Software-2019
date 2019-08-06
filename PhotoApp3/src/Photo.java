/*  This Photo class contains details for a photo object

    Author: Gillian McCreedy <gillianmccreedy@gmail.com>

    Details:
    -
    -
 */

import java.net.URL;

public class Photo {
    String filePath, name, caption;
    URL fileURL;
//    ArrayList photos;
//    int h,w; //height and width of the photo

    public Photo() {
         this.filePath = "";
         this.name = "";
         this.caption = "";
         this.fileURL=null;
    }


    //onestop shop for setting photo metadata
//    @XmlElement
    public void setMeta(String picName, String picFilePath, String picCaption, URL picURL) {
        this.name = picName;
        this.filePath = picFilePath;
        this.caption = picCaption;
        this.fileURL = picURL;
    }


    //URL methods
    public void setPhotoURL(URL picURL) {
        this.fileURL=picURL;
    }

    public URL getPhotoURL() {
        return this.fileURL;
    }



    //caption methods
//    @XmlElement
    public void setCaption(String text) {
        //take user input from somewhere
        this.caption = text;
    }

    public String getCaption() {
        return this.caption;
    }



    //filePath methods
//    @XmlElement
    public void setFilePath(String path) {
        this.filePath = path;
    }

    public String getFilePath() {
        return this.filePath;
    }


    //name methods
//    @XmlElement
    public void setName(String fileName) {
        this.name = fileName;
    }

    public String getName() {
        return this.name;
    }

}
