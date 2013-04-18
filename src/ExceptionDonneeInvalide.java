

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
    System.out.println("ExceptionDonneInvalide_ENUM+DETAILS: "+typeErreur.toString()+" : "+details);
    genererFichierErreur();
}

public ExceptionDonneeInvalide(String msg) 
    {
    super(msg);
    this.typeErreur = EnumErreurLecture.ERREUR_INCONNUE;
    System.out.println("ExceptionDonneInvalide_str: "+msg);
    genererFichierErreur();
    }
    
    
    private void genererFichierErreur()      
            
    {
        Traitement traitement = Traitement.getInstance();
        try {        
            SortieJSON sortieJSON = new SortieJSON();
            sortieJSON.redigerDocumentSortie(this);
            }
        catch (ExceptionIO ex)
            {
                
            }
    }
   
    
    
    protected String getLiteral() {
        String message;
        switch (this.typeErreur) {

            case CONTRAT_ABSENT:

                message = "Le contrat recherché est introuvable.";

                break;

            case CONTRAT_INVALIDE:

                message = "Le contrat suivant est invalide. Je vous rappelle qu’un contrat "
                        + "valide est dans les intervalles de A à E respectivement.";
                break;

            case NOCLIENT_ABSENT:

                message = "Le numéro de client est introuvable.";
                break;

            case NOCLIENT_INVALIDE:

                message = "Le numéro de client suivant est invalide. Je vous rappelle que  le numéro de client doit obligatoirement"
                        + " être composé de 6 chiffres, aucun autre caractère que des chiffres ne doit se retrouver dans le numéro de client";
                break;

            case NOSOIN_ABSENT:

                message = "Le soin recherché est introuvable.";
                break;

            case NOSOIN_INVALIDE:

                message = "Le numéro de soin n’est pas valide";
                break;

            case DATE_ABSENT:

                message = "La date recherché est introuvable";
                break;

            case DATE_FORMAT_INVALIDE:

                message = ": La date entrée est invalide. Je vous rappelle que le format "
                        + "de date est le suivant : (AAAA-MM-JJ)";
                break;

            case DATE_MAUVAIS_MOIS:

                message = "Le mois suivant est invalide. ";
                break;

            case MONTANT_ABSENT:

                message = "Le montant suivant est introuvable";
                break;

            case MONTANT_SIGNE_DOLLAR:
                message = "Je vous rappelle que Le signe de dollar ($) doit toujours être présent à "
                        + "la fin d'un montant.";
                break;

            case MONTANT_FORMAT_INVALIDE:
                message = "Le montant suivant est invalide.";
                break;
            case MONTANT_NEGATIF:
                message = "Le montant suivant est négatif.";
                break;


            case MAXIMUM_INVALIDE:
                message = "Le maximum entré est invalide";
                break;

            case POURCENTAGE_INVALIDE:
                message = "Le taux (%) d'assurance couvert n'es pas valide";
                break;

            case POURCENTAGE_ABSENT:
                message = "Le pourcentage est introuvable";
                break;

            case CODESOIN_INEXISTANT:
                message = "Le soin n'est pas couvert par le contrat";
                break;

            case DOSSIER_ABSENT:
                message = "La balise recherché est introuvable";
                 break;
                
            case DOSSIER_INVALIDE:
                message = "La balise recherché est invalide";
                break;
                
                
            case MOIS_ABSENT:
                message = "le mois recherché est introuvable";
                break;
                    
         
            case MOIS_INVALIDE:
                message = "Le mois recherché est invalide ";
                break;
                
            default:
                message = "Une erreur inconnue";
                break;
        }     
        

        return message;
    }
}


