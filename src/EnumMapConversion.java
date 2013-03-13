

import java.util.HashMap;
import java.util.Map;

public class EnumMapConversion<V extends Enum<V> & EnumConvertisseur> 
{
  private Map<Integer, V> map = new HashMap<>();
  public EnumMapConversion(Class<V> valueType) {
    for (V v : valueType.getEnumConstants()) {
      map.put(v.conversion(), v);
    }
  }

public V get(Integer num) throws ExceptionValeurInexistante 
{
    num = (num/100 * 100);// TODO : mieux gérer les intervalles
    
if (map.containsKey(num))
    {
    return map.get(num);
    }
else throw new ExceptionValeurInexistante(new Integer (num));
}

}
    
    
    
  