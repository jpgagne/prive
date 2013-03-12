package reclamationsassurance;


import java.util.Date;

public class Reclamation {
    
private EnumCategorieSoin catSoin;
private Date dateSoin;
private Double montantReclame;
private char typeContrat;




Reclamation(EnumCategorieSoin catSoin, Date dateSoin, Double montantReclame, char typeContrat) 
{  
this.catSoin = catSoin;
this.dateSoin = dateSoin;
this.montantReclame = montantReclame;
this.typeContrat = typeContrat;
}



    /**
     * @return the dateSoin
     */
    public Date getDateSoin() {
        return dateSoin;
    }

    /**
     * @return the montantReclame
     */
    public Double getMontantReclame() {
        return montantReclame;
    }

    /**
     * @return the typeContrat
     */
    public char getTypeContrat() {
        return typeContrat;
    }

    /**
     * @return the catSoin
     */
    public EnumCategorieSoin getCatSoin() {
        return catSoin;
    }

    
    





}