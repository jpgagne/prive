package inf2015.assurance;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Remboursement
{
private Integer noSoin;
private Date date;
private Argent montant;
private String code;


Remboursement(Integer noSoin, Date date, Argent montant, String code)
    {
    this.montant = montant;
    this.noSoin = noSoin;
    this.date = date;
    this.code = code;
    }
public EnregistrementJSON_Remboursement getEnregistrementJSON_Reclamation()
{
    SimpleDateFormat dateFormat = new SimpleDateFormat(ParseurNombres.formatDateJour, Locale.CANADA_FRENCH);
    return new EnregistrementJSON_Remboursement(
            this.noSoin.toString(), this.code, dateFormat.format(this.date), this.montant.toString());
}
public Integer getSoin()
    {
    return this.noSoin;
    }

public Date getDate()
    {
    return date;
    }

public Argent getMontant()
    {
    return montant;
    }

    
    @Override
public String toString()
    {
    return "*REMBOURSEMENT* : Soin"+this.noSoin+", Date: "+this.date+", Montant: "+this.montant;
    }

    
    
    
    
    
    
    
    
    
}