package javaitzen.spring.oxm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

/**
 * The Class OXMarshall.
 */
public class OXMarshall {
    
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    /**
     * Sets the marshaller.
     * 
     * @param marshaller
     *            the new marshaller
     */
    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    /**
     * Sets the unmarshaller.
     * 
     * @param unmarshaller
     *            the new unmarshaller
     */
    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    /**
     * Save xml.
     * 
     * @param message
     *            the message
     * @param fileName
     *            the file name
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public void saveXML(ClassToXMLMap message, String fileName) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            this.marshaller.marshal(message, new StreamResult(fos));
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * Load xml.
     * 
     * @param fileName
     *            the file name
     * @return the class to xml map
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public ClassToXMLMap loadXML(String fileName) throws IOException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            return (ClassToXMLMap) this.unmarshaller.unmarshal(new StreamSource(fis));
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }


    
}

