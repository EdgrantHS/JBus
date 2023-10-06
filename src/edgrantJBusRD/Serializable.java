package edgrantJBusRD;
import java.util.HashMap;
import java.util.Objects;

public class Serializable
{
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();

    public static<T> Integer getLastAssignedId(Class<T> obj){
        return mapCounter.getOrDefault(obj, 0);
    }
    public static<T> Integer setLastAssignedId(Class<T> obj, int id){
        return mapCounter.replace(obj, id);
    }

    public boolean equals(Serializable obj){
        return (obj != null) && (obj.id == this.id);
    }

    public boolean equals(Object obj){
//        Serializable temp = ((Serializable) obj);
        return obj instanceof Serializable && (((Serializable) obj).id == this.id);
    }

    public boolean booleancompareTo(Serializable obj){
        return obj != null && obj.getClass() == this.getClass() && obj.id == this.id;
    }

    //test

    protected Serializable(int id)
    {
        Class<?> obj = this.getClass();
        int lastId = getLastAssignedId(obj);
        this.id = lastId + 1;
        mapCounter.put(obj, this.id);
    }

}
