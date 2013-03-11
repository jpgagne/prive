package reclamationsassurance;

public class ExceptionDonneeInvalide extends Exception {

    /**
     * Creates a new instance of
     * <code>ExceptionDonneeInvalide</code> without detail message.
     */
    public ExceptionDonneeInvalide() {
        System.out.println("Constructeur ExceptionDonneeInvalide");
        
    }

    /**
     * Constructs an instance of
     * <code>ExceptionDonneeInvalide</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExceptionDonneeInvalide(String msg) {
        super(msg);
    }
}
