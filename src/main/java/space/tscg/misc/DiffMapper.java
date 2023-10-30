package space.tscg.misc;

import java.lang.reflect.Field;

import space.tscg.collections.DiffMap;

public class DiffMapper
{
    @SuppressWarnings("unchecked")
    public static <T> DiffMap build(T obj1, T obj2)
    {
        Class<T> class1 = (Class<T>) obj1.getClass();
        Class<T> class2 = (Class<T>) obj2.getClass();

        if (class1.equals(class2)) {
            DiffMap.Builder builder = DiffMap.Builder();
            for (int i = 0; i < class1.getDeclaredFields().length; i++) {
                Field f1 = class1.getDeclaredFields()[i];
                f1.setAccessible(true);

                Field f2 = class2.getDeclaredFields()[i];
                f2.setAccessible(true);

                try {
                    var t1 = f1.get(obj1);
                    var t2 = f2.get(obj2);

                    if(t1 instanceof Enum && t2 instanceof Enum)
                    {
                        builder.appendEnum(f1.getName(), (Enum<?>) t1, (Enum<?>) t2);
                    }
                    
                    if (t1 instanceof String && t2 instanceof String) {
                        builder.append(f1.getName(), (String) t1, (String) t2);
                    } 
                    
                    if (t1 instanceof Boolean && t2 instanceof Boolean) {
                        builder.append(f1.getName(), (boolean) t1, (boolean) t2);
                    }
                    
                    if (t1 instanceof Integer && t2 instanceof Integer) {
                        builder.append(f1.getName(), (int) t1, (int) t2);
                    }
                    
                    if (t1 instanceof Long && t2 instanceof Long) {
                        builder.append(f1.getName(), (long) t1, (long) t2);
                    }

                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            return builder.build();
        }

        return null;
    }
}
