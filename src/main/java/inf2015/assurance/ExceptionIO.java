package inf2015.assurance;


public class ExceptionIO extends Exception {


    public ExceptionIO(String msg) {
        super(msg);
        System.out.println(msg);
    }
}
