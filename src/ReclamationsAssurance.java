
import java.io.File;

public class ReclamationsAssurance
{

public static void main(String args[])
    {
    int intNbArgs = args.length;

    System.out.println("Start run");
    try {
        if (intNbArgs != 2) 
            {
            throw new ExceptionIO("NB arguments = " + intNbArgs + " / doit être =2");
            }
        
        
        
        Traitement traitement;
        traitement =  Traitement.getInstance();
        //traitement.execution(args[0], args[1]);
        }

    catch (ExceptionIO excIO)
        {
        System.out.println("ERREUR - L'exécution se termine avec échec critique");
        if (excIO.getMessage().length() > 0)
            {
            System.out.println(excIO.getMessage() + "\n");
            }
        }
    }

}