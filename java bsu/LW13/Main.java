package LW13;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        MyCollection collection = new MyCollection();
        collection.add(new Student(1344, "Boris",  2, 2));
        collection.add(new Student(1345, "Vlad",  2, 2));
        collection.add(new Student(1346, "DanTon",  1, 2));
        collection.add(new Student(1347, "Alex",  3, 2));

        MyCollectionXMLWriter xmlWriter = new MyCollectionXMLWriter();
        xmlWriter.saveToXML(collection, "C:/BORIS/unser.xml");

        MyCollectionSAXReader readerOne = new MyCollectionSAXReader();
        MyCollectionStAXReader readerTwo = new MyCollectionStAXReader();
        MyCollection students = readerOne.readFromXMLUsingSAX(new File("C:/BORIS/unser.xml"));
        System.out.println(students.toString());
        students = readerTwo.readFromXMLUsingStAX(new File("C:/BORIS/unser.xml"));
        System.out.println(students.toString());
    }
}
