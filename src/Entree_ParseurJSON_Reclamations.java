import java.io.*;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;

public class Entree_ParseurJSON_Reclamations {

//singletons
    private ListeContrats listeContrats;
    private CategoriesSoin categoriesSoin;
//in
    private File fichierInput;
    
//out
    private Evaluateur evaluateur;
//local
    private char typeContrat;
    private Integer noClient;
    private Date moisTraite;
//</editor-fold>

    Entree_ParseurJSON_Reclamations(File fichierInput) throws ExceptionDonneeInvalide, ExceptionUsage, FileNotFoundException {
        this.categoriesSoin = CategoriesSoin.getInstance();
        this.listeContrats = ListeContrats.getInstance();
        this.fichierInput = fichierInput;
        ouvrirFichierEntree();
    }
    
    public static String loadFileIntoString(String filePath, String fileEncoding) throws FileNotFoundException, IOException {
        return IOUtils.toString(new FileInputStream(filePath), fileEncoding);
    }
        
    private void lireFichier() throws IOException{     
        String jsonTxt = loadFileIntoString(this.fichierInput.getAbsolutePath(), "UTF-8");
        System.out.println(jsonTxt);
    }
    
    
    private void ouvrirFichierEntree() throws ExceptionUsage, FileNotFoundException {
        JSONObject object = JSONObject.fromObject(new FileReader(fichierInput));
        JSONArray arr = object.getJSONArray("reclamations");
        
        try {
            for (int i = 0; i < arr.size(); i++) {
                JSONObject person = (JSONObject) arr.get(i);
                String soin = (String) person.get("soin");
                String date = (String) person.get("date");
                String montant = (String) person.get("montant");
                System.out.println("Soin: " + soin +  " Date: " + date + " Montant: " + montant);
            }
            throw new FileNotFoundException();
            
        } catch (FileNotFoundException fnfExc) {
            System.out.println("Fichier: " + fichierInput.getAbsolutePath() + " non trouvé");
            throw new ExceptionUsage(fnfExc.getMessage());
        } finally {
            System.out.println("Ouverture du fichier " + fichierInput.getName() + " . ");
        }
    }
    
