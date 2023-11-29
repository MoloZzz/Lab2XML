package org.example;

public class XmlParserApp {

    public static void main(String[] args) {

        String path = "C:\\Users\\megao\\IdeaProjects\\Lab2XML\\src\\main\\resources\\computer_parts.xml";

        System.out.println("DOM parse: \n");
        XMLparserDom.parseXML(path);
        System.out.println("SAX parse: \n");
        XMLParserSAX.parseXML(path);
        System.out.println("StAX parse: \n");
        XMLParserStAX.parseXML(path);
    }


}
