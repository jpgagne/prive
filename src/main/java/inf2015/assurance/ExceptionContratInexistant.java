package inf2015.assurance;


public class ExceptionContratInexistant extends ExceptionSpecifique {

private Character carTypeContrat;
    
private ExceptionContratInexistant()
    {
    }   // CANTDO

private ExceptionContratInexistant(String msg)
    {
    }   //CANTDO

protected ExceptionContratInexistant(Character carTypeContrat) 
    {
    super(EnumCodeErreur.CODECONTRAT_ABSENT, carTypeContrat.toString());
    this.carTypeContrat = carTypeContrat;
    }
    
    
public Character getCarTypeContrat()
    {
    return this.carTypeContrat;
    }
    
    
}