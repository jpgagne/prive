public class ExceptionSoinNonCouvert extends Exception
{
    

public ExceptionSoinNonCouvert(Intervalle noSoinIntervalle)
    {
    super(noSoinIntervalle.toString());
    }

public ExceptionSoinNonCouvert(Integer intNoSoin) 
    {
    super(intNoSoin.toString());
    }

public ExceptionSoinNonCouvert(Soin soin)
    {
    super(soin.toString());
    }


}