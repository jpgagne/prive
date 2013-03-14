
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

public static CategoriesSoin getSessionUnique(){
    if (instance == null){
        instance = new CategoriesSoin();
        
    }
    return instance;
}




private void chargerSoins()
{
    mapSoins = new HashMap();
    
    Soin nouveauSoin = new Soin(0, "Massothérapie"); 
    this.mapSoins = new HashMap<>();
    mapSoins.put(nouveauSoin.getIntervalleNoSoin(), nouveauSoin);
            
    nouveauSoin = new Soin(100, "Ostéopathie"); 
    this.mapSoins = new HashMap<>();
    mapSoins.put(nouveauSoin.getIntervalleNoSoin(), nouveauSoin);
    
    nouveauSoin = new Soin(200, "Psychologie individuelle"); 
    this.mapSoins = new HashMap<>();
    mapSoins.put(nouveauSoin.getIntervalleNoSoin(), nouveauSoin);
    
    nouveauSoin = new Soin(new Intervalle(300, 399), "Soins dentaires"); 
    this.mapSoins = new HashMap<>();
    mapSoins.put(nouveauSoin.getIntervalleNoSoin(), nouveauSoin);
    
    nouveauSoin = new Soin(400, "Naturopathie, acuponcture"); 
    this.mapSoins = new HashMap<>();
    mapSoins.put(nouveauSoin.getIntervalleNoSoin(), nouveauSoin);
    
    nouveauSoin = new Soin(500, "Chiropratie"); 
    this.mapSoins = new HashMap<>();
    mapSoins.put(nouveauSoin.getIntervalleNoSoin(), nouveauSoin);
    
    nouveauSoin = new Soin(600, "Physiothérapie"); 
    this.mapSoins = new HashMap<>();
    mapSoins.put(nouveauSoin.getIntervalleNoSoin(), nouveauSoin);
    
    nouveauSoin = new Soin(700, "Orthophonie, ergothérapie"); 
    this.mapSoins = new HashMap<>();
    mapSoins.put(nouveauSoin.getIntervalleNoSoin(), nouveauSoin);
    
    System.out.print("taille du hashMap: "+mapSoins.size() );
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
