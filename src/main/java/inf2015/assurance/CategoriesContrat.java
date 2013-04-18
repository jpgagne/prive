package inf2015.assurance;



import java.util.HashMap;
import java.util.Map;

public class CategoriesContrat
{


private Map<Character, Contrat> mapContrats;


private static CategoriesContrat instance = null;




private CategoriesContrat() 
    {
    chargerContrats();
    this.ajouterContrat(new Contrat('A'));
    this.ajouterContrat(new Contrat('B'));
    this.ajouterContrat(new Contrat('C'));
    this.ajouterContrat(new Contrat('D'));
    this.ajouterContrat(new Contrat('E'));
    }




protected static CategoriesContrat getInstance()
    {
    if(instance == null)
        {
        instance = new CategoriesContrat();
        }
    return instance;
    }

private void chargerContrats()
    {
    mapContrats = new HashMap<Character, Contrat>();
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
