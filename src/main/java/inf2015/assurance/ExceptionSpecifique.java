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
    System.out.println("*****************************************");
    System.out.println("EXCEPTION: "+this.getClass().getName());
    System.out.println("CODE: "+ this.codeErreur);
    System.out.println("MESSAGE: "+super.getMessage());

    Throwable t = new Throwable();  
    StackTraceElement[] ste = t.getStackTrace();  
    String classeAppelante = ste[3].getClassName() + "." + ste[3].getMethodName() + ":" + ste[3].getLineNumber(); 
    System.out.println("CLASSE: "+classeAppelante);
    }

public EnumCodeErreur getCodeErreur()
    {
    return this.codeErreur;
    }
    
    
}
