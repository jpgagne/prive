package inf2015.assurance;


import java.util.EnumMap;
import java.util.Map;

public class CategoriesBeneficiaire 
{
private Map<EnumCodetypeBeneficiaire, Beneficiaire> mapBeneficiaires;

private static CategoriesBeneficiaire instance = null;

private CategoriesBeneficiaire() 
    {
    this.chargerMapBeneficiaires();
    }

public static CategoriesBeneficiaire getInstance()
    {
    if(instance == null)
        {
        instance = new CategoriesBeneficiaire();
        }
    return instance;
    }



private void chargerMapBeneficiaires()
    {
    Beneficiaire beneficiaire;
    this.mapBeneficiaires = new EnumMap<EnumCodetypeBeneficiaire, Beneficiaire>(EnumCodetypeBeneficiaire.class);
    beneficiaire = new Beneficiaire("a", EnumCodetypeBeneficiaire.ADHERENT, "Adherent", 100 );
    this.mapBeneficiaires.put(EnumCodetypeBeneficiaire.ADHERENT, beneficiaire);
    beneficiaire = new Beneficiaire("c", EnumCodetypeBeneficiaire.CONJOINT, "Conjoint", 100 );
    this.mapBeneficiaires.put(EnumCodetypeBeneficiaire.CONJOINT, beneficiaire);
    beneficiaire = new Beneficiaire("e*", EnumCodetypeBeneficiaire.ENFANT, "Enfant", 100 );
    this.mapBeneficiaires.put(EnumCodetypeBeneficiaire.ENFANT, beneficiaire);
    beneficiaire = new Beneficiaire("h*", EnumCodetypeBeneficiaire.PERSONNE_A_CHARGE, "Personne à charge", 50 );
    this.mapBeneficiaires.put(EnumCodetypeBeneficiaire.PERSONNE_A_CHARGE, beneficiaire);

    }


public Beneficiaire trouverBeneficiaire(EnumCodetypeBeneficiaire codetypeBeneficiaire)
    {
    return this.mapBeneficiaires.get(codetypeBeneficiaire);
    }


public final EnumCodetypeBeneficiaire trouverType(String str) throws ExceptionDonneeInvalide
{

if (str.matches("A"))
    {
    return EnumCodetypeBeneficiaire.ADHERENT;
    }

if (str.matches("C"))
    {
    return EnumCodetypeBeneficiaire.CONJOINT;
    }

if (str.matches("E\\d"))
    {
    return EnumCodetypeBeneficiaire.ENFANT;
    }

if (str.matches("H\\d"))
    {
    return EnumCodetypeBeneficiaire.PERSONNE_A_CHARGE;
    }

throw new ExceptionDonneeInvalide (EnumCodeErreur.TYPEBENEFICIAIRE_INVALIDE, str);
}


public boolean validerCodeBeneficiaire(String codeLu)
    {
    try {
        trouverType(codeLu);
        return  true;
        } 
    catch (ExceptionDonneeInvalide excDI)
        {
        return false;
        }
    }




}
