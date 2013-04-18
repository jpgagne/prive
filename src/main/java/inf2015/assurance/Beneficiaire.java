package inf2015.assurance;

import java.util.HashMap;
import java.util.Map;

public class Beneficiaire
{
private final String code;
private final Integer pourcentage;
private final String litteral;
private final EnumCodetypeBeneficiaire typeBeneficiaire;
private Map <String, Argent> totalMensuelParSoin;

public Beneficiaire(String code,EnumCodetypeBeneficiaire typeBeneficiaire, String litteral, Integer pourcentage ) 
    {
    this.code = code;
    this.typeBeneficiaire = typeBeneficiaire;
    this.pourcentage = pourcentage;
    this.litteral = litteral;
    this.totalMensuelParSoin = new HashMap<String, Argent>();
    }




public Argent ajouterATotalMensuel(Soin soin, Argent ajout, Argent maxMensuel)
{
    System.out.println("ajouterATotalMensuel()");
            
    System.out.println(this.toString()+soin+"  AJOUT"+ajout+" MAXIMUM: "+maxMensuel);
    Argent depense = new Argent(0);
    depense.additionner(ajout);

    Argent rellementAjoute = new Argent(0);
    rellementAjoute.additionner(ajout);
    if (totalMensuelParSoin.containsKey(soin.getLitteral()))
        {
        depense.additionner(totalMensuelParSoin.get(soin.getLitteral()));
        }
    
    if ((maxMensuel.getMontantCentimes() > -1)& (maxMensuel.getMontantCentimes() < depense.getMontantCentimes()))
        {
        rellementAjoute.additionner(maxMensuel);
        rellementAjoute.soustraire(depense); 
        totalMensuelParSoin.put(soin.getLitteral(), maxMensuel);
        }
    else
        {
        totalMensuelParSoin.put(soin.getLitteral(), depense);
        }

  return rellementAjoute;
}







    @Override
public String toString()
{
    return "***BENEFICIAIRE*, CODE: "+this.code
            +", TYPE: "+this.typeBeneficiaire
            +", POURCENTAGE: "+this.pourcentage
            +", LITTERAL: "+this.litteral
            +" ***";
}

//<editor-fold defaultstate="collapsed" desc="getters">
public String getCode()
    {
    return code;
    }

public String getLitteral()
    {
    return this.litteral;
    }

public Integer getPourcentage()
    {
    return pourcentage;
    }

public EnumCodetypeBeneficiaire getTypeBeneficiaire()
{
    return this.typeBeneficiaire;
}
//</editor-fold>






}   // end of class Beneficiaire