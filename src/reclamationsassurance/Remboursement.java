package reclamationsassurance;

import java.util.Date;


/**
 *
 * @author JP
 */
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
    
    
    
    
    
    
    
    
    
    
    
    
    
}