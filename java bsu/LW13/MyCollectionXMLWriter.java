package LW13;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MyCollectionXMLWriter {

    public static void saveToXML(MyCollection collection, String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            // Root element
            Element root = document.createElement("Students");
            document.appendChild(root);

            for (Student student : collection.getCollectionStudents()) { 
                Element studentElement = document.createElement("Student");

                Element surname = document.createElement("Surname");
                surname.appendChild(document.createTextNode(student.getSurname()));
                studentElement.appendChild(surname);

                Element groupNumber = document.createElement("GroupNumber");
                groupNumber.appendChild(document.createTextNode(String.valueOf(student.getGroupNumber())));
                studentElement.appendChild(groupNumber);

                Element courseNumber = document.createElement("CourceNumber");
                courseNumber.appendChild(document.createTextNode(String.valueOf(student.getCourseNumber())));
                studentElement.appendChild(courseNumber);

                root.appendChild(studentElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filePath));

            transformer.transform(source, result);
            System.out.println("File saved to " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}