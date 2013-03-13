
import java.io.File;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class XML_Output
{
String filePath;
File fichierSortie;

private Element elementRacine;
private Document documentSortie;

XML_Output(Document documentSortie, String filePath) throws ExceptionIO
    {
    this.filePath = filePath;
    this.documentSortie = documentSortie;
    produireFichierSortie(); 
    }

protected void redigerDocumentSortie()
{
elementRacine = documentSortie.createElement("remboursements");
documentSortie.appendChild(elementRacine);
    
}


private void produireFichierSortie() throws ExceptionIO 
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
