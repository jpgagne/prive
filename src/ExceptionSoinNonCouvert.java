public class ExceptionSoinNonCouvert extends Exception
{
    
EnumCategorieSoin categorieSoin;

public ExceptionSoinNonCouvert(EnumCategorieSoin categorieSoin)
    {
    super(categorieSoin.toString());
    this.categorieSoin = categorieSoin;
    }

protected EnumCategorieSoin getCatSoin()
    {
    return this.categorieSoin;
    }


}