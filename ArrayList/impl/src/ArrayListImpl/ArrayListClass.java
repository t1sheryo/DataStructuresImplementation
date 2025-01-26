package ArrayListImpl;

import java.util.*;
import java.io.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import jdk.internal.util.ArraysSupport;

public class ArrayListClass<T> extends AbstractList<T>
        implements RandomAccess, Cloneable, Serializable, Iterable<T>,
            Collection<T>, List<T>, SequencedCollection<T> {

    @Serial
    private static final long serialVersionUID = 1399283982983923L;
    private final int DEFAULT_CAPACITY = 10;
    private Object[] innerArray;
    private static final Object[] EMPTY_ARRAY = {};
    // We use it to distinguish case when we have default capacity array
    // from when we have empty array. To save memory we don't initialize
    // initial array with DEFAULT_CAPACITY. Instead, we do it only when
    // we add first element or in some other methods where we manipulate
    // inner array.
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ARRAY = {};
    private int size;


    // Constructs an empty list with an initial capacity of ten. In fact,
    // we initialize it with empty array for a while.
    public ArrayListClass() {
        this.innerArray = DEFAULT_CAPACITY_EMPTY_ARRAY;
    }

    // Constructs an empty list with the specified initial capacity.
    public ArrayListClass(int initialCapacity) {
        if(initialCapacity > 0){
            this.innerArray = new Object[initialCapacity];
        }
        else if(initialCapacity == 0){
            this.innerArray = EMPTY_ARRAY;
        }
        else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    // Constructs a list containing the elements of the specified collection,
    // in the order they are returned by the collection's iterator.
    public ArrayListClass(Collection<? extends T> collection){
        Object[] array = collection.toArray();
        if((size = array.length) == 0){
            this.innerArray = EMPTY_ARRAY;
        }
        else {
            this.innerArray = Arrays.copyOf(array, size , Object[].class);
        }
    }

    // Inserts the specified element at the specified position in this list.
    @Override
    public void add(int index, T element) {
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Illegal index: " + index);
        }

        Object[] array = innerArray;
        // if there is not enough capacity we inflate an array
        // and in grow() method we modify innerArray and return it
        // to assign the reference to array so we keep modifying innerArray
        if(size == array.length){
            array = grow();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    // Appends the specified element to the end of this list.
    public boolean addAll(T element) {
        return false;
    }

    // Inserts all of the elements in the specified collection into this list, starting at the specified position.
    @Override
    public boolean addAll(int index, Collection<? extends T> elements) {
        return false;
    }

    // Adds an element as the first element of this collection (optional operation).
    @Override
    public void addFirst(T element) {

    }

    // Adds an element as the last element of this collection (optional operation).
    @Override
    public void addLast(T element) {

    }

    // TODO
    @Override
    public void clear() {

    }

    // TODO
    @Override
    public Object clone() {
        return null;
    }

    // TODO
    @Override
    public boolean contains(Object object) {
        return false;
    }

    // TODO
    public void ensureCapacity(int minCapacity) {

    }

    // TODO
    public void forEach(Consumer<? super T> consumer) {

    }

    // TODO
    @Override
    public T get(int index) {
        return null;
    }

    // TODO
    @Override
    public T getFirst() {
        return null;
    }

    // TODO
    @Override
    public T getLast() {
        return null;
    }

    // TODO
    @Override
    public int indexOf(Object object) {
        return 0;
    }

    // TODO
    @Override
    public boolean isEmpty() {
        return false;
    }

    // TODO
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    // TODO
    @Override
    public int lastIndexOf(Object object) {
        return 0;
    }

    // TODO
    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    // TODO
    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    // TODO
    @Override
    public T remove(int index) {
        return null;
    }

    // TODO
    @Override
    public boolean remove(Object object) {
        return false;
    }

    // TODO
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    // TODO
    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    // TODO
    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    // TODO
    @Override
    public T removeFirst() {
        return null;
    }

    // TODO
    public boolean removeIf(Predicate<? super T> predicate) {
        return false;
    }

    // TODO
    @Override
    public T removeLast() {
        return null;
    }

    // TODO
    @Override
    public void removeRange(int fromIndex, int toIndex) {

    }

    // TODO
    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    // TODO
    @Override
    public T set(int index, T element) {
        return null;
    }

    // TODO
    @Override
    public int size() {
        return 0;
    }

    // TODO
    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    // TODO
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return List.of();
    }

    // TODO
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    // TODO
    @Override
    public <T1> T1[] toArray(T1[] array) {
        return null;
    }

    // TODO
    @Override
    public boolean add(T t) {
        return false;
    }

    // TODO
    public void trimToSize() {}

    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = this.innerArray.length;
        if(oldCapacity < minCapacity || this.innerArray != DEFAULT_CAPACITY_EMPTY_ARRAY){
            int newCapacity = ArraysSupport.newLength(oldCapacity,
                    minCapacity - oldCapacity, oldCapacity >> 1);
            this.innerArray = Arrays.copyOf(this.innerArray, newCapacity);
        }
        else{
            this.innerArray = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }

        return this.innerArray;
    }

    // TODO : implement some other methods which i haven't covered for now
}


