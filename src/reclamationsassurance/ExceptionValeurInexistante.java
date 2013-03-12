
package reclamationsassurance;

public class ExceptionValeurInexistante extends Exception
{

private Integer valeurInexistante;

public ExceptionValeurInexistante(Integer valeurInexistante)
    {
    super("Valeur inexistante: "+valeurInexistante.toString());
    this.valeurInexistante = valeurInexistante;
    }

protected Integer getValeurInexistante()
{
    return this.valeurInexistante;
}

}
