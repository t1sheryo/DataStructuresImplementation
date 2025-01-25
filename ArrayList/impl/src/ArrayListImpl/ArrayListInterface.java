package ArrayListImpl;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface ArrayListInterface<T> extends Serializable, Cloneable, Iterable<T>, Collection<T>, List<T>, RandomAccess, SequencedCollection<T> {
    void add(int index, T element);
    boolean addAll(T element);
    boolean addAll(int index, Collection<? extends T> elements);
    void addFirst(T element);
    void addLast(T element);
    void clear();
    Object clone();
    boolean contains(Object object);
    void ensureCapacity(int minCapacity);
    boolean equals(Object object);
    void forEach(Consumer<? super T> consumer);
    T get(int index);
    T getFirst();
    T getLast();
    int hashCode();
    int indexOf(Object object);
    boolean isEmpty();
    Iterator<T> iterator();
    int lastIndexOf(Object object);
    ListIterator<T> listIterator();
    ListIterator<T> listIterator(int index);
    T remove(int index);
    boolean remove(Object object);
    boolean removeAll(Collection<?> collection);
    T removeFirst();
    boolean removeIf(Predicate<? super T> predicate);
    T removeLast();
    void removeRange(int fromIndex, int toIndex);
    boolean retainAll(Collection<?> collection);
    T set(int index, T element);
    int size();
    Spliterator<T> spliterator();
    List<T> subList(int fromIndex, int toIndex);
    Object[] toArray();
    <T> T[] toArray(T[] array);
    void trimToSize();
}


