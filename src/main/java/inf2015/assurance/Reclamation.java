package inf2015.assurance;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Reclamation {

    

private Integer noSoin;
private Date dateSoin;
private Double montantReclame;
private char typeContrat;
private String codeTypePrestataire;


EntreeReclamationJSON entreeReclamationJSON;

Reclamation (EntreeReclamationJSON entreeReclamationJSON)
{
    this.entreeReclamationJSON = entreeReclamationJSON;
}


public String getValeurs()
{
    SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd", Locale.FRENCH);
    return  "*RECLAMATION* "
            +"Date: "+dateFormat.format(dateSoin)
            +" / Categ: "+this.noSoin
            +" / Montant "+montantReclame;
}

    @Override
public String toString()
{
    return this.entreeReclamationJSON.toString();
}


    
    
public Date getDateSoin() {
    return dateSoin;
}

public Double getMontantReclame() {
    return montantReclame;
}

public char getTypeContrat() {
    return typeContrat;
}

public Integer getNoSoin()
{
    return this.noSoin;
}

    
    





}