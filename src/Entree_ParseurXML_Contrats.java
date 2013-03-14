import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class Entree_ParseurXML_Contrats 
{
    private CategoriesSoin categoriesSoin;
    private ListeContrats listeContrats;

    private File fichierInput;
    private Document docInput;
    private DocumentBuilderFactory docBuilderFactoryInput;
    private DocumentBuilder docBuilderInput;
    private Map<Soin, Couverture> mapCouvertures;
    



    
public Entree_ParseurXML_Contrats(String nomFichierInput) throws ExceptionDonneeInvalide, ExceptionUsage 
{
    ouvrirFichierEntree(nomFichierInput);
}

//<editor-fold defaultstate="collapsed" desc="Ouvrir Fichiers">
/* Ouvre le fichier d'entree et cree l'objet documentImput, 
     * ExceptionUsage détaillé si problème*/
    private void ouvrirFichierEntree(String nomFichierInput) throws ExceptionUsage {
        fichierInput = new File(nomFichierInput);
        System.out.println("Ouverture du fichier entrée " + fichierInput.getName());

        docBuilderFactoryInput = DocumentBuilderFactory.newInstance();
        boolean succes = false;
        try {
            docBuilderInput = docBuilderFactoryInput.newDocumentBuilder();
            docInput = docBuilderInput.parse(fichierInput);
            docInput.getDocumentElement().normalize();     //Répare les balises XML mal formées
            succes = true;
            
        } catch (java.io.FileNotFoundException fnfExc) {
            System.out.println("Fichier: " + fichierInput.getAbsolutePath() + " non trouvé");
            throw new ExceptionUsage(fnfExc.getMessage());
            
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            System.out.println("ERREUR - " + ex.getMessage());
            throw new ExceptionUsage(ex.getMessage());
            
        } finally {
            System.out.println("Document " + fichierInput.getName() + " : " + Boolean.toString(succes));
        }
    }
//</editor-fold>

protected  Map<Character, Contrat>  parserFichierContrats() throws ExceptionDonneeInvalide, ExceptionUsage, ExceptionSoinNonCouvert
{
Map<Character,Contrat> mapContrats = new HashMap<>();
NodeList listeNoeudsContrat = docInput.getElementsByTagName("contrat");
System.out.println("Nb objets Contrat lus= "+ listeNoeudsContrat.getLength());

for (int compteur = 0; compteur < listeNoeudsContrat.getLength(); compteur++)
    {
    Node noeudContrat = listeNoeudsContrat.item(compteur);
    if (noeudContrat.getNodeType() == Node.ELEMENT_NODE)
        {
        Contrat nouveauContrat = parserValiderContrat(noeudContrat);
        Character carTypeContrat = nouveauContrat.getTypeContrat();
        if (mapContrats.containsKey(carTypeContrat))
            {
            throw new ExceptionDonneeInvalide("Contrat |"+carTypeContrat+"| dédoublé");
            }
        
        
        this.mapCouvertures = parserValiderListeCouvertures((Element) noeudContrat);
        
        nouveauContrat.setMapCouvertures(mapCouvertures);
        mapContrats.put(carTypeContrat, nouveauContrat);
        }
    else
        {
        throw new ExceptionUsage("Structure XML non conforme sous balise <contrat> no "+compteur);
        }
    }
return mapContrats;
}



private Map<Soin, Couverture> parserValiderListeCouvertures(Element elementContrat) throws ExceptionDonneeInvalide, ExceptionSoinNonCouvert
    {
    this.mapCouvertures = new HashMap<>();
    NodeList listeNoeudsCouverture = elementContrat.getElementsByTagName("couverture");
    String typeContrat = elementContrat.getAttribute("type");
    System.out.println("Nb objets Couverture lus pour Contrat "+typeContrat+" = "+ listeNoeudsCouverture.getLength());
    for (int compteur = 0; compteur < listeNoeudsCouverture.getLength(); compteur++)
        {
        Node noeudCouverture = listeNoeudsCouverture.item(compteur);
        if (noeudCouverture.getNodeType() == Node.ELEMENT_NODE)
            {
            Couverture nouvelleCouverture = parserValiderCouverture(noeudCouverture);
            System.out.println("Couverture chargee: "+nouvelleCouverture.toString());
            
            if (mapCouvertures.containsKey(nouvelleCouverture.getSoin()))
                {
                throw new ExceptionDonneeInvalide("Couverture "+nouvelleCouverture.getSoin().toString()+" dédoublé");
                }
            mapCouvertures.put(nouvelleCouverture.getSoin(), nouvelleCouverture);
            }
        else
            {                            
            throw new ExceptionDonneeInvalide("Structure XML non conforme sous balise <couverture> no "
                    +compteur+" du contrat "+typeContrat);
            }
        }
    return mapCouvertures;
    }


private Contrat parserValiderContrat(Node noeudContrat) throws ExceptionDonneeInvalide
{
Element elementContrat = (Element) noeudContrat;
String strTypeContrat = elementContrat.getAttribute("type");
if (strTypeContrat.length() != 1)
    {
    throw  new ExceptionDonneeInvalide("Type de contrat plus de 1 caractere: ("+strTypeContrat.length()+") cars : |"+strTypeContrat+"|");
    }
if ( strTypeContrat.toUpperCase() == null ? strTypeContrat != null : !strTypeContrat.toUpperCase().equals(strTypeContrat))
    {
    throw  new ExceptionDonneeInvalide("Type de contrat doit etre caractere majuscule: ("+ strTypeContrat +")");
    }
char carTypeContrat = strTypeContrat.charAt(0);

Contrat nouveauContrat = new Contrat(carTypeContrat);



return nouveauContrat;

}


private Couverture parserValiderCouverture (Node noeudCouverture) throws ExceptionDonneeInvalide, ExceptionSoinNonCouvert
{
    Soin soin;
Element elementCouverture = (Element) noeudCouverture;
Intervalle intervalleNumeroSoin = parserNumeroSoin(elementCouverture);

soin = categoriesSoin.trouverSoinIntervalle(intervalleNumeroSoin);
    
    
Double pourcentage = parserPourcentage(elementCouverture);
Boolean aMaximum = parserAMaximum(elementCouverture);
Double maximum;

Couverture nouvelleCouverture;
if (aMaximum)
    {
    maximum = parserMaximum(elementCouverture);
    nouvelleCouverture = new  Couverture(soin, pourcentage, maximum);
    }
else
    {
    nouvelleCouverture = new Couverture(soin, pourcentage);
    }  
return nouvelleCouverture;
}


private Intervalle parserNumeroSoin(Element elementCouverture) throws ExceptionDonneeInvalide
    {
    NodeList listeNoeudSoin = elementCouverture.getElementsByTagName("numeroSoin");
    if (listeNoeudSoin.getLength() != 1)
        {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.NOSOIN_ABSENT, "nb d'éléments 'soin' = " + listeNoeudSoin.getLength());
        }
    Element elementSoin = (Element) listeNoeudSoin.item(0);
    NodeList sousListe = elementSoin.getChildNodes();
    if (sousListe.getLength() != 1)
        {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.NOSOIN_ABSENT, "nb d'éléments 'soin' = " + sousListe.getLength());
        }
    
    String strSoin = ((Node) sousListe.item(0)).getNodeValue().trim();
   
        try {
            return   ParseurNombres.parseChainePourIntervalle(strSoin);
            }
        catch (ExceptionParseur excP)
            {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.NOSOIN_INVALIDE, strSoin);
            }

    }


