package reclamationsassurance;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XmlAssurancesParser {

//<editor-fold defaultstate="collapsed" desc="Objets accès XML">
    File fichierInput;
    Document docInput;
    DocumentBuilderFactory docBuilderFactoryInput;
    DocumentBuilder docBuilderInput;
    File fichierOutput;
    DocumentBuilderFactory docFactoryOutput;
    DocumentBuilder docBuilderOutput;
    Document docOutput;
    Element rootElement;
//</editor-fold>
    private char typeContrat;
    private Integer noClient;
    private Contrat contrat = null;

    /*
     * Constructeur
     */
    public XmlAssurancesParser(String nomFichierInput, String nomFichierOutput) throws ExceptionFinProgramme {
        System.out.println("Objet parseur créé");
        ouvrirFichierEntree(nomFichierInput);
        ouvrirFichierSortie(nomFichierOutput);
        execution();
        produireFichier();
    }

//<editor-fold defaultstate="collapsed" desc="Ouvrir Fichiers">
/* Ouvre le fichier d'entree et cree l'objet documentImput, 
 * ExceptionFinProgramme détaillé si problème*/
    private void ouvrirFichierEntree(String nomFichierInput) throws ExceptionFinProgramme {
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
            throw new ExceptionFinProgramme(fnfExc.getMessage());
            
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            System.out.println("ERREUR - " + ex.getMessage());
            throw new ExceptionFinProgramme(ex.getMessage());
            
        } finally {
            System.out.println("Document " + fichierInput.getName() + " : " + Boolean.toString(succes));
        }
    }

    /* Prépare (ouvre ou crée) le fichier de sortie (vide) 
     ExceptionFinProgramme détailé si problème I/O  */
    private void ouvrirFichierSortie(String nomFichierOutput) throws ExceptionFinProgramme {
        fichierOutput = new File(nomFichierOutput);
        System.out.println("Ouverture du fichier sortie " + fichierOutput.getName());

        boolean succes = false;
        try {
            docFactoryOutput = DocumentBuilderFactory.newInstance();
            docBuilderOutput = docFactoryOutput.newDocumentBuilder();
            docOutput = docBuilderOutput.newDocument();
            succes = true;
            
        } catch (ParserConfigurationException ex) {
            throw new ExceptionFinProgramme(ex.getMessage());
            
        } finally {
            System.out.println("Document " + fichierOutput.getName() + " : " + Boolean.toString(succes));
        }
    }
