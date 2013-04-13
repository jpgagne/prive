package inf2015.assurance;

public abstract class ExceptionSpecifique extends Exception
{    
private EnumCodeErreur codeErreur;


public ExceptionSpecifique() 
    {
    super();
    this.codeErreur = EnumCodeErreur.ERREUR_INCONNUE;
    }

public ExceptionSpecifique(EnumCodeErreur codeErreur)
    {
    super();
    this.codeErreur = codeErreur;
    }

public ExceptionSpecifique(String message)
    {
    super(message);
    this.codeErreur = EnumCodeErreur.ERREUR_INCONNUE;
    }

public ExceptionSpecifique(EnumCodeErreur codeErreur, String message)
    {
    super(message);
    this.codeErreur = codeErreur;
    }


public EnumCodeErreur getCodeErreur()
    {
    return this.codeErreur;
    }
    
    
}
