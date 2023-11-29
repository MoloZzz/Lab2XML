package org.example;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class XMLValidator {

    public static boolean validateXMLAgainstXSD(String xmlFilePath, String xsdFilePath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Schema schema = factory.newSchema(new StreamSource(new File(xsdFilePath)));

            Validator validator = schema.newValidator();

            validator.validate(new StreamSource(new File(xmlFilePath)));

            return true;

        } catch (SAXException e) {
            System.out.println("XML document is not valid. Reason: " + e.getMessage());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}