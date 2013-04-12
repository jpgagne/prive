package inf2015.assurance;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author JP
 */
public class ExceptionUsage extends Exception {

    /**
     * Creates a new instance of
     * <code>ExceptionUsage</code> without detail message.
     */
    public ExceptionUsage() {
    }

    /**
     * Constructs an instance of
     * <code>ExceptionUsage</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ExceptionUsage(String msg) {
        super(msg);
    }
}
