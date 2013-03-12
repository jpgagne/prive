package reclamationsassurance;

public class TestRead {

    public static void main(String args[]) {
        int intNbArgs = args.length;
        
        System.out.println("Start run");
        System.out.println("Nb d'Arguments: " + args.length);

        try {
            if (intNbArgs == 2) {
                ParseurXML_Imput parseur = new ParseurXML_Imput((String) args[0], (String) args[1]);

            } else {
                throw new ExceptionUsage("NB arguments = " + intNbArgs + " / doit être =2");
            }
            
            System.out.println("Exécution terminée avec succès");

        } catch (ExceptionUsage excFP) {
            System.out.println("ERREUR - L'exécution se termine avec échec");
            
            if (excFP.getMessage().length() > 0) {
                System.out.println(excFP.getMessage() + "\n");
            }
            
        }
        
    }
}