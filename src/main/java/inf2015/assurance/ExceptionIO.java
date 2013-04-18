package inf2015.assurance;

import java.io.File;


public class ExceptionIO extends ExceptionSpecifique
{
    
private File fichierFautif;


public ExceptionIO(File fichierFautif, EnumCodeErreur codeErreur, String message) 
    {
    super(codeErreur, message);
    this.fichierFautif = fichierFautif;
    } 



public ExceptionIO(File fichierFautif) 
    {
    super(EnumCodeErreur.ERREUR_LECTURE_FICHIER);
    this.fichierFautif = fichierFautif;
    } 

public ExceptionIO(File fichierFautif, String message) 
    {
    super(EnumCodeErreur.ERREUR_LECTURE_FICHIER, message);
    this.fichierFautif = fichierFautif;
    } 
    
public ExceptionIO() 
    {
    super(EnumCodeErreur.ERREUR_LECTURE_FICHIER);
    }

public ExceptionIO(EnumCodeErreur codeErreur)
    {
    super(codeErreur);
    }

public ExceptionIO(String message)
    {
    super(EnumCodeErreur.ERREUR_LECTURE_FICHIER, message); 
    }

public ExceptionIO(EnumCodeErreur codeErreur, String message)
    {
    super(codeErreur, message);
    }





public File getFichierFautif()
    {
    return this.fichierFautif;
    }
}
