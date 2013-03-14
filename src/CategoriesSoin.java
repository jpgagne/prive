
import java.util.HashMap;
import java.util.Map;

public class CategoriesSoin 
{


private static CategoriesSoin instance = null;
private CategoriesSoin() 
{
chargerSoins();
}





private void chargerSoins()
{
    //TODO
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




protected soinExiste (Integer integerNumeroSoin)
{
    
}
        
        
        protected Soin trouverSoin( Integer integerNumeroSoin)
{
    
    
    Soin soinTrouve;
    
    
    
    return soinTrouve;
    //TODO

}

}
