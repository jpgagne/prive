

import java.util.HashMap;
import java.util.Map;

public class ListeContrats
{


private Map<Character, Contrat> mapContrats;


private static ListeContrats instance = null;




private ListeContrats() 
    {
    chargerContrats();
    }




protected static ListeContrats getInstance()
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


protected void ajouterContrat(Contrat contrat)
    {
    this.mapContrats.put(contrat.getTypeContrat(), contrat);
    }


protected boolean  contratPresent(Character carTypeContrat)
{
    return this.mapContrats.containsKey(carTypeContrat);
}

protected Contrat trouverContrat (Character carTypeContrat) throws ExceptionContratInexistant
{
    
    System.out.println( "Je recherche le contrat '"+carTypeContrat+"' parmis mes "+this.mapContrats.size()+" Entrees");
    
if (this.mapContrats.containsKey(carTypeContrat))
    {
    return this.mapContrats.get(carTypeContrat);
    }
throw new ExceptionContratInexistant(carTypeContrat);
}
    
    
    
    
    
    
}
