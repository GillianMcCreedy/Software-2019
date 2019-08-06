/*  This AppSaver class should *hopefully* save my application's changes

    Author: Gillian McCreedy <gillianmccreedy@gmail.com>

    Details:
    -
    -
 */

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import com.google.gson.Gson;

public class AppSaver {

    public static void main(String[] args) {
        Photo peepers = new Photo();
        File file = new File("photoapp.xml");



//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(Photo.class);
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//            jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true); //nice printing
//            jaxbMarshaller.marshal(peepers, file);
//        }
//        catch (JAXBException e) {
//            e.printStackTrace();
//        }
    }

}
