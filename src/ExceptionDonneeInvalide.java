
public class ExceptionDonneeInvalide extends Exception
{
EnumErreurLecture typeErreur;
  
public ExceptionDonneeInvalide(EnumErreurLecture typeErreur)
    {
    super(typeErreur.toString());
    this.typeErreur = typeErreur;
    System.out.println("ExceptionDonneInvalide_ENUM: "+typeErreur.toString());
    genererFichierErreur();
    }

public ExceptionDonneeInvalide(EnumErreurLecture typeErreur, String details)
    {
    super(typeErreur.toString());
    this.typeErreur = typeErreur;
    System.out.println("ExceptionDonneInvalide_ENUM+DETAILS: "+typeErreur.toString());
    genererFichierErreur();
}

public ExceptionDonneeInvalide(String msg) 
    {
    super(msg);
    System.out.println("ExceptionDonneInvalide_str: "+msg);
    genererFichierErreur();
    }
    
    
    private void genererFichierErreur()
    {
        System.out.println("*********** genererFichierErreur() ********");
    }
   
}
