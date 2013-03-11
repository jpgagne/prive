package reclamationsassurance;

public class Soin 
{
private Integer noSoin;    
private String nom;
private Integer taux;
private Integer max;


Soin(Integer noSoin, String nom, Integer taux, Integer max)
{
this.noSoin = noSoin;
this.nom = nom;
this.taux = taux;
this.max = max;
}
    

Soin(Integer noSoin, String nom, Integer taux)
{
this.noSoin = noSoin;
this.nom = nom;
this.taux = taux;
this.max = -1;
}

//<editor-fold defaultstate="collapsed" desc="Getters">
public Integer getNoSoin()
{
    return noSoin;
}

public String getNom()
{
    return nom;
}

public Integer getTaux() {
    return taux;
}

public Integer getMax() {
    return max;
}
//</editor-fold>





}
