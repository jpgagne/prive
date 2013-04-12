package inf2015.assurance;


public class ExceptionParseur extends Exception {
private String chaineParsee;

public ExceptionParseur(String chaineParsee)
    {
    super(chaineParsee);
    this.chaineParsee = chaineParsee;
    }

public String getChaineParsee()
{
    return this.chaineParsee;
}








}
