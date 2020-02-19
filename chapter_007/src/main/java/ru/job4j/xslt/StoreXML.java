package ru.job4j.xslt;

import ru.job4j.xslt.entries.Entries;
import ru.job4j.xslt.entries.Entry;
import ru.job4j.xslt.sax.OperationXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *Saving database contents in xml format
 */
public class StoreXML {
    private String target;

    public StoreXML(String target) {
        this.target = target;
    }
    public void save(List<Entry> list) {
        Entries entryes = new Entries();
        entryes.setEntries(list);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(
                entryes,
                new FileOutputStream(target)
        );
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
