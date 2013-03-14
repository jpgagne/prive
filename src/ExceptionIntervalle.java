
public class ExceptionIntervalle extends Exception {


private Intervalle nombreIntervalle;
private Integer nombreInteger;
private boolean estIntegerDoitEtreIntervalle;
private boolean estIntervalleDoitEtreInteger;
    
public ExceptionIntervalle(Intervalle nombreIntervalle)
{
super(nombreIntervalle.toString());
this.estIntervalleDoitEtreInteger = true;
this.estIntegerDoitEtreIntervalle = false;
this.nombreIntervalle = nombreIntervalle;
this.nombreInteger = nombreIntervalle.getBornePlancher();
}

public ExceptionIntervalle (Integer nombreInteger)
{
super(nombreInteger.toString());
this.estIntervalleDoitEtreInteger = false;
this.estIntegerDoitEtreIntervalle = true;
this.nombreIntervalle = new Intervalle(nombreInteger);
this.nombreInteger = nombreInteger;
}

    /**
     * @return the nombreIntervalle
     */
    public Intervalle getNombreIntervalle() {
        return nombreIntervalle;
    }

    /**
     * @return the nombreInteger
     */
    public Integer getNombreInteger() {
        return nombreInteger;
    }

    /**
     * @return the estIntegerDoitEtreIntervalle
     */
    public boolean isEstIntegerDoitEtreIntervalle() {
        return estIntegerDoitEtreIntervalle;
    }

    /**
     * @return the estIntervalleDoitEtreInteger
     */
    public boolean isEstIntervalleDoitEtreInteger() {
        return estIntervalleDoitEtreInteger;
    }






}
