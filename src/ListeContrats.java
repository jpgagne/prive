




import java.util.HashMap;
import java.util.Map;

public class ListeContrats
{

private static ListeContrats instance = null;



private Map<Character, Contrat> mapContrats;





private ListeContrats() 
{
chargerContrats();
}




public static ListeContrats getInstance()
    {
    if(instance == null)
        {
        instance = new ListeContrats();
        }
    return instance;
    }

private void chargerContrats()
    {
    mapContrats = new HashMap<>();
    }



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
