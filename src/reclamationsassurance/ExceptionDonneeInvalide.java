/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamationsassurance;

/**
 *
 * @author JP
 */
public class ExceptionDonneeInvalide extends Exception {

    /**
     * Creates a new instance of
     * <code>ExceptionDonneeInvalide</code> without detail message.
     */
    public ExceptionDonneeInvalide() {
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
