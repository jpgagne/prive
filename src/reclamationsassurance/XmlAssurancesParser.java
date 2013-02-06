
package reclamationsassurance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XmlAssurancesParser {

//<editor-fold defaultstate="collapsed" desc="Objets accès XML">
    File fichierImput;
    Document docImput;
    DocumentBuilderFactory docBuilderFactoryImput;
    DocumentBuilder docBuilderImput;
    File fichierOutput;
    DocumentBuilderFactory docFactoryOutput;
    DocumentBuilder docBuilderOutput;
    Document docOutput;
//</editor-fold>

/*
* Constructeur
*/
public XmlAssurancesParser(String nomFichierImput, String nomFichierOutput)
    {
    System.out.println("Objet parseur créé");
    ouvrirFichierEntree(nomFichierImput);
    ouvrirFichierSortie(nomFichierOutput);
    execution();
    }

//<editor-fold defaultstate="collapsed" desc="Ouvrir Fichiers">
    private void ouvrirFichierEntree(String nomFichierImput) {

        fichierImput = new File(nomFichierImput);
        System.out.println("Ouverture du fichier entrée " + fichierImput.getName());

        docBuilderFactoryImput = DocumentBuilderFactory.newInstance();

        boolean succes = false;
        try {
            docBuilderImput = docBuilderFactoryImput.newDocumentBuilder();
            docImput = docBuilderImput.parse(fichierImput);
            succes = true;
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(XmlAssurancesParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Document " + fichierImput.getName() + " : " + Boolean.toString(succes));
        }
    }

    private void ouvrirFichierSortie(String nomFichierOutput) {
        fichierOutput = new File(nomFichierOutput);
        System.out.println("Ouverture du fichier sortie " + fichierOutput.getName());

        boolean succes = false;
        try {
            docFactoryOutput = DocumentBuilderFactory.newInstance();
            docBuilderOutput = docFactoryOutput.newDocumentBuilder();
            docOutput = docBuilderOutput.newDocument();
            succes = true;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlAssurancesParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Document " + fichierOutput.getName() + " : " + Boolean.toString(succes));
        }
    }
//</editor-fold>

private void execution()
{
List<Item> listeReclamations = lireReclamations();
for (Item item : listeReclamations) 
    {
    System.out.println(item);
    }
}

    
public List<Item> lireReclamations() 
        
{
List<Item> items = new ArrayList<>();
try 
{
    
    
    
}
catch (Exception exc)
        {
            System.out.println("Erreur: "+exc.getMessage());
        }
finally
{
    return items;
}
    
}
    
    private void ecrireRemboursement(Element elementRemboursement) {
        
        
        
        
        
    }
}
//        try {
//
//
//
//// root elements
//            Document doc = docBuilder.newDocument();
//            Element rootElement = doc.createElement("company");
//            doc.appendChild(rootElement);
//
//// staff elements
//            Element staff = doc.createElement("Staff");
//            rootElement.appendChild(staff);
//
//// set attribute to staff element
//            Attr attr = doc.createAttribute("id");
//            attr.setValue("1");
//            staff.setAttributeNode(attr);
//
//// shorten way
//// staff.setAttribute("id", "1");
//
//// firstname elements
//            Element firstname = doc.createElement("firstname");
//            firstname.appendChild(doc.createTextNode("yong"));
//            staff.appendChild(firstname);
//
//// lastname elements
//            Element lastname = doc.createElement("lastname");
//            lastname.appendChild(doc.createTextNode("mook kim"));
//            staff.appendChild(lastname);
//
//// nickname elements
//            Element nickname = doc.createElement("nickname");
//            nickname.appendChild(doc.createTextNode("mkyong"));
//            staff.appendChild(nickname);
//
//// salary elements
//            Element salary = doc.createElement("salary");
//            salary.appendChild(doc.createTextNode("100000"));
//            staff.appendChild(salary);
//
//// write the content into xml file
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource source = new DOMSource(doc);
//            StreamResult result = new StreamResult(new File("C:\\file.xml"));
//
//// Output to console for testing
//// StreamResult result = new StreamResult(System.out);
//
//            transformer.transform(source, result);
//
//            System.out.println("File saved!");
//
//        } catch (ParserConfigurationException pce) {
//            pce.printStackTrace();
//        } catch (TransformerException tfe) {
//            tfe.printStackTrace();
//        }
//    }
//}
//public List<Item> readConfig(String configFile) {
//List<Item> items = new ArrayList<Item>();
//try {
//// First create a new XMLInputFactory
//XMLInputFactory inputFactory = XMLInputFactory.newInstance();
//// Setup a new eventReader
//InputStream in = new FileInputStream(configFile);
//XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
//// Read the XML document
//Item item = null;
//
//while (eventReader.hasNext()) {
//XMLEvent event = eventReader.nextEvent();
//
//if (event.isStartElement()) {
//StartElement startElement = event.asStartElement();
//// If we have a item element we create a new item
//if (startElement.getName().getLocalPart() == (ITEM)) {
//item = new Item();
//// We read the attributes from this tag and add the date
//// attribute to our object
//Iterator<Attribute> attributes = startElement.getAttributes();
//while (attributes.hasNext()) {
//Attribute attribute = attributes.next();
//if (attribute.getName().toString().equals(DATE)) {
//item.setDate(attribute.getValue());
//}
//
//}
//}
//
//if (event.isStartElement()) {
//if (event.asStartElement().getName().getLocalPart().equals(MODE)) {
//event = eventReader.nextEvent();
//item.setMode(event.asCharacters().getData());
//continue;
//}
//}
//if (event.asStartElement().getName().getLocalPart().equals(UNIT)) {
//event = eventReader.nextEvent();
//item.setUnit(event.asCharacters().getData());
//continue;
//}
//
//if (event.asStartElement().getName().getLocalPart().equals(CURRENT)) {
//event = eventReader.nextEvent();
//item.setCurrent(event.asCharacters().getData());
//continue;
//}
//
//if (event.asStartElement().getName().getLocalPart().equals(INTERACTIVE)) {
//event = eventReader.nextEvent();
//item.setInteractive(event.asCharacters().getData());
//continue;
//}
//}
//// If we reach the end of an item element we add it to the list
//if (event.isEndElement()) {
//EndElement endElement = event.asEndElement();
//if (endElement.getName().getLocalPart() == (ITEM)) {
//items.add(item);
//}
//}
//
//}
//} catch (FileNotFoundException e) {
//e.printStackTrace();
//} catch (XMLStreamException e) {
//e.printStackTrace();
//}
//return items;
//}
//}
