
import java.util.Date;

public class Remboursement {
    
 
private EnumCategorieSoin catSoin;
private Date date;
private Double montant;

Remboursement(EnumCategorieSoin catSoin, Date date, Double montant)
    {
     this.montant = montant;
     this.catSoin = catSoin;
     this.date = date;
    }


public EnumCategorieSoin getCatSoin()
    {
    return catSoin;
    }

public Date getDate()
    {
    return date;
    }

public Double getMontant()
    {
    return montant;
    }

    
    
    
    
    
    
    
    
    
    
    
    
}