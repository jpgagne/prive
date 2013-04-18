import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
//import org.w3c.dom.Element;
import java.text.DecimalFormatSymbols;
import java.text.DecimalFormat;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SortieJSON {

    private String nomFichierSortie;
    private String strMoisTraite = "";
    private String dossierClient = "";
    private char contrat;
    private Integer numeroClient;
    private double totalRemboursements = 0.0;
    private Traitement traitement;
    private File fichierSortie;
    private JSONObject elementRacine;
    private Document documentSortie;
    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;
    private SimpleDateFormat formatDateMoisTraite;
    private SimpleDateFormat formatDateSoinSortie;
    private NumberFormat currencyFormatter;
    private Date moisTraite;
    private JSONObject document = new JSONObject();
    private JSONArray root = new JSONArray();

    SortieJSON() throws ExceptionIO {
        this.traitement = Traitement.getInstance();
        this.fichierSortie = traitement.getFichierSortant();
        this.nomFichierSortie = this.fichierSortie.getName();
        docFactory = DocumentBuilderFactory.newInstance();
        
        try {
            docBuilder = docFactory.newDocumentBuilder();
            
        } catch (ParserConfigurationException excPC) {
            throw new ExceptionIO("ParserConfigurationException sur " + fichierSortie.getName() + " Message: " + excPC.getMessage());
        }
        
        this.documentSortie = docBuilder.newDocument();
        this.formatDateSoinSortie = new SimpleDateFormat("yyyy-MM-dd", Locale.FRENCH);
        this.formatDateMoisTraite = new SimpleDateFormat("yyyy-MM", Locale.FRENCH);
        this.currencyFormatter = NumberFormat.getCurrencyInstance(Locale.CANADA);
    }

    protected void setDossier(char contrat, Integer numeroClient) {
        this.contrat = contrat;
        this.numeroClient = numeroClient;

        StringBuilder dossier = new StringBuilder();
        dossier.append(contrat);
        dossier.append(String.format("%06d", numeroClient));
        this.dossierClient = dossier.toString();

    }

    protected void setMoisTraite(Date moisTraite) {
        this.moisTraite = moisTraite;
        this.strMoisTraite = this.formatDateMoisTraite.format(moisTraite);
    }

    protected void redigerDocumentSortie(ArrayList<Remboursement> remboursements) throws ExceptionIO {

        if (this.dossierClient.isEmpty() | this.strMoisTraite.isEmpty()) // Assertion defensive
        {
            throw new ExceptionIO("Dossier client ou mois traite vide");
        }


        docFactory = DocumentBuilderFactory.newInstance();
        elementRacine = document.getJSONObject("remboursements");
        //documentSortie.appendChild(elementRacine);
        document.put("remboursements", elementRacine);


        ajouterDossieraDocumentSortie();
        ajouterMoisTraiteADocumentSortie();

        this.totalRemboursements = 0.0;

        for (Iterator<Remboursement> it = remboursements.iterator(); it.hasNext();) {
            Remboursement remboursement = it.next();
            ajouterUnRemboursementaDocumentSortie(remboursement);
            this.totalRemboursements = this.totalRemboursements + remboursement.getMontant();
            it.remove();
        }
        ajouterTotalRemboursementsaDocumentSortie();
    }

    protected void redigerDocumentSortie(Throwable exception) throws ExceptionIO {
        String message;
        docFactory = DocumentBuilderFactory.newInstance();
        
        elementRacine = document.getJSONObject("remboursements");
        document.put("remboursements", elementRacine);
        if (exception instanceof ExceptionDonneeInvalide) {
            ExceptionDonneeInvalide exceptionDonneeInvalide = (ExceptionDonneeInvalide) exception;
            message = exceptionDonneeInvalide.getLiteral();

        } else {
            message = exception.getMessage();
        }
        creerMessageErreur(message);
    }

    private void creerMessageErreur(String message) {
        //Element elementMessage = this.documentSortie.createElement("message");
        JSONObject elementMessage = this.document.getJSONObject("message");
        elementMessage.getString(message);
        System.out.println(message);

        this.elementRacine.put("message", elementMessage);
        
        try {
            produireFichierSortie();
        } catch (ExceptionIO ex) {
            quandCaVaMal(message);
        }
    }

    private void quandCaVaMal(String message) {
        System.out.println("Ça va vraiment pas bien!");
        System.out.println(" Exception entree/sortie sur le fichier de sortie " + this.nomFichierSortie);
        System.out.println(" Donc on pourra pas vous laisser un log de l'exception de type");
        System.out.println(message);

    }

    private void ajouterUnRemboursementaDocumentSortie(Remboursement remboursement) {
        //Element elementRemboursement = this.documentSortie.createElement("remboursements");
        JSONArray elementRemboursement = this.document.getJSONArray("remboursements");

        //Element elementSoin = this.documentSortie.createElement("soin");
        JSONObject elementSoin = this.document.getJSONObject("soin");
        
        //elementSoin.appendChild(this.documentSortie.createTextNode(remboursement.getSoin().toString()));
        //elementRemboursement.appendChild(elementSoin);
        elementSoin.put("soin", remboursement.getSoin().toString());
        elementRemboursement.add(elementSoin);

        String strDate = formatDateSoinSortie.format(remboursement.getDate());
        //Element elementDateSoin = this.documentSortie.createElement("date");
        JSONObject elementDateSoin = this.document.getJSONObject("date");
        
        //elementDateSoin.appendChild(this.documentSortie.createTextNode(strDate));
        //elementRemboursement.appendChild(elementDateSoin);
        elementDateSoin.put("date", strDate);
        elementRemboursement.add(elementDateSoin);

        String strMontantRembourse = formateWeirdo(remboursement.getMontant());
        //Element elementMontant = this.documentSortie.createElement("montant");
        //elementMontant.appendChild(this.documentSortie.createTextNode(strMontantRembourse));
        JSONObject elementMontant = this.document.getJSONObject("montant");
        elementMontant.put("montant", strMontantRembourse);
        
        //elementRemboursement.appendChild(elementMontant);
        elementRemboursement.add(elementMontant);

        //this.elementRacine.appendChild(elementRemboursement);
        this.document.getJSONArray(elementRemboursement.toString());
    }

    private void ajouterDossieraDocumentSortie() {
        //Element elementDossier = this.documentSortie.createElement("dossier");
        JSONObject elementDossier = this.document.getJSONObject("dossier");
        //elementDossier.appendChild(this.documentSortie.createTextNode(dossierClient));
        elementDossier.put("dossier", dossierClient);
        //this.elementRacine.appendChild(elementDossier);
        this.document.getJSONArray(elementDossier.toString());
    }

    private void ajouterMoisTraiteADocumentSortie() {
        //Element elementMoisTraite = this.documentSortie.createElement("mois");
        JSONObject elementMoisTraite = this.document.getJSONObject("mois");
        //elementMoisTraite.appendChild(this.documentSortie.createTextNode(strMoisTraite));
        elementMoisTraite.put("mois", strMoisTraite);
        //this.elementRacine.appendChild(elementMoisTraite);
        this.document.getJSONArray(elementMoisTraite.toString());
    }

    private void ajouterTotalRemboursementsaDocumentSortie() {
        String strTotalRemboursements = formateWeirdo(totalRemboursements);
        System.out.println("TOTAL = " + strTotalRemboursements);
        //Element elementTotalRemboursements = this.documentSortie.createElement("total");
        JSONObject elementTotalRemboursements = this.document.getJSONObject("total");
        //elementTotalRemboursements.appendChild(this.documentSortie.createTextNode(strTotalRemboursements));
        elementTotalRemboursements.put("total", strTotalRemboursements);
        //this.elementRacine.appendChild(elementTotalRemboursements);
        this.document.getJSONArray(elementTotalRemboursements.toString());
    }

    protected void produireFichierSortie() throws ExceptionIO {
        TransformerFactory transformeurFactory = TransformerFactory.newInstance();

        try {
            Transformer transformeur = transformeurFactory.newTransformer();
            transformeur.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(this.documentSortie);
            StreamResult result = new StreamResult(this.fichierSortie);
            transformeur.transform(source, result);
            
        } catch (TransformerException exTr) {
            throw new ExceptionIO("TransformerException sur " + fichierSortie.getName() + " Message: " + exTr.getMessage());
        }

        System.out.println("Fichier " + fichierSortie.getName() + " : créé");
    }

    private String formateWeirdo(Double valeurDouble) {
        DecimalFormat formatDecimal = new DecimalFormat("#,###,##0.00");
        String str = formatDecimal.format(valeurDouble).toString().trim().replace(',', '.') + '$';
        return str;
    }
}
