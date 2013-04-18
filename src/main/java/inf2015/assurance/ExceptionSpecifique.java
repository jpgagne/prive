package inf2015.assurance;

public abstract class ExceptionSpecifique extends Exception
{    
private EnumCodeErreur codeErreur;


public ExceptionSpecifique() 
    {
    super();
    this.codeErreur = EnumCodeErreur.ERREUR_INCONNUE;
    afficherException();
    }

public ExceptionSpecifique(EnumCodeErreur codeErreur)
    {
    super();
    this.codeErreur = codeErreur;
    afficherException();
    }

public ExceptionSpecifique(String message)
    {
    super(message);
    this.codeErreur = EnumCodeErreur.ERREUR_INCONNUE;
    afficherException();
    }

public ExceptionSpecifique(EnumCodeErreur codeErreur, String message)
    {
    super(message);
    this.codeErreur = codeErreur;
    afficherException();
    }


public void afficherException()
{
    System.out.println("EXCEPTION: "+this.getClass().getName()
            +"  CODE: "+ this.codeErreur
            +"  MESSAGE: "+super.getMessage());
}

public EnumCodeErreur getCodeErreur()
    {
    return this.codeErreur;
    }
    
    
}
