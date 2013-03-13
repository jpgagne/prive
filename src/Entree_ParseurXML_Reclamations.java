
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Entree_ParseurXML_Reclamations {

//<editor-fold defaultstate="collapsed" desc="Objets accès XML">
    private  File fichierInput;
    private  Document docInput;
    private  DocumentBuilderFactory docBuilderFactoryInput;
    private  DocumentBuilder docBuilderInput;
    
//</editor-fold>
    
    private Evaluateur evaluateur;

    private char typeContrat;
    private Integer noClient;
    private Date moisTraite;


    
public Entree_ParseurXML_Reclamations(String nomFichierInput) throws ExceptionDonneeInvalide, ExceptionUsage 
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

protected  Evaluateur  parserFichierReclamations() throws ExceptionDonneeInvalide, ExceptionUsage
{

NodeList nodeListDossier = docInput.getElementsByTagName("dossier");
this.noClient = parseValideNoClient(nodeListDossier);
this.typeContrat = parseValideTypeContrat(nodeListDossier);

NodeList nodeListMois = docInput.getElementsByTagName("mois");
this.moisTraite = parserValiderMoisTraite(nodeListMois);

evaluateur = new Evaluateur(getNoClient(), getTypeContrat(), getMoisTraite());

NodeList listeNoeudsReclamation = docInput.getElementsByTagName("reclamation");
parserValiderListeReclamations(listeNoeudsReclamation);

return  evaluateur;

}

//<editor-fold defaultstate="collapsed" desc="Parser entete">
private Integer parseValideNoClient(NodeList nodeList) throws ExceptionDonneeInvalide
    {
    Element elementClient = (Element) nodeList.item(0);
    String strDossier = ((Text) elementClient.getFirstChild()).getData();
    String strNoClient = strDossier.substring(1);   // Retrait du premier caractere, en principe la lettre du contrat
    Integer intNoClient = 0;
    
    try {
        intNoClient = Integer.valueOf(strNoClient);
    } catch (NumberFormatException nfexc)
    {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.NOCLIENT_INVALIDE, strNoClient);
    }
    
    if ((intNoClient > 999999) || (intNoClient < 100000))
    {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.NOCLIENT_INVALIDE, intNoClient.toString());
    }
    
    return intNoClient;
    }



private char parseValideTypeContrat(NodeList nodeList) throws ExceptionDonneeInvalide
{
    Element elementContrat = (Element) nodeList.item(0);
    String strDossier = ((Text) elementContrat.getFirstChild()).getData();
    
    char charTypeContrat;
    
    if (strDossier.length() < 1) {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.CONTRAT_INVALIDE, strDossier);
    }
    charTypeContrat = strDossier.charAt(0);
    
    return valider_Code_Contrat(charTypeContrat);
}

private char valider_Code_Contrat(char charTypeContrat) throws ExceptionDonneeInvalide  // todo -> à intégrer avec chargement dynamique
{
    if (!((charTypeContrat == 'A') || (charTypeContrat == 'B') || (charTypeContrat == 'C') || (charTypeContrat == 'D')|| (charTypeContrat == 'E')))
    {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.CONTRAT_INVALIDE, Character.toString(charTypeContrat));
    }
    return charTypeContrat;
}




private Date parserValiderMoisTraite(NodeList nodeList) throws ExceptionDonneeInvalide, ExceptionUsage
{
    if (nodeList.getLength() != 1)
    {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.MOIS_ABSENT,
                "nb d'éléments 'date' = " + nodeList.getLength());
    }
    Element elementMois = (Element) nodeList.item(0);
    String strMois = ((Text) elementMois.getFirstChild()).getData();
    
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
    try{
        Date date = (Date)formatter.parse(strMois);
        return date;
    }
    catch (ParseException exceptionParsingDate)
    {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.DATE_FORMAT_INVALIDE, strMois);
    }
}
//</editor-fold>


private void parserValiderListeReclamations(NodeList listeNoeudsReclamation) throws ExceptionDonneeInvalide, ExceptionUsage
{ 
System.out.println("Nb objets Reclamation lus = "+ listeNoeudsReclamation.getLength());

for (int compteur = 0; compteur < listeNoeudsReclamation.getLength(); compteur++)
    {
    Node noeudReclamation = listeNoeudsReclamation.item(compteur);
    if (noeudReclamation.getNodeType() == Node.ELEMENT_NODE)
            {Reclamation nouvelleReclamation = parserValiderReclamation(noeudReclamation);
            evaluateur.ajouterReclamation(nouvelleReclamation);
            }
    else throw new ExceptionUsage("Structure XML non conforme sous balise <reclamation> no "+compteur);
    }
}

private Reclamation parserValiderReclamation(Node noeudReclamation) throws ExceptionDonneeInvalide
{
Element elementReclamation = (Element) noeudReclamation;

Intervalle intervalleNoSoin = parserNumeroSoin(elementReclamation);
EnumCategorieSoin catSoin;
try {
    catSoin = validerCategorieSoin(intervalleNoSoin);
    }
    catch (ExceptionValeurInexistante excValIn)
        {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.CODESOIN_INCONNU, intervalleNoSoin.toString());
        }

Date dateSoin = parserValiderDateSoin(elementReclamation);
if (!dateDansMoisEnCours(dateSoin))
{
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMMM", Locale.FRENCH);
    throw new ExceptionDonneeInvalide(EnumErreurLecture.DATE_MAUVAISMOIS, 
            "Attendu: " + dateFormat.format(dateSoin)
            +"; trouvé: "+dateFormat.format(this.getMoisTraite()));
}
Double montantReclame = parserValiderMontantReclame(elementReclamation);

