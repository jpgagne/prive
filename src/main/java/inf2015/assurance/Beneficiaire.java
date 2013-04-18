package inf2015.assurance;

public class Beneficiaire
{
private final String code;
private final Integer pourcentage;
private final String litteral;
private final EnumCodetypeBeneficiaire typeBeneficiaire;

public Beneficiaire(String code,EnumCodetypeBeneficiaire typeBeneficiaire, String litteral, Integer pourcentage ) 
    {
    this.code = code;
    this.typeBeneficiaire = typeBeneficiaire;
    this.pourcentage = pourcentage;
    this.litteral = litteral;
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