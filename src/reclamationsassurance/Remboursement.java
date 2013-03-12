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

    /**
     * @return the catSoin
     */
    public EnumCategorieSoin getCatSoin() {
        return catSoin;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the montant
     */
    public Double getMontant() {
        return montant;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}