
import java.util.Date;

public class Remboursement {
    
 
private Soin soin;
private Date date;
private Double montant;

Remboursement(Soin soin, Date date, Double montant)
    {
     this.montant = montant;
     this.soin = soin;
     this.date = date;
    }


protected Soin getSoin()
    {
    return this.soin;
    }

protected Date getDate()
    {
    return date;
    }

protected Double getMontant()
    {
    return montant;
    }

    
    
    
    
    
    
    
    
    
    
    
    
}