package inf2015.assurance;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;




public class Sortie_JSON
{

private File fichierSortie;
private JsonFactory jfactory;
private JsonGenerator jGenerator;
private ObjectMapper mapper;

private static Sortie_JSON instance = null;

public static Sortie_JSON getInstance()
    {
    if(instance == null)
        {
        instance = new Sortie_JSON();
        }
    return instance;
    }
private Sortie_JSON()
{
    
}

public void assignerFichierSortie(File fichierSortie)
{
    this.fichierSortie = fichierSortie;
}




public void produireSortie (ExceptionSpecifique exception)
    {
    try 
        {
        messageErreur(exception);
        }
    catch (IOException excIO) 
        {
        terminaisonErreurIO(excIO);
        }
    }


public void produireSortie(Evaluateur evaluateur)
{
ArrayList<EnregistrementJSON_Remboursement> listeEnregistrementsJSON = new ArrayList<EnregistrementJSON_Remboursement>();
for (Iterator<Remboursement> it = evaluateur.getRemboursements().iterator(); it.hasNext();) 
    {
    Remboursement remboursement = it.next();
    listeEnregistrementsJSON.add(remboursement.getEnregistrementJSON_Reclamation());
    }
this.jfactory = new JsonFactory();
try {
    this.jGenerator = jfactory.createJsonGenerator(fichierSortie, JsonEncoding.UTF8);
    jGenerator.writeStartObject();
    jGenerator.writeStringField("dossier",  Character.toString(evaluateur.getContrat().getTypeContrat())+evaluateur.getNoClient());
    SimpleDateFormat formatDate = new SimpleDateFormat(ParseurNombres.formatDateMois, Locale.CANADA_FRENCH);
    this.jGenerator.writeStringField("mois", formatDate.format(evaluateur.getMoisTraite()));
    jGenerator.writeFieldName("remboursements");
    jGenerator.writeStartArray(); 
        for (Iterator<EnregistrementJSON_Remboursement> it = listeEnregistrementsJSON.iterator(); it.hasNext();) {
            EnregistrementJSON_Remboursement ejsonr = it.next();
            ecrireUnRemboursement(ejsonr);  
        } 
    jGenerator.writeEndArray(); 
    jGenerator.writeEndObject();
    jGenerator.close();
    }
catch (IOException excIO)
    {
    terminaisonErreurIO(excIO);
    }
}

 private void ecrireUnRemboursement(EnregistrementJSON_Remboursement ejsonr) throws IOException
    {
    jGenerator.writeStartObject();
    jGenerator.writeStringField("soin",  ejsonr.soin);
    jGenerator.writeStringField("code", ejsonr.code);
    jGenerator.writeStringField("date",  ejsonr.date);
    jGenerator.writeStringField("montant", ejsonr.montant);
    jGenerator.writeEndObject();
    }

private void messageErreur(ExceptionSpecifique exception) throws IOException
{
    mapper = new ObjectMapper();
    mapper.defaultPrettyPrintingWriter().writeValue(fichierSortie, exception);
}
private void terminaisonErreurIO(IOException excIO)
{
    System.out.println(" ERREUR - IMPOSSIBLE d'ecrire dans le fichier de sortie "+fichierSortie.getName());
    System.out.println(excIO.getMessage());
}
}   // end of class Sortie_JSON