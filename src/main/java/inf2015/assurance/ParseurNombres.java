package inf2015.assurance;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseurNombres
{

final static String regexIntervalleEntier = ".\\d{1,3}..\\d{1,3}.";
final static String regexNombreEntier = "\\d{1,3}";
final static String formatDateSimple = "yyyy-MM";


private ParseurNombres()
{
    
}


public static Double parseChainePourDouble(String chaine) throws ParseException
    {
    NumberFormat format = NumberFormat.getInstance(Locale.CANADA_FRENCH);
    Number number = format.parse(chaine);
    return number.doubleValue();
    }


public static Intervalle parseChainePourIntervalle(String chaine) throws ExceptionParseur
    {
    if (chaineEstNombreEntier(chaine))
        {
        return parseNombreEntierEnIntervalle(chaine);
        }
    if (chaineEstIntervalle(chaine))
        {
        return parseIntervalle(chaine);
        }
    throw new ExceptionParseur(chaine);
    }

public static Integer parseChainePourInteger(String chaine) throws ExceptionParseur
    {
    if (chaineEstNombreEntier(chaine))
        {
        return parseNombreEntier(chaine);
        }
    if (chaineEstIntervalle(chaine))
        {
        throw new ExceptionParseur(chaine+ " Est un intervalle, doit etre unaire");
        }
    throw new ExceptionParseur(chaine);
    }


public static Date parseChainePourDate(String chaine) throws ExceptionParseur 
    {
    SimpleDateFormat formatter = new SimpleDateFormat(formatDateSimple);
    try
        {
        Date date = (Date)formatter.parse(chaine);
        return date;
        }
    catch (ParseException excP)
        {
        throw new ExceptionParseur(Date.class, chaine);
        }
    }













private static boolean  chaineEstIntervalle(String chaine)
    {
    return ((chaine.matches(regexIntervalleEntier)) & (chaine.charAt(0) == '[') & (chaine.charAt(chaine.length()-1) == ']') );
    }

private static boolean chaineEstNombreEntier(String chaine)
    {
    return (chaine.matches(regexNombreEntier));
    }


private static Integer parseNombreEntier (String chaine) throws ExceptionParseur
    {
    if (!chaineEstNombreEntier(chaine)) 
            {
            throw new ExceptionParseur(chaine);
            }
    return Integer.parseInt(chaine);
    }


private static Intervalle parseNombreEntierEnIntervalle (String chaine) throws ExceptionParseur 
    {
    Integer nombreParse = parseNombreEntier(chaine);
    return new Intervalle(nombreParse); 
    }
        
private static Intervalle parseIntervalle(String chaine) throws ExceptionParseur
    {
    if (!chaineEstIntervalle(chaine) )
        {
        throw new ExceptionParseur(chaine);
        }
    Pattern patternNombre = Pattern.compile(regexNombreEntier);   
    Matcher matcheur = patternNombre.matcher(chaine);
    String strNombrePlancher, strNombrePlafond;
    
    if (matcheur.find())
        {
        strNombrePlancher = (matcheur.group());
        if (matcheur.find())
            {
            strNombrePlafond = (matcheur.group());
            }
        else
            {
            throw new ExceptionParseur(chaine);
            }
        }
    else
        {
        throw new ExceptionParseur(chaine);
        }
    
    try {
        Integer intNombrePlancher = Integer.parseInt(strNombrePlancher);
        Integer intNombrePlafond = Integer.parseInt(strNombrePlafond);
        Intervalle nouvelIntervalle = new Intervalle(intNombrePlancher,intNombrePlafond);
        return nouvelIntervalle;
        }
    catch (NumberFormatException excNF)
        {
        throw new ExceptionParseur(chaine);
        }
    }
   
    






}   