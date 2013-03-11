package reclamationsassurance;

public class ExceptionDonneeInvalide extends Exception {

  
    public ExceptionDonneeInvalide(EnumErreurLecture typeErreur)
    {
        super(typeErreur.toString());
        System.out.println("ExceptionDonneInvalide_ENUM: "+typeErreur.toString());
    }

      public ExceptionDonneeInvalide(EnumErreurLecture typeErreur, String details)
    {
        super(typeErreur.toString());
        System.out.println("ExceptionDonneInvalide_ENUM+DETAILS: "+typeErreur.toString());
    }
    
    public ExceptionDonneeInvalide(String msg) 
    {
        super(msg);
        System.out.println("ExceptionDonneInvalide_str: "+msg);
    }
}
