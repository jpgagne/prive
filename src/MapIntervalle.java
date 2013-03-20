
import java.util.*;


abstract class MapIntervallee<V> 
{

    
    
private final SortedMap<Intervalle, V> map = new TreeMap<Intervalle, V>();


public V get(Intervalle cle) throws ExceptionIntervalle 
    {
    if (map.containsKey(cle))
        {
        return map.get(cle);
        } 
    else 
        {
        throw new ExceptionIntervalle(cle);
        }
    }

   

    
 
public SortedSet<Intervalle> keysetIntervalle()
    {
    SortedSet<Intervalle> sortedSet = new TreeSet<Intervalle>(map.keySet());
    return sortedSet;
    }
    
public SortedSet<Integer> keysetInteger() throws ExceptionIntervalle
  
    {
    SortedSet<Integer> sortedSetIntegerFlatten = new TreeSet<Integer>();       
    SortedSet<Intervalle> sortedSetIntervalle = this.keysetIntervalle();
    for (Iterator<Intervalle> it = sortedSetIntervalle.iterator(); it.hasNext();)
        {
        Intervalle intervalle = it.next();
        SortedSet<Integer> sortedSetInteger = intervalle.sequence();
            for (Iterator<Integer> it1 = sortedSetInteger.iterator(); it1.hasNext();)
            {
            Integer integer = it1.next();
            if (sortedSetIntegerFlatten.contains(integer))
                {
                throw new ExceptionIntervalle(integer);
                }
            sortedSetIntegerFlatten.add(integer);              
            }
        }
    return sortedSetIntegerFlatten;
    }






public boolean intervalleOuvert(Intervalle cleIntervalle)
    {
    return true;
    }
    
public void putIntervalle(Intervalle cleIntervalle, V objet) throws ExceptionIntervalle
    {
    if (!intervalleOuvert(cleIntervalle))
            {
                throw new ExceptionIntervalle(cleIntervalle);
            }
    }


public V getInteger(Integer cleInteger)
    {
    return null;
    }

public V getIntervalle(Intervalle cleIntervalle) 
    {
    if (map.containsKey(cleIntervalle))
        {
        return map.get(cleIntervalle);
        }
    return null;
    }

    //public abstract V getData(Object key);
    
    
    
    
    

    
    
    
    










}// End Class MapIntervalle