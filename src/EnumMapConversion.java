
import java.util.HashMap;
import java.util.Map;

public class EnumMapConversion<V extends Enum<V> & EnumConvertisseur> 
{
  private Map<Intervalle, V> map = new HashMap<>();
  public EnumMapConversion(Class<V> valueType)
    {
    for (V v : valueType.getEnumConstants())
           {
        map.put(v.conversion(), v);
        }
    }

public V get(Intervalle valeur) throws ExceptionValeurInexistante 
    {
    for (Map.Entry<Intervalle, V> entry : map.entrySet())
        {
        Intervalle intervalle = entry.getKey();
        if (intervalle.equals(valeur))
            {
            return entry.getValue();
            }
        }
    throw new ExceptionValeurInexistante (valeur);
    }
    


}
    
    
  