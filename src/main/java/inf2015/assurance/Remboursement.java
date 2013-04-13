package inf2015.assurance;


import java.util.Date;

public class Remboursement {
    
 
//private Soin soin;
private Integer noSoin;
private Date date;
private Argent montant;

//Remboursement(Soin soin, Date date, Argent montant)
//    {
//     this.montant = montant;
//     this.soin = soin;
//     this.date = date;
//    }

Remboursement(Integer noSoin, Date date, Argent montant)
    {
     this.montant = montant;
     this.noSoin = noSoin;
     this.date = date;
    }

protected Integer getSoin()
    {
    return this.noSoin;
    }

protected Date getDate()
    {
    return date;
    }

protected Argent getMontant()
    {
    return montant;
    }

    
    
    
    
    
    
    
    
    
    
    
    
}