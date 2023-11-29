package org.example;

import java.util.Collections;
import java.util.List;

public class XmlParserApp {

    public static void main(String[] args) {

        String path = "C:\\Users\\megao\\IdeaProjects\\Lab2XML\\src\\main\\resources\\computer_parts.xml";

        System.out.println("DOM parse: \n");
        XMLparserDom.parseXML(path);
        System.out.println("SAX parse: \n");
        List<Device> parsedDevices = XMLParserSAX.parseXML(path);
        System.out.println(parsedDevices);
        System.out.println("StAX parse: \n");
        XMLParserStAX.parseXML(path);

        Collections.sort(parsedDevices, new Device.DeviceComparator());
        for (Device device : parsedDevices) {
            System.out.println(device);
        }

        String pathXSD = "C:\\Users\\megao\\IdeaProjects\\Lab2XML\\src\\main\\resources\\computer_parts.xsd";
        boolean isValid = XMLValidator.validateXMLAgainstXSD(path, pathXSD);

        if (isValid) {
            System.out.println("XML document is valid according to the XSD schema.");
        } else {
            System.out.println("XML document is not valid according to the XSD schema.");
        }





    }


}
