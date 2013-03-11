package reclamationsassurance;

public class ExceptionDonneeInvalide extends Exception {

    public ExceptionDonneeInvalide(String msg) {
        super(msg);
        System.out.println("Constructeur ExceptionDonneeInvalide");
    }
}