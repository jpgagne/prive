
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CategoriesSoin 
{

private Set<Soin> setSoins;


private static CategoriesSoin instance = null;
private CategoriesSoin() 
{
chargerSoins();
}


public static CategoriesSoin getInstance()
    {
    if(instance == null)
        {
        instance = new CategoriesSoin();
        }
    return instance;
    }

protected Soin getSoinParInteger(Integer noSoinInteger) throws ExceptionSoinNonCouvert
    {
   // System.out.println("NB de soins charges: "+ setSoins.size());
    int i = 0;
    for (Iterator<Soin> it1 = setSoins.iterator(); it1.hasNext();)
        {
        
        i++;
     //  System.out.print(" i = " + i +"   ");
        
        Soin soin = it1.next();
     // System.out.print(" Cherche: "+noSoinInteger.toString()+" inc dans? "+soin);
        if (soin.getIntervalleNoSoin().inclus(noSoinInteger))
            {
        //    System.out.println("  TROUVE");
            return soin;
            }
        else
            {
         //   System.out.println( "  NOPE");
            }
         }
    System.out.println("ABSENT");
    throw new ExceptionSoinNonCouvert(noSoinInteger);
    }




private void chargerSoins()
{
    setSoins= new HashSet<>();
    
    Soin nouveauSoin = new Soin(0, "Massothérapie"); 

   setSoins.add( nouveauSoin);
    nouveauSoin = new Soin(100, "Ostéopathie"); 

   setSoins.add( nouveauSoin);
   
   nouveauSoin = new Soin(150, "Kinésithérapie");
   setSoins.add(nouveauSoin);

   nouveauSoin = new Soin(175, "Médecin généraliste privé");
   setSoins.add(nouveauSoin);
   
    nouveauSoin = new Soin(200, "Psychologie individuelle"); 

   setSoins.add( nouveauSoin);

    nouveauSoin = new Soin(new Intervalle(300, 399), "Soins dentaires"); 

   setSoins.add( nouveauSoin);

    nouveauSoin = new Soin(400, "Naturopathie, acuponcture"); 

   setSoins.add( nouveauSoin);

    nouveauSoin = new Soin(500, "Chiropratie"); 
  
   setSoins.add( nouveauSoin);

    nouveauSoin = new Soin(600, "Physiothérapie"); 

   setSoins.add( nouveauSoin);

    nouveauSoin = new Soin(700, "Orthophonie, ergothérapie"); 

   setSoins.add( nouveauSoin);
    
    System.out.println("taille du ThreeSet: " +setSoins.size());
}





private void ajouterSoin(Intervalle intervalleNoSoin, Soin nouveauSoin)
{
    this.setSoins.add(nouveauSoin);
}




protected boolean soinExisteInteger (Integer integerNumeroSoin)
    {
    try 
        {
        Soin soin = this.getSoinParInteger(integerNumeroSoin);  
        return true;

        } catch (ExceptionSoinNonCouvert ex)
        {
            return false;
        }
    }


}
