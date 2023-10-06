package edgrantJBusRD;
import java.util.HashMap;
import java.util.Objects;

public class Serializable
{
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();

    public static Integer getLastAssignedId(Class<?> obj){
        return mapCounter.getOrDefault(obj, 0);
    }
    public static Integer setLastAssignedId(Class<?> obj, int id){
        return mapCounter.replace(obj, id);
    }

    public boolean equals(Serializable obj){
       return compareTo(obj);
    }

    public boolean compareTo(Serializable obj){
        return obj.getClass() == this.getClass() && obj.id == this.id;
    }

//    public boolean equals(Objects obj){
//        boolean b = (obj instanceof Serializable) && (((this) obj).id == this.id);
//        return b;
//    }



    protected Serializable(int id)
    {
        Class<?> obj = this.getClass();
        int lastId = getLastAssignedId(obj);
        this.id = lastId + 1;
        mapCounter.put(obj, this.id);
    }

}
