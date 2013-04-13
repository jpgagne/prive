package inf2015.assurance;

public class ExceptionArgent extends ExceptionSpecifique {

public ExceptionArgent()
    {
    super(EnumCodeErreur.ERREUR_ARGENT);
    }

public ExceptionArgent(String msg)
    {
    super(EnumCodeErreur.ERREUR_ARGENT,msg);
    }

public ExceptionArgent(EnumCodeErreur codeErreur)
    {
    super(codeErreur);
    }

public ExceptionArgent(EnumCodeErreur codeErreur, String message)
    {
    super(codeErreur, message);
    }


}
