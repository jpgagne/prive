
import java.util.HashMap;
import java.util.Map;

public class EnumMapConversion<V extends Enum<V> & EnumConvertisseur> 
{
  private Map<Intervalle, V> map = new HashMap<>();
  public EnumMapConversion(Class<V> valueType) throws ExceptionIntervalle
    {
    for (V v : valueType.getEnumConstants())
           {
        map.put(v.conversionIntervalle(), v);
        }
    }

public V get(Intervalle valeur) throws ExceptionValeurInexistante 
    {
    for (Map.Entry<Intervalle, V> entry : map.entrySet())
        {
        Intervalle intervalle = entry.getKey();
        
        System.out.print(intervalle.toString()+ " =?= " + valeur.toString());
        
        if (intervalle.equals(valeur))
            {
                System.out.println(" OUI");
            return entry.getValue();
            }
        else System.out.println("NON");
        }
    System.out.println("EnumMapConversion");
    throw new ExceptionValeurInexistante (valeur);
    }
    


}
    
    
  