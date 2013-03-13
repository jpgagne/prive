
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 

public class XML_Output
{
private String filePath;
private File fichierSortie;

private Element elementRacine;
private Document documentSortie;
private DocumentBuilderFactory docFactory;
private DocumentBuilder docBuilder;

XML_Output(String filePath) throws ExceptionIO
    {
    this.filePath = filePath;

    docFactory = DocumentBuilderFactory.newInstance();
    try {
        docBuilder = docFactory.newDocumentBuilder();
        }
    catch (ParserConfigurationException excPC)
        {
        throw new ExceptionIO("ParserConfigurationException sur "+fichierSortie.getName()+ " Message: "+excPC.getMessage());
        }
    this.documentSortie = docBuilder.newDocument();
    }


protected void redigerDocumentSortie(ArrayList<Remboursement> remboursements)
    {
    docFactory = DocumentBuilderFactory.newInstance(); 
    elementRacine = documentSortie.createElement("remboursements");
    documentSortie.appendChild(elementRacine);
    
    Integer total = 0;
    
        for (Iterator<Remboursement> it = remboursements.iterator(); it.hasNext();)
        {
            Remboursement remboursement = it.next();
            ajouterUnRemboursementaDocumentSortie(remboursement);
        }
    }

private void ajouterUnRemboursementaDocumentSortie( Remboursement remboursement)
{
    
}

private void ajouterTotalRemboursementsaDocumentSortie( Integer total)
{
    
}

protected void redigerDocumentSortie(Throwable exception)
    {
    docFactory = DocumentBuilderFactory.newInstance(); 
    elementRacine = documentSortie.createElement("remboursements");
    documentSortie.appendChild(elementRacine);
    
    
    }







protected void produireFichierSortie() throws ExceptionIO 
{
    this.fichierSortie = new File(this.filePath);

    TransformerFactory transformeurFactory = TransformerFactory.newInstance();

    try {
        Transformer transformeur = transformeurFactory.newTransformer();
        DOMSource source = new DOMSource(this.documentSortie);
        StreamResult result = new StreamResult(fichierSortie);
        transformeur.transform(source, result);
        }
    catch (TransformerException exTr)
        {
        throw new ExceptionIO("TransformerException sur "+fichierSortie.getName()+ " Message: "+exTr.getMessage());
        }

    System.out.println("Fichier " + fichierSortie.getName() + " : créé");
    }


}
