package inf2015.assurance;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author JP
 */


public class MapBeneficiaires
{
private Map<String, Beneficiaire> mapBeneficiaires;


MapBeneficiaires()
    {
    mapBeneficiaires = new HashMap<String, Beneficiaire>();
    }

public boolean  ajouterBeneficiaire(Beneficiaire nouveauBeneficiaire)
    {
    if (this.mapBeneficiaires.containsKey(nouveauBeneficiaire.getCode()))
            {
            return false;
            }
    this.mapBeneficiaires.put(nouveauBeneficiaire.getCode(), nouveauBeneficiaire);
    return true;
    }

public boolean BeneficiairePresent(String code)
{
    return this.mapBeneficiaires.containsKey(code);
}
public Beneficiaire trouverBeneficiaire(String code)
{
    return this.mapBeneficiaires.get(code);
}


}   // end of class MapBeneficiaires