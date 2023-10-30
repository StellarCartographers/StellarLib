package space.tscg.collections.list;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import space.tscg.collections.ListUtility;
import space.tscg.collections.decorator.IteratorDecorator;
import space.tscg.collections.decorator.ListIteratorDecorator;
import space.tscg.collections.decorator.SerializableListDecorator;
import space.tscg.collections.set.UnalterableSet;

public class UniqueList<E> extends SerializableListDecorator<E>
{

    private static final long serialVersionUID = -6589293803395027035L;
    private final Set<E>      set;

    public static <E> UniqueList<E> uniqueList(final List<E> list)
    {
        Objects.requireNonNull(list, "list");
        if (list.isEmpty()) {
            return new UniqueList<>(list, new HashSet<>());
        }
        final List<E> temp = new ArrayList<>(list);
        list.clear();
        final UniqueList<E> sl = new UniqueList<>(list, new HashSet<>());
        sl.addAll(temp);
        return sl;
    }

    protected UniqueList(final List<E> list, final Set<E> set)
    {
        super(list);
        this.set = Objects.requireNonNull(set, "set");
    }

    public Set<E> asSet()
    {
        return UnalterableSet.unalterableSet(set);
    }

    @Override
    public boolean add(final E object)
    {
        final int sizeBefore = size();

        add(size(), object);

        return sizeBefore != size();
    }

    @Override
    public void add(final int index, final E object)
    {
        if (!set.contains(object)) {
            set.add(object);
            super.add(index, object);
        }
    }

    @Override
    public boolean addAll(final Collection<? extends E> coll)
    {
        return addAll(size(), coll);
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends E> coll)
    {
        final List<E> temp = new ArrayList<>();
        for (final E e : coll) {
            if (set.add(e)) {
                temp.add(e);
            }
        }
        return super.addAll(index, temp);
    }

    @Override
    public E set(final int index, final E object)
    {
        final int pos = indexOf(object);
        final E removed = super.set(index, object);

        if (pos != -1 && pos != index) {
            super.remove(pos);
        }

        set.remove(removed);
        set.add(object);

        return removed;
    }

    @Override
    public boolean remove(final Object object)
    {
        final boolean result = set.remove(object);
        if (result) {
            super.remove(object);
        }
        return result;
    }

    @Override
    public E remove(final int index)
    {
        final E result = super.remove(index);
        set.remove(result);
        return result;
    }

    @Override
    public boolean removeIf(final Predicate<? super E> filter)
    {
        final boolean result = super.removeIf(filter);
        set.removeIf(filter);
        return result;
    }

    @Override
    public boolean removeAll(final Collection<?> coll)
    {
        boolean result = false;
        for (final Object name : coll) {
            result |= remove(name);
        }
        return result;
    }

    @Override
    public boolean retainAll(final Collection<?> coll)
    {
        final boolean result = set.retainAll(coll);
        if (!result) {
            return false;
        }
        if (set.isEmpty()) {
            super.clear();
        } else {
            super.retainAll(set);
        }
        return result;
    }

    @Override
    public void clear()
    {
        super.clear();
        set.clear();
    }

    @Override
    public boolean contains(final Object object)
    {
        return set.contains(object);
    }

    @Override
    public boolean containsAll(final Collection<?> coll)
    {
        return set.containsAll(coll);
    }

    @Override
    public Iterator<E> iterator()
    {
        return new SetListIterator<>(super.iterator(), set);
    }

    @Override
    public ListIterator<E> listIterator()
    {
        return new SetListListIterator<>(super.listIterator(), set);
    }

    @Override
    public ListIterator<E> listIterator(final int index)
    {
        return new SetListListIterator<>(super.listIterator(index), set);
    }

    @Override
    public List<E> subList(final int fromIndex, final int toIndex)
    {
        final List<E> superSubList = super.subList(fromIndex, toIndex);
        final Set<E> subSet = createSetBasedOnList(set, superSubList);
        return ListUtility.unalterableList(new UniqueList<>(superSubList, subSet));
    }

    @SuppressWarnings("unchecked")
    protected Set<E> createSetBasedOnList(final Set<E> set, final List<E> list)
    {
        Set<E> subSet;
        if (set.getClass().equals(HashSet.class)) {
            subSet = new HashSet<>(list.size());
        } else {
            try {
                subSet = set.getClass().getDeclaredConstructor(set.getClass()).newInstance(set);
            } catch (final InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ie) {
                subSet = new HashSet<>();
            }
        }
        subSet.addAll(list);
        return subSet;
    }

    static class SetListIterator<E> extends IteratorDecorator<E>
    {

        private final Set<E> set;
        private E            last;

        protected SetListIterator(final Iterator<E> it, final Set<E> set)
        {
            super(it);
            this.set = set;
        }

        @Override
        public E next()
        {
            last = super.next();
            return last;
        }

        @Override
        public void remove()
        {
            super.remove();
            set.remove(last);
            last = null;
        }
    }

    static class SetListListIterator<E> extends ListIteratorDecorator<E>
    {

        private final Set<E> set;
        private E            last;

        protected SetListListIterator(final ListIterator<E> it, final Set<E> set)
        {
            super(it);
            this.set = set;
        }

        @Override
        public E next()
        {
            last = super.next();
            return last;
        }

        @Override
        public E previous()
        {
            last = super.previous();
            return last;
        }

        @Override
        public void remove()
        {
            super.remove();
            set.remove(last);
            last = null;
        }

        @Override
        public void add(final E object)
        {
            if (!set.contains(object)) {
                super.add(object);
                set.add(object);
            }
        }

        @Override
        public void set(final E object)
        {
            throw new UnsupportedOperationException("ListIterator does not support set");
        }
    }

}
