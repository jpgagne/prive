package inf2015.assurance;


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
setSoins= new HashSet<Soin>();

Soin nouveauSoin = new Soin(0, "Massothérapie"); 

ajouterSoin ( nouveauSoin);
nouveauSoin = new Soin(100, "Ostéopathie"); 

ajouterSoin ( nouveauSoin);

nouveauSoin = new Soin(150, "Kinésithérapie");
ajouterSoin (nouveauSoin);

nouveauSoin = new Soin(175, "Médecin généraliste privé");
ajouterSoin (nouveauSoin);

nouveauSoin = new Soin(200, "Psychologie individuelle"); 

ajouterSoin ( nouveauSoin);

nouveauSoin = new Soin(new Intervalle(300, 399), "Soins dentaires"); 

ajouterSoin ( nouveauSoin);

nouveauSoin = new Soin(400, "Naturopathie, acuponcture"); 

ajouterSoin ( nouveauSoin);

nouveauSoin = new Soin(500, "Chiropratie"); 

ajouterSoin ( nouveauSoin);

nouveauSoin = new Soin(600, "Physiothérapie"); 

ajouterSoin ( nouveauSoin);

nouveauSoin = new Soin(700, "Orthophonie, ergothérapie"); 

ajouterSoin ( nouveauSoin);


}



private void ajouterSoin (Soin nouveauSoin)
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
