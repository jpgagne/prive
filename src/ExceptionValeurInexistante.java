


public class ExceptionValeurInexistante extends Exception
{

private Intervalle valeurInexistante;

public ExceptionValeurInexistante(Integer valeurInexistante)
    {
    super("Valeur inexistante: "+valeurInexistante.toString());
    this.valeurInexistante = new Intervalle(valeurInexistante);
    }


public ExceptionValeurInexistante(Intervalle valeurInexistante)
    {
    super("Valeur inexistante: "+valeurInexistante.toString());
    this.valeurInexistante = valeurInexistante;
    }


protected Intervalle getValeurInexistante()
{
    return this.valeurInexistante;
}

}
