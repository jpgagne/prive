
package inf2015.assurance;


public class ExceptionIntervalle extends ExceptionSpecifique {

  private  Intervalle i1 = null;
  private  Intervalle i2 = null;
    
    
    
private ExceptionIntervalle() 
{
}

public ExceptionIntervalle(Intervalle intervalle)
    {
    super(EnumCodeErreur.INTERVALLES_COLLISION, intervalle.toString());
    this.i1 = intervalle;
    }
   
public ExceptionIntervalle(Intervalle i1, Intervalle i2)
    {
    super(EnumCodeErreur.INTERVALLES_COLLISION, i1+" CLASH "+i2);
    this.i1 = i1;
    this.i2 = i2;
    }

public Intervalle getIntervalle1()
    {
    return this.i1;
    }

public Intervalle getIntervalle2()
    {
    return this.i2;
    }

}
