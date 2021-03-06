
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Reclamation {
    
private Integer noSoin;
private Date dateSoin;
private Double montantReclame;
private char typeContrat;


Reclamation(Integer noSoin, Date dateSoin, Double montantReclame, char typeContrat) 
{  
this.noSoin = noSoin;
this.dateSoin = dateSoin;
this.montantReclame = montantReclame;
this.typeContrat = typeContrat;
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
    return getValeurs();
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