/*  This AppRestore class should *hopefully* restore my application's changes

    Author: Gillian McCreedy <gillianmccreedy@gmail.com>

    Details:
    -
    -
 */

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class AppRestore {
    public static void main(String[] args) { File file = new File("monkey.xml");
    try {
        JAXBContext jaxbContext = JAXBContext.newInstance(Photo.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Photo peepers = (Photo)jaxbUnmarshaller.unmarshal(file);
    }
    catch (JAXBException e) {
        e.printStackTrace();
    }
    }
}
