package inf2015.assurance;


import java.util.Date;

public class Remboursement
{
private Integer noSoin;
private Date date;
private Argent montant;


Remboursement(Integer noSoin, Date date, Argent montant)
    {
    this.montant = montant;
    this.noSoin = noSoin;
    this.date = date;
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