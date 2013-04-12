package inf2015.assurance;


public class ExceptionContratInexistant extends Exception {

    private Character carTypeContrat;
    
    
private ExceptionContratInexistant()
    {
    }   // CANTDO

private ExceptionContratInexistant(String msg)
    {
    }   //CANTDO

protected ExceptionContratInexistant(Character carTypeContrat, String msg) {
        super(carTypeContrat.toString());
        this.carTypeContrat = carTypeContrat;
        System.out.println("EXCEPTION CONTRAT INEXISTANT : "+"|"+carTypeContrat+"|");
    }
    
protected ExceptionContratInexistant(Character carTypeContrat) {
    super();
//        super(carTypeContrat.toString());
        this.carTypeContrat = carTypeContrat;
    }
    
protected Character getCarTypeContrat()
{
    return this.carTypeContrat;
}
    
    
}