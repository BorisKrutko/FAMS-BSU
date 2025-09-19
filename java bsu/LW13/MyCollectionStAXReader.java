package LW13;

import java.io.File;
import java.io.FileInputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class MyCollectionStAXReader {

    public static MyCollection readFromXMLUsingStAX(File file) {
        MyCollection collection = new MyCollection();

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(file));

            Student currentStudent = null;

            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        if ("Student".equals(reader.getLocalName())) {
                            currentStudent = new Student();
                        } else if (currentStudent != null) {
                            switch (reader.getLocalName()) {
                                case "Surname":
                                    currentStudent.setSurname(reader.getElementText());
                                    break;
                                case "GroupNumber":
                                    currentStudent.setGroupNumber(Integer.parseInt(reader.getElementText()));
                                    break;
                                case "CourceNumber":
                                    currentStudent.setCourseNumber(Integer.parseInt(reader.getElementText()));
                                    break;
                            }
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        if ("Student".equals(reader.getLocalName()) && currentStudent != null) {
                            collection.add(currentStudent);
                            currentStudent = null;
                        }
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return collection;
    }
}