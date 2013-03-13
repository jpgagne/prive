
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EnumMapConversion<V extends Enum<V> & EnumConvertisseur> 
{
  private Map<Intervalle, V> map = new HashMap<>();
  public EnumMapConversion(Class<V> valueType)
    {
    for (V v : valueType.getEnumConstants())
           {
//            System.out.print(v+" ");
//            System.out.println(v.conversion());
        map.put(v.conversion(), v);
        }
    
    }

public V get(Integer valeur) throws ExceptionValeurInexistante 
    {
    for (Map.Entry<Intervalle, V> entry : map.entrySet())
        {
        Intervalle intervalle = entry.getKey();
        if (intervalle.inclus(valeur))
            {
            return entry.getValue();
            }
        }
    throw new ExceptionValeurInexistante(new Integer (valeur));
    }
    


}
    
    
  