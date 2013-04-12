package inf2015.assurance;

public class Soin
{    
private Intervalle intervalleNoSoin;
private String litteral;
      

Soin(Integer integerNoSoin, String litteral)
    {
    this.litteral = litteral;
    this.intervalleNoSoin = new Intervalle(integerNoSoin);
    }

Soin(Intervalle intervalleNoSoin, String litteral)
    {
    this.intervalleNoSoin = intervalleNoSoin;
    this.litteral = litteral;
    }   
    
protected Intervalle getIntervalleNoSoin()
    {
    return this.intervalleNoSoin;
    }
    
    
protected String getLitteral()
    {
    return this.litteral;
    }

    @Override
public String toString()
{
    return (this.intervalleNoSoin+" - "+this.getLitteral());
}

}