private Double parserPourcentage(Element elementCouverture) throws ExceptionDonneeInvalide
    {
    NodeList listeNoeudPourcentage = elementCouverture.getElementsByTagName("pourcentage");
    if (listeNoeudPourcentage.getLength() != 1)
        {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.POURCENTAGE_ABSENT,
                            "nb d'éléments 'pourcentage' = " + listeNoeudPourcentage.getLength());
        }
    Element elementPourcentage = (Element) listeNoeudPourcentage.item(0);
    NodeList sousListe = elementPourcentage.getChildNodes();
    if (sousListe.getLength() != 1)
        {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.POURCENTAGE_ABSENT,
                            "nb d'éléments 'pourcentage' = "+ sousListe.getLength());
        }
    String strPourcentage = ((Node) sousListe.item(0)).getNodeValue().trim();
    try 
        {
        return Double.parseDouble(strPourcentage);
        }
    catch (NumberFormatException excNF)
        {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.POURCENTAGE_INVALIDE, strPourcentage);
        }
    }


private Boolean parserAMaximum(Element elementCouverture)
{
    NodeList listeNoeudMaximum = elementCouverture.getElementsByTagName("maximum");
    return (listeNoeudMaximum.getLength() == 1);
}

private Double parserMaximum(Element elementCouverture) throws ExceptionDonneeInvalide
{
    if (parserAMaximum(elementCouverture))
    {
        return  -1.0;
    }

    NodeList listeNoeudMaximum = elementCouverture.getElementsByTagName("maximum");
    Element elementMaximum = (Element) listeNoeudMaximum.item(0);
    NodeList sousListe = elementMaximum.getChildNodes();
    if (sousListe.getLength() != 1)
        {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.MAXIMUM_INVALIDE, "nb d'éléments 'maximum' = " + sousListe.getLength());
        }
    String strMaximum = ((Node) sousListe.item(0)).getNodeValue().trim();
    try {
        return  Double.parseDouble(strMaximum);
        }
    catch (NumberFormatException excNF)
        {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.MAXIMUM_INVALIDE, strMaximum);
        }
    }



}
