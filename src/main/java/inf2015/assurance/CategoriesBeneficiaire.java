package inf2015.assurance;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CategoriesBeneficiaire 
{

private Set<String> setPatternsCategoriesBeneficiaire;
private String[] patterns = { "A", "C", "E\\d", "H\\d"};


private static CategoriesBeneficiaire instance = null;
private CategoriesBeneficiaire() 
{
chargerPatternsCategorieBeneficiaire();
}


public static CategoriesBeneficiaire getInstance()
    {
    if(instance == null)
        {
        instance = new CategoriesBeneficiaire();
        }
    return instance;
    }



private void chargerPatternsCategorieBeneficiaire()
    {
    this.setPatternsCategoriesBeneficiaire = new HashSet<String>(Arrays.asList(patterns));
    }


public boolean validerCodeBeneficiaire( String codeLu)
    {
    for (Iterator<String> it = setPatternsCategoriesBeneficiaire.iterator(); it.hasNext();)
        {
        String pattern = it.next();
        if (codeLu.matches(pattern))
            {
            return true;
            }
        }
    return false;   
    }




}
