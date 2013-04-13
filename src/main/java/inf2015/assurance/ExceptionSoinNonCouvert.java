package inf2015.assurance;

public class ExceptionSoinNonCouvert extends ExceptionSpecifique
{
    

public ExceptionSoinNonCouvert(Intervalle noSoinIntervalle)
    {
    super(EnumCodeErreur.NOSOIN_NONCOUVERT, noSoinIntervalle.toString());
    }

public ExceptionSoinNonCouvert(Integer intNoSoin) 
    {
    super(EnumCodeErreur.NOSOIN_NONCOUVERT, intNoSoin.toString());
    }

public ExceptionSoinNonCouvert(Soin soin)
    {
    super(EnumCodeErreur.NOSOIN_NONCOUVERT, soin.toString());
    }


}