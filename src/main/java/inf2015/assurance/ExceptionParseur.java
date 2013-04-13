package inf2015.assurance;


public class ExceptionParseur extends ExceptionSpecifique

{
private String chaineParsee = null;
private Class typeAttendu = null;


//<editor-fold defaultstate="collapsed" desc="constructeurs">

public ExceptionParseur(String chaineParsee)
    {
    super(EnumCodeErreur.ERREUR_PARSEUR, chaineParsee);
    this.chaineParsee = chaineParsee;
    }

public ExceptionParseur(Class type)
    {
    super(EnumCodeErreur.ERREUR_PARSEUR, "Type Attendu: "+type.getName());
    this.typeAttendu = type;
    }

public ExceptionParseur(Class type, String chaineParsee)
    {
    super(EnumCodeErreur.ERREUR_PARSEUR, "Type Attendu: "+type.getName()+"; lu: "+chaineParsee);
    this.chaineParsee = chaineParsee;
    this.typeAttendu = type;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getters">

public String getChaineParsee()
    {
    if (this.chaineParsee == null)
        {
        return "VALEUR_NON_SPECIFIEE";
        }
    return this.chaineParsee;
    }


public String getTypeAttendu()
    {
    if (this.typeAttendu == null)
        {
        return "TYPE_NON_SPECIFIE";
        }
    return this.typeAttendu.getName();
    }
//</editor-fold>


}