//</editor-fold>

    private void execution() throws ExceptionFinProgramme {
        rootElement = docOutput.createElement("remboursements");
        docOutput.appendChild(rootElement);

        System.out.println("Element noeud :" + docInput.getDocumentElement().getNodeName());
        
        // Valider le numéro de client
        NodeList nodeList = docInput.getElementsByTagName("client");
        Element elementClient = (Element) nodeList.item(0);
        String strNoClient = ((Text) elementClient.getFirstChild()).getData();
        Integer intNoClient = 0;

        try {
            intNoClient = Integer.valueOf(strNoClient);

        } catch (NumberFormatException nfexc) {
            throw new ExceptionFinProgramme(" Format numero de client invalide: " + strNoClient);
        }

        if ((intNoClient > 999999) || (intNoClient < 100000)) {
            throw new ExceptionFinProgramme(" Numero de client hors bornes: " + intNoClient);
        }

        this.noClient = intNoClient;
        System.out.println("No Client: " + this.noClient);


        // Valider le type de contrat
        nodeList = docInput.getElementsByTagName("contrat");
        Element elementContrat = (Element) nodeList.item(0);
        String strContrat = ((Text) elementContrat.getFirstChild()).getData();
        char charTypeContrat;

        if (strContrat.length() != 1) {
            throw new ExceptionFinProgramme("Type de contrat format invalide");
        }

        charTypeContrat = strContrat.charAt(0);
        charTypeContrat = Character.toUpperCase(charTypeContrat);
        
        if (!((charTypeContrat == 'A') || (charTypeContrat == 'B') || (charTypeContrat == 'C') || (charTypeContrat == 'D'))) {
            throw new ExceptionFinProgramme("Type de contrat inconnu: " + charTypeContrat);
        }

        this.typeContrat = charTypeContrat;
        this.contrat = new Contrat(charTypeContrat);
        System.out.println("Type de contrat: " + this.typeContrat);

        NodeList listeNoeudsReclamation = docInput.getElementsByTagName("reclamation");

        int intNbReclamations = listeNoeudsReclamation.getLength();
        System.out.println("Nombre de réclamations lues: " + intNbReclamations);

        for (int compteur = 0; compteur < listeNoeudsReclamation.getLength(); compteur++) {
            Node noeudReclamation = listeNoeudsReclamation.item(compteur);
            if (noeudReclamation.getNodeType() == Node.ELEMENT_NODE) {
                Integer intNoSoin = -1;
                Element elementReclamation = (Element) noeudReclamation;

                // tag <soin>
                NodeList listeNoeudSoin = elementReclamation.getElementsByTagName("soin");
                if (listeNoeudSoin.getLength() != 1) {
                    throw new ExceptionFinProgramme("nb d'éléments 'soin' = " + listeNoeudSoin.getLength());
                }
                
                Element elementSoin = (Element) listeNoeudSoin.item(0);
                NodeList sousListe = elementSoin.getChildNodes();
                if (sousListe.getLength() != 1) {
                    throw new ExceptionFinProgramme("nb d'éléments 'soin' = " + sousListe.getLength());
                }

                String strSoin = ((Node) sousListe.item(0)).getNodeValue().trim();

                try {
                    intNoSoin = Integer.parseInt(strSoin);
                } catch (NumberFormatException excNF) {
                    throw new ExceptionFinProgramme("No de soin format non numerique = " + strSoin);
                }

                // tag <date>
                NodeList listeNoeudDate = elementReclamation.getElementsByTagName("date");
                if (listeNoeudDate.getLength() != 1) {
                    throw new ExceptionFinProgramme("nb d'éléments 'date' = " + listeNoeudDate.getLength());
                }
                
                Element elementDate = (Element) listeNoeudDate.item(0);
                sousListe = elementDate.getChildNodes();
                if (sousListe.getLength() != 1) {
                    throw new ExceptionFinProgramme("nb d'éléments 'date' = " + sousListe.getLength());
                }
                String strDate = ((Node) sousListe.item(0)).getNodeValue().trim();

                // tag <montant>
                NodeList listeNoeudMontant = elementReclamation.getElementsByTagName("montant");
                if (listeNoeudDate.getLength() != 1) {
                    throw new ExceptionFinProgramme("nb d'éléments 'montant' = " + listeNoeudMontant.getLength());
                }
                
                Element elementMontant = (Element) listeNoeudMontant.item(0);
                sousListe = elementMontant.getChildNodes();
                if (sousListe.getLength() != 1) {
                    throw new ExceptionFinProgramme("nb d'éléments 'montant' = " + sousListe.getLength());
                }  
                String strMontant = ((Node) sousListe.item(0)).getNodeValue().trim();
                
                System.out.println(strSoin + " - " + strDate + " - " + strMontant);

                Reclamation nouvelleReclamation = new Reclamation(intNoSoin, strDate, strMontant);

                nouvelleReclamation.setdMontantRemboursable(contrat.calculerRemboursement(intNoSoin, nouvelleReclamation.getDoubleMontantFormate()));
                ecrireRemboursement(nouvelleReclamation);
            } //end if

        }
         
        // Le total
        Element total = docOutput.createElement("total"); // <message>
        total.setTextContent("valeur total ici");
        docOutput.getDocumentElement().appendChild(total); // </message>
    }

    private void ecrireRemboursement(Reclamation reclamation) throws ExceptionFinProgramme {

        Double montantRembourse = reclamation.getdMontantRemboursable();

        System.out.println("Montant remboursé = " + montantRembourse);

        Element remboursement = this.docOutput.createElement("remboursement");

        Element soin = docOutput.createElement("soin");
        soin.appendChild(docOutput.createTextNode(reclamation.getIntNoSoin().toString()));
        remboursement.appendChild(soin);


        Element date = docOutput.createElement("date");
        date.appendChild(docOutput.createTextNode(reclamation.getStrDate()));
        remboursement.appendChild(date);


        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);
        String strMontantRembourse = currencyFormatter.format(montantRembourse);
        Element montant = docOutput.createElement("montant");
        montant.appendChild(docOutput.createTextNode(strMontantRembourse));
        remboursement.appendChild(montant);

        rootElement.appendChild(remboursement);

    }

    private void produireFichier() throws ExceptionFinProgramme {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException ex) {
            throw new ExceptionFinProgramme(ex.getMessage());
        }

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(docOutput);
        StreamResult result = new StreamResult(fichierOutput);

        try {
            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);
        } catch (TransformerException ex) {
            throw new ExceptionFinProgramme(ex.getMessage());
        }

        System.out.println("File saved!");
    }
    
    /*
     * Nouvelle Partie: Message d'erreur dans XML resultat (A effacer si ca bogue)
     * EffaceTout() efface tout ce qui est dans <reclamations></reclamations>
     * MessageErreur() genere le message <remboursements><message>Donnees invalides</message></remboursements>
     */

    /*
     public static void EffaceTout() {
     Node rc = docInput.getElementsByTagName("reclamations").item(0);
     NodeList list = rc.getChildNodes();

     for (int i = 0; i < list.getLength(); i++) {
     Node node = list.item(i);
     if ("contrat".equals(node.getNodeName()) 
     || "client".equals(node.getNodeName()) 
     || "mois".equals(node.getNodeName())
     || "reclamation".equals(node.getNodeName())) {
     rc.removeChild(node);
     }
     }
     }

     public static void MessageErreur() {
     NodeList reclamationsBalise = docInput.getElementsByTagName("reclamations");
        
     for (int i = 0; i < reclamationsBalise.getLength(); i++) {
     docInput.renameNode(reclamationsBalise.item(i), null, "remboursements");
     }
        
     Element rembourse = docOutput.createElement("message"); // <message>
     rembourse.setTextContent("Donnees invalides");
     docOutput.getDocumentElement().appendChild(rembourse); // </message>
     }
    
     public static void lanceMessageErreur() throws Exception {
     EffaceTout();
     MessageErreur();
     System.exit(0);
     }
     */
}