    protected Evaluateur parserFichierReclamations() throws ExceptionDonneeInvalide, ExceptionUsage {
        JSONObject json = JSONObject.fromObject(fichierInput);
        JSONArray listDossierJSON = json.getJSONArray("dossier");
        JSONArray listMoisJSON = json.getJSONArray("mois");
        
        this.noClient = parseValideNoClient(listDossierJSON); 
        this.typeContrat = parseValideTypeContrat(listDossierJSON); 
        this.moisTraite = parserValiderMoisTraite(listMoisJSON); 

        try {
            this.evaluateur = new Evaluateur(getNoClient(), getTypeContrat(), getMoisTraite());
        } catch (ExceptionContratInexistant ex) {
            Logger.getLogger(Entree_ParseurJSON_Reclamations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.evaluateur;
    }

//<editor-fold defaultstate="collapsed" desc="methodes utilitaires private">
//</editor-fold>
    private Integer parseValideNoClient(JSONArray jsonList) throws ExceptionDonneeInvalide {
        if (jsonList.size() != 1) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.DOSSIER_ABSENT);
        }

        JSONObject elementClient = jsonList.getJSONObject(0);
        String strDossier = elementClient.getString(noClient.toString());
        String strNoClient = strDossier.substring(1);   // Retrait du premier caractere, en principe la lettre du contrat
        Integer intNoClient = 0;

        try {
            intNoClient = Integer.valueOf(strNoClient);

        } catch (NumberFormatException nfexc) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.NOCLIENT_INVALIDE, strNoClient);
        }

        if ((intNoClient > 999999) || (intNoClient < 100000)) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.NOCLIENT_INVALIDE, intNoClient.toString());
        }

        return intNoClient;
    }

    private char parseValideTypeContrat(JSONArray jsonList) throws ExceptionDonneeInvalide {
        JSONObject elementContrat = jsonList.getJSONObject(0);
        String strDossier = elementContrat.getString(Character.toString(typeContrat)); //
        
        char charTypeContrat;

        if (strDossier.length() < 1) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.CONTRAT_INVALIDE, strDossier);
        }
        charTypeContrat = strDossier.charAt(0);

        return valider_Code_Contrat(charTypeContrat);
    }

    private char valider_Code_Contrat(char charTypeContrat) throws ExceptionDonneeInvalide {
        if (!((charTypeContrat == 'A') || (charTypeContrat == 'B') || (charTypeContrat == 'C') || (charTypeContrat == 'D') || (charTypeContrat == 'E'))) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.CONTRAT_INVALIDE, Character.toString(charTypeContrat));
        }
        return charTypeContrat;
    }

    private Date parserValiderMoisTraite(JSONArray jsonList) throws ExceptionDonneeInvalide, ExceptionUsage {
        if (jsonList.size() != 1) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.MOIS_ABSENT,
                    "nb d'éléments 'date' = " + jsonList.size());
        }
        JSONObject elementMois = jsonList.getJSONObject(0);
        String strMois = elementMois.getString(moisTraite.toString());//

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        try {
            Date date = (Date) formatter.parse(strMois);
            return date;
        } catch (ParseException exceptionParsingDate) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.DATE_FORMAT_INVALIDE, strMois);
        }
    }

    private void parserValiderListeReclamations(JSONArray listeNoeudsReclamation) throws ExceptionDonneeInvalide, ExceptionUsage {
        //System.out.println("Nb objets Reclamation lus = "+ listeNoeudsReclamation.getLength());

        for (int compteur = 0; compteur < listeNoeudsReclamation.size(); compteur++) {
            JSONObject noeudReclamation = listeNoeudsReclamation.getJSONObject(compteur);
            if (noeudReclamation == JSONObject.fromObject(listeNoeudsReclamation)) {
                Reclamation nouvelleReclamation = parserValiderReclamation(listeNoeudsReclamation);
                this.evaluateur.ajouterReclamation(nouvelleReclamation);
            } else {
                throw new ExceptionUsage("Structure JSON non conforme sous ''reclamations'' no " + compteur);
            }
        }
    }

    private Reclamation parserValiderReclamation(JSONArray noeudReclamation) throws ExceptionDonneeInvalide {

        JSONObject elementReclamation = noeudReclamation.toJSONObject(noeudReclamation);

        Integer intNoSoin = parserNumeroSoin(elementReclamation);

        Soin soin;
        try {
            soin = validerCategorieSoin(intNoSoin);
        } catch (ExceptionSoinNonCouvert excSNC) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.CODESOIN_INCONNU, intNoSoin.toString());
        }

        Date dateSoin = parserValiderDateSoin(elementReclamation);
        if (!dateDansMoisEnCours(dateSoin)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMMM", Locale.FRENCH);
            throw new ExceptionDonneeInvalide(EnumErreurLecture.DATE_MAUVAIS_MOIS,
                    "Attendu: " + dateFormat.format(dateSoin)
                    + "; trouvé: " + dateFormat.format(this.getMoisTraite()));
        }
        Double montantReclame = parserValiderMontantReclame(elementReclamation);
        return new Reclamation(intNoSoin, dateSoin, montantReclame, getTypeContrat());
    }

    private Soin validerCategorieSoin(Integer integerNoSoin) throws ExceptionSoinNonCouvert {
        return categoriesSoin.getSoinParInteger(integerNoSoin);
    }

    private Integer parserNumeroSoin(JSONObject elementReclamation) throws ExceptionDonneeInvalide {
        JSONArray listeNoeudSoin = elementReclamation.getJSONArray("soin");
        if (listeNoeudSoin.size() != 1) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.NOSOIN_ABSENT,
                    "nb d'éléments 'soin' = " + listeNoeudSoin.size());
        }
        
        JSONObject elementSoin = listeNoeudSoin.getJSONObject(0);
        
        JSONArray sousListe = elementSoin.names();
        if (sousListe.size() != 1) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.NOSOIN_ABSENT,
                    "nb d'éléments 'soin' = " + sousListe.size());
        }
        String strSoin = (sousListe.iterator()).toString().trim();
        try {
            return ParseurNombres.parseChainePourInteger(strSoin);
        } catch (ExceptionParseur excP) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.NOSOIN_INVALIDE, excP.getMessage());
        }
    }

    private Date parserValiderDateSoin(JSONObject elementReclamation) throws ExceptionDonneeInvalide {
        JSONArray listeNoeudDate = elementReclamation.getJSONArray("date");
        if (listeNoeudDate.size() != 1) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.DATE_ABSENT,
                    "nb d'éléments 'date' = " + listeNoeudDate.size());
        }

        JSONObject elementDate = listeNoeudDate.getJSONObject(0);
        JSONArray sousListe = elementDate.names();
        if (sousListe.size() != 1) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.DATE_ABSENT,
                    "nb d'éléments 'date' = " + sousListe.size());
        }
        String strDate = (sousListe.iterator()).toString().trim();
        Date date = validerFormatDate(strDate);
        if (dateDansMoisEnCours(date)) {
            return date;
        } else {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.DATE_MAUVAIS_MOIS, strDate);
        }
    }

    private Date validerFormatDate(String strDate) throws ExceptionDonneeInvalide {
        try {
            DateFormat formatter;
            Date date;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = (Date) formatter.parse(strDate);
            return date;
        } catch (ParseException exceptionParsingDate) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.DATE_FORMAT_INVALIDE, strDate);
        }
    }

    private boolean dateDansMoisEnCours(Date date) {
        Calendar calendrierMoisEnCours = new GregorianCalendar();
        calendrierMoisEnCours.setTime(this.getMoisTraite());

        Calendar calendrierDateLue = new GregorianCalendar();
        calendrierDateLue.setTime(date);

        return (calendrierDateLue.get(Calendar.MONTH) == calendrierMoisEnCours.get(Calendar.MONTH));
    }

    private Double parserValiderMontantReclame(JSONObject elementReclamation) throws ExceptionDonneeInvalide {
        JSONArray listeNoeudMontant = elementReclamation.getJSONArray("montant");
        if (listeNoeudMontant.size() != 1) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.MONTANT_ABSENT,
                    "nb d'éléments 'montant' = " + listeNoeudMontant.size());
        }

        JSONObject elementMontant = listeNoeudMontant.getJSONObject(0);
        JSONArray sousListe = elementMontant.names();
        if (sousListe.size() != 1) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.MONTANT_ABSENT,
                    "nb d'éléments 'montant' = " + sousListe.size());
        }

        String strMontant = (sousListe.iterator()).toString().trim();
        Double montant = validerMontant(strMontant);
        if (montant < 0) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.MONTANT_NEGATIF, strMontant);
        }
        return montant;
    }

    private Double validerMontant(String strMontant) throws ExceptionDonneeInvalide {
        if (strMontant.length() == 0) {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.MONTANT_FORMAT_INVALIDE, "champ vide");
        }
        String strMontantTrim = strMontant.trim();
        if (strMontantTrim.charAt(strMontantTrim.length() - 1) != '$') {
            throw new ExceptionDonneeInvalide(EnumErreurLecture.MONTANT_SIGNE_DOLLAR, strMontant);
        }

        strMontant = strMontant.substring(0, strMontant.length() - 1);
        try {
            String strMontantSansEspaces = strMontant.replaceAll("\\s", "");
            return Double.parseDouble(strMontantSansEspaces);
        } catch (NumberFormatException excNF) {
            DecimalFormat formatDecimal = new DecimalFormat();
            DecimalFormatSymbols symbolesDecimaux = new DecimalFormatSymbols();
            symbolesDecimaux.setDecimalSeparator(',');
            formatDecimal.setDecimalFormatSymbols(symbolesDecimaux);
            try {
                return formatDecimal.parse(strMontant).doubleValue();
            } catch (ParseException ex) {
                throw new ExceptionDonneeInvalide(EnumErreurLecture.MONTANT_FORMAT_INVALIDE, strMontant);
            }

        }
    }

//<editor-fold defaultstate="collapsed" desc="accesseurs">
    protected char getTypeContrat() {
        return typeContrat;
    }

    protected Integer getNoClient() {
        return noClient;
    }

    protected Date getMoisTraite() {
        return moisTraite;
    }
//</editor-fold>
}
