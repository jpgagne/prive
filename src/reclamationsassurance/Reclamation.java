package reclamationsassurance;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Reclamation {
    
private Integer intNoSoin;
private String strSoin;
private String strDate;
private Date dateValide;
private String strMontantReclame;
private String strMontantFormate;
private Double doubleMontantFormate = 0.0;
private Double dMontantRemboursable = 0.0;
//private char charTypeContrat;
//private Contrat contrat;

// Constructeur
Reclamation(Integer intNoSoin, String strDate, String strMontantReclame) throws ExceptionFinProgramme {  
    
    this.intNoSoin = intNoSoin;
    validerDate(strDate);
    validerMontantReclame(strMontantReclame);
    
//    this.charTypeContrat = typeContrat; //Deja validé - pcq une seule fois par document
//   this.strSoin = strSoin; // pas encore validé à ce point - dans objet 'Contrat'
//        try 
//            {
//          contrat = new Contrat(charTypeContrat);
//           } 
//        catch (ExceptionDonneeInvalide ex) 
//            {
//            throw new ExceptionFinProgramme(ex.getMessage());
//            }
}


private void validerMontantReclame(String strMontantReclame) throws ExceptionFinProgramme
{

    
    if (strMontantReclame.length() == 0)
    {     
        throw new ExceptionFinProgramme("Montant reclame - champ vide");
    }     
    if (strMontantReclame.charAt(strMontantReclame.length()-1) != '$')
    {     
        throw new ExceptionFinProgramme("Montant non suivi de '$': "+ strMontantReclame);
    }
    
    strMontantReclame = strMontantReclame.substring(0,strMontantReclame.length()-1);
    
    
    try
    {
        this.doubleMontantFormate = Double.parseDouble(strMontantReclame);
    
    }
    catch (NumberFormatException excNF)
    {
        throw new ExceptionFinProgramme("Montant invalide: "+ strMontantReclame);
    }
    NumberFormat nombreFormat = NumberFormat.getCurrencyInstance( java.util.Locale.CANADA );
       this.strMontantFormate = nombreFormat.format(this.getDoubleMontantFormate());
       
       
}

private void validerDate(String strDate) throws ExceptionFinProgramme
{
 try 
    {  
    DateFormat formatter ; 
    Date date;
    formatter = new SimpleDateFormat("yyyy-MM-dd");
    
    date = (Date)formatter.parse(strDate);  
    this.strDate = strDate;
    this.dateValide = date;
    } 
 catch (ParseException exceptionParsingDate)
    {
      //throw new ExceptionFinProgramme("Format de date invalide: "+strDate); 
        System.out.println(" Erreur de parsing date: "+ strDate);
    } 

 
 // *** A faire = date dans mois en cours
 // *** A faire = mois français
}

    /**
     * @return the strSoin
     */
//    public String getStrSoin() {
//        return strSoin;
//    }


    public Integer getIntNoSoin()
    {
        return this.intNoSoin;
    }
    /**
     * @return the dateValide
     */
    public Date getDateValide() {
        return dateValide;
    }

    /**
     * @return the strMontantFormate
     */
    public String getStrMontantFormate() {
        return strMontantFormate;
    }

    /**
     * @return the doubleMontantFormate
     */
    public Double getDoubleMontantFormate() {
        return doubleMontantFormate;
    }

    /**
     * @return the strDate
     */
    public String getStrDate() {
        return strDate;
    }

    /**
     * @return the dMontantRemboursable
     */
    public Double getdMontantRemboursable() {
        return dMontantRemboursable;
    }

    /**
     * @param dMontantRemboursable the dMontantRemboursable to set
     */
    public void setdMontantRemboursable(Double dMontantRemboursable) {
        this.dMontantRemboursable = dMontantRemboursable;
    }

    
    
}
