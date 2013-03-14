
import java.util.HashMap;
import java.util.Map;

public class CategoriesSoin 
{


    private Map<Intervalle, Soin> mapSoins;
    
    
private static CategoriesSoin instance = null;
private CategoriesSoin() 
{
chargerSoins();
}





private void chargerSoins()
{
    
    this.mapSoins = new HashMap<>();
}

public static CategoriesSoin getInstance()
    {
    if(instance == null)
        {
        instance = new CategoriesSoin();
        }
    return instance;
    }



private Map<Intervalle, Soin> soins = new HashMap();

private void ajouterSoin(Intervalle intervalleNoSoin, Soin nouveauSoin)
{
    this.soins.put(intervalleNoSoin, nouveauSoin);
}




protected boolean soinExiste (Integer integerNumeroSoin)
    {
    return true; //TODO
    }


protected Soin trouverSoinIntervalle( Intervalle intervalleNumeroSoin) throws ExceptionSoinNonCouvert
    {


    Soin soinTrouve;
    if (soinExisteIntervalle(intervalleNumeroSoin))

    {
    soinTrouve = new Soin(intervalleNumeroSoin, null);
    return soinTrouve;

    }throw new ExceptionSoinNonCouvert(intervalleNumeroSoin);
        //TODO

    }



protected boolean soinExisteIntervalle (Intervalle integerNumeroSoin)
    {
    return true; //TODO
    }


protected Soin trouverSoinInteger( Integer integerNumeroSoin) throws ExceptionSoinNonCouvert
    {


    Soin soinTrouve;
    if (soinExiste(integerNumeroSoin))

    {
    soinTrouve = new Soin(integerNumeroSoin, null);
    return soinTrouve;

    }throw new ExceptionSoinNonCouvert(integerNumeroSoin);
        //TODO

    }

}
