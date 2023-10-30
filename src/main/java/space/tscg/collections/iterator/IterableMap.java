package space.tscg.collections.iterator;

import java.util.Map;

import space.tscg.collections.Put;

public interface IterableMap<K, V> extends Map<K, V>, Put<K, V>, IterableGet<K, V>
{

}
