
package inf2015.assurance;


public class EntreeReclamationJSON
{


public String soin;
public String code;
public String date;
public String montant;

    

    @Override
public String toString()
{
    return soin+"-"+code+"-"+date+"-"+montant;
    
}
    
}