return new Reclamation(catSoin, dateSoin, montantReclame, getTypeContrat());
} 



private EnumCategorieSoin validerCategorieSoin(Intervalle intervalleNoSoin) throws ExceptionValeurInexistante
    {
    EnumMapConversion<EnumCategorieSoin> enumSoinConversion =
                     new EnumMapConversion(EnumCategorieSoin.class);
    EnumCategorieSoin catSoinValide = enumSoinConversion.get(intervalleNoSoin);
    return catSoinValide;
}


private Intervalle parserNumeroSoin(Element elementReclamation) throws ExceptionDonneeInvalide
    {
    NodeList listeNoeudSoin = elementReclamation.getElementsByTagName("soin");
    if (listeNoeudSoin.getLength() != 1)
        {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.NOSOIN_ABSENT,
                        "nb d'éléments 'soin' = " + listeNoeudSoin.getLength());
        }
    Element elementSoin = (Element) listeNoeudSoin.item(0);
    NodeList sousListe = elementSoin.getChildNodes();
    if (sousListe.getLength() != 1)
        {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.NOSOIN_ABSENT, 
                    "nb d'éléments 'soin' = " + sousListe.getLength());
        }
    String strSoin = ((Node) sousListe.item(0)).getNodeValue().trim();
    try {
        return ParseurNombres.parseChainePourIntervalle(strSoin);
        }
    catch (ExceptionParseur excP)
        {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.NOSOIN_INVALIDE, strSoin);
        }
    }

 private Date parserValiderDateSoin(Element elementReclamation) throws ExceptionDonneeInvalide 
{
NodeList listeNoeudDate = elementReclamation.getElementsByTagName("date");
if (listeNoeudDate.getLength() != 1)
    {
    throw new ExceptionDonneeInvalide(EnumErreurLecture.DATE_ABSENT,
                        "nb d'éléments 'date' = " + listeNoeudDate.getLength());
    }

Element elementDate = (Element) listeNoeudDate.item(0);
NodeList sousListe = elementDate.getChildNodes();
if (sousListe.getLength() != 1)
    {
    throw new ExceptionDonneeInvalide(EnumErreurLecture.DATE_ABSENT,
            "nb d'éléments 'date' = " + sousListe.getLength());
    }
String strDate = ((Node) sousListe.item(0)).getNodeValue().trim();
Date date = validerFormatDate(strDate);
if (dateDansMoisEnCours(date))
   {
   return date;
   }
else
    {
    throw new ExceptionDonneeInvalide(EnumErreurLecture.DATE_MAUVAISMOIS, strDate);
    }
}
 
 
private Date validerFormatDate(String strDate) throws ExceptionDonneeInvalide 
{
 try 
    { 
    DateFormat formatter ; 
    Date date;
    formatter = new SimpleDateFormat("yyyy-MM-dd");
    date = (Date)formatter.parse(strDate);  
    return date;
    } 
 catch (ParseException exceptionParsingDate)
    {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.DATE_FORMAT_INVALIDE, strDate);
    } 


}
 
private boolean dateDansMoisEnCours(Date date)
{
Calendar calendrierMoisEnCours = new GregorianCalendar();
calendrierMoisEnCours.setTime(this.getMoisTraite());

Calendar calendrierDateLue = new GregorianCalendar();
calendrierDateLue.setTime(date);

return (calendrierDateLue.get(Calendar.MONTH) == calendrierMoisEnCours.get(Calendar.MONTH));
}

private Double parserValiderMontantReclame(Element elementReclamation) throws ExceptionDonneeInvalide
{
        NodeList listeNoeudMontant = elementReclamation.getElementsByTagName("montant");
        if (listeNoeudMontant.getLength() != 1) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.MONTANT_ABSENT, 
                    "nb d'éléments 'montant' = " + listeNoeudMontant.getLength());
        }

        Element elementMontant = (Element) listeNoeudMontant.item(0);
        NodeList sousListe = elementMontant.getChildNodes();

        if (sousListe.getLength() != 1) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.MONTANT_ABSENT,
                    "nb d'éléments 'montant' = " + sousListe.getLength());
        }  

        String strMontant = ((Node) sousListe.item(0)).getNodeValue().trim();
        Double montant = validerMontant(strMontant);
        if (montant < 0)
        {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.MONTANT_NEGATIF, strMontant);
        }
    return montant;
}   

private Double validerMontant(String strMontant) throws ExceptionDonneeInvalide

    {
    if (strMontant.length() == 0)
    {     
        throw new ExceptionDonneeInvalide(EnumErreurLecture.MONTANT_FORMATINVALIDE, "champ vide");
    }     
    if (strMontant.charAt(strMontant.length()-1) != '$')
    {     
        throw new ExceptionDonneeInvalide(EnumErreurLecture.MONTANT_SIGNEDEPIASSE, strMontant);
    }
    
    strMontant = strMontant.substring(0,strMontant.length()-1);

    try
        {
        return Double.parseDouble(strMontant);
        }
    catch (NumberFormatException excNF)
        {
        throw new ExceptionDonneeInvalide(EnumErreurLecture.MONTANT_FORMATINVALIDE,strMontant);
        }

    }


    public char getTypeContrat() {
        return typeContrat;
    }

    public Integer getNoClient() {
        return noClient;
    }

    public Date getMoisTraite() {
        return moisTraite;
    }
}
