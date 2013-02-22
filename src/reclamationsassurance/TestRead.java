package reclamationsassurance;

public class TestRead {

    public static void main(String args[]) {
        int intNbArgs = args.length;
        
        System.out.println("Start run");
        System.out.println("Nb d'Arguments: " + args.length);

        try {
            if (intNbArgs == 2) {
                XmlAssurancesParser parser = new XmlAssurancesParser((String) args[0], (String) args[1]);

            } else {
                throw new ExceptionFinProgramme("NB arguments = " + intNbArgs + " / doit être =2");
            }
            System.out.println("Exécution terminée avec succès");

        } catch (ExceptionFinProgramme excFP) {
            System.out.println("ERREUR - L'exécution se termine avec échec");
            if (excFP.getMessage().length() > 0) {
                System.out.println(excFP.getMessage() + "\n");
            }
        }
    }
}