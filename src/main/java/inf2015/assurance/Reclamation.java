package inf2015.assurance;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Reclamation {



private Integer noSoin;
private Date dateSoin;
private Argent montantReclame;
private EnumCodetypeBeneficiaire codeTypeBeneficiaire;



Reclamation (EnregistrementJSON_Reclamation entreeReclamationJSON) throws ExceptionDonneeInvalide
    {
    validerReclamation(entreeReclamationJSON);
    }


//<editor-fold defaultstate="collapsed" desc="validations">


private void validerReclamation(EnregistrementJSON_Reclamation enregistrementJSON_Reclamation) throws ExceptionDonneeInvalide
    {
    validerNoSoin(enregistrementJSON_Reclamation);
    validerDateSoin(enregistrementJSON_Reclamation);
    validerMontantReclame(enregistrementJSON_Reclamation);
    validerCodeTypeBeneficiaire(enregistrementJSON_Reclamation);
    }



private void validerNoSoin(EnregistrementJSON_Reclamation ejsonr) throws ExceptionDonneeInvalide
    {
    CategoriesSoin categoriesSoin = CategoriesSoin.getInstance();
    try
        {
        this.noSoin = ParseurNombres.parseChainePourInteger(ejsonr.soin);
        }
    catch (ExceptionParseur excP)
        {
        throw new ExceptionDonneeInvalide(EnumCodeErreur.NOSOIN_INVALIDE, ejsonr.soin);
        }
    
    if (! categoriesSoin.soinExiste(noSoin))
        {
        throw new ExceptionDonneeInvalide(EnumCodeErreur.NOSOIN_INCONNU, this.noSoin.toString());
        }
    }


private void validerDateSoin(EnregistrementJSON_Reclamation ejsonr) throws ExceptionDonneeInvalide
    {
    try
        {
        this.dateSoin = ParseurNombres.parseChainePourDateJour(ejsonr.date);
        }
    catch (ExceptionParseur excP)
        {
        throw new ExceptionDonneeInvalide(EnumCodeErreur.DATE_FORMATINVALIDE, ejsonr.date);
        }
    }


private void validerMontantReclame(EnregistrementJSON_Reclamation ejsonr) throws ExceptionDonneeInvalide
    {
    try 
        {
        this.montantReclame = new Argent(ejsonr.montant);
        } 
    catch (ExceptionArgent excA)
        {
        throw new ExceptionDonneeInvalide(excA.getCodeErreur(), ejsonr.montant);
        }
         
    catch (ExceptionParseur excP)
        {
        throw new ExceptionDonneeInvalide(EnumCodeErreur.MONTANT_FORMAT_INVALIDE, ejsonr.montant);
        }
    
    if (!this.montantReclame.estPositif())
        {
        throw new ExceptionDonneeInvalide(EnumCodeErreur.MONTANT_NEGATIF, ejsonr.montant);
        }
    }


private void validerCodeTypeBeneficiaire(EnregistrementJSON_Reclamation ejsonr) throws ExceptionDonneeInvalide
    {
    CategoriesBeneficiaire categoriesBeneficiaire = CategoriesBeneficiaire.getInstance();
    this.codeTypeBeneficiaire = categoriesBeneficiaire.trouverType(ejsonr.code);
    }
//</editor-fold>


//<editor-fold defaultstate="collapsed" desc="utilitaires">

@Override
public String toString()
{
    SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd", Locale.FRENCH);
    return  "*RECLAMATION* "
            
            +" Soin: "+this.noSoin
            +" / Code: "+this.codeTypeBeneficiaire
            +" / Date: "+dateFormat.format(dateSoin)
            +" / Montant "+montantReclame;
}
//</editor-fold>


//<editor-fold defaultstate="collapsed" desc="getters">
public Date getDateSoin()
{
    return dateSoin;
}

public Argent getMontantReclame()
{
    return montantReclame;
}

public EnumCodetypeBeneficiaire getCodeTypeBeneficiaire()
{
    return this.codeTypeBeneficiaire;
}


public Integer getNoSoin()
{
    return this.noSoin;
}
//</editor-fold>
    





}