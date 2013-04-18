package inf2015.assurance;



import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CategoriesContrat
{


private Map<Character, Contrat> mapContrats;


private static CategoriesContrat instance = null;




private CategoriesContrat() 
    {
    mapContrats = new HashMap<Character, Contrat>();
    }




protected static CategoriesContrat getInstance()
    {
    if(instance == null)
        {
        instance = new CategoriesContrat();
        }
    return instance;
    }

public void chargerContrats() throws ExceptionDonneeInvalide, ExceptionIO, ExceptionUsage, ExceptionSoinNonCouvert
    {
    
    Entree_ParseurXML_Contrats entree_ParseurXML_Contrats = new Entree_ParseurXML_Contrats("contrats.xml");
    this.mapContrats = entree_ParseurXML_Contrats.parserFichierContrats();
System.out.println("NB de contrats chargés= "+mapContrats.size());
for (Character key : mapContrats.keySet()) {
    System.out.println("Contrat chargé = " + key);
}

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
