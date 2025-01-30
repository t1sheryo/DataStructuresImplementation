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

    // TODO: optimize methods by adding local variables
    //  and avoiding extra memory jumps

    // TODO: add modificationCount variable to
    // keep track of modification(read more about it)

    // TODO: try to implement thread safe version
    // (read more about it and learn)

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
    public boolean add(T element) {
        add(size, element);
        return true;
    }

    // Appends all the elements in the specified collection to the end of this list,
    // in the order that they are returned by the specified collection's Iterator.
    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return addAll(this.size, collection);
    }

    // Inserts all the elements in the specified collection into this list, starting at the specified position.
    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if(collection == null) {
            throw new NullPointerException("Null Collection!");
        }

        if(collection.isEmpty()) {
            return false;
        }

        int pos = index;
        T[] array = (T[]) collection.toArray();
        for(final T el : array){
            if(size >= this.innerArray.length){
                grow();
            }
            add(pos++, el);
            size++;
        }

        return true;
    }

    // Adds an element as the first element of this collection (optional operation).
    @Override
    public void addFirst(T element) {
        add(0, element);
    }

    // Adds an element as the last element of this collection (optional operation).
    @Override
    public void addLast(T element) {
        add(size, element);
    }

    // Removes all the elements from this list.
    @Override
    public void clear() {
        int sizeSaved = this.size;
        this.size = 0;

        for(int i = 0; i < sizeSaved; i++){
            this.innerArray[i] = null;
        }
    }

    // Returns a shallow copy of this ArrayList instance.
    @Override
    public Object clone() {
        try {
            ArrayListClass<T> clone = (ArrayListClass<T>) super.clone();
            // now innerArray points on clone innerArray so we need to make shallow copy of if
            clone.innerArray = Arrays.copyOf(this.innerArray, this.innerArray.length);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    // Returns true if this list contains the specified element.
    @Override
    public boolean contains(Object object) {
        return !(indexOf(object) == -1);
    }

    // Increases the capacity of this ArrayList instance, if necessary,
    // to ensure that it can hold at least the number of
    // elements specified by the minimum capacity argument.
    public void ensureCapacity(int minCapacity) {
        if(minCapacity > this.innerArray.length
            && minCapacity > DEFAULT_CAPACITY
            && !(this.innerArray == DEFAULT_CAPACITY_EMPTY_ARRAY)){
            grow(minCapacity);
        }
    }

    // Performs the given action for each element of the
    // Iterable until all elements have been processed
    // or the action throws an exception.
    public void forEach(Consumer<? super T> consumer) {
        if(consumer == null){
            throw new NullPointerException("Null Consumer!");
        }
        for(int i = 0; i < size; i++){
            consumer.accept(get(i));
        }
    }

    // Returns the element at the specified position in this list.
    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Illegal index: " + index);
        }

        return (T) this.innerArray[index];
    }

    // Gets the first element of this collection.
    @SuppressWarnings("unchecked")
    @Override
    public T getFirst() {
        if(size == 0){
            throw new NoSuchElementException("Empty List!");
        }
        return (T) this.innerArray[0];
    }

    // Gets the last element of this collection.
    @SuppressWarnings("unchecked")
    @Override
    public T getLast() {
        if(size == 0){
            throw new NoSuchElementException("Empty List!");
        }
        return (T) this.innerArray[size - 1];
    }

    // Returns the index of the first occurrence of the specified element in this list,
    // or -1 if this list does not contain the element. More formally,
    // returns the lowest index i such that Objects.equals(o, get(i)), or -1 if there is no such index.
    @Override
    public int indexOf(Object object) {
        if(object == null){
            for(int i = 0; i < size; i++){
                if(this.innerArray[i] == null){
                    return i;
                }
            }
        }
        else{
            for(int i = 0; i < size; i++){
                if(this.innerArray[i].equals(object)) return i;
            }
        }

        return -1;
    }

    // Returns true if this list contains no elements.
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // TODO
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    // Returns the index of the last occurrence of the
    // specified element in this list, or -1 if this list does not contain the element.
    @Override
    public int lastIndexOf(Object object) {
        int size = this.size;
        int result = -1;

        if(object == null){
            for(int i = 0; i < size; i++){
                if(this.innerArray[i] == null){
                    result = i;
                }
            }
        }
        else{
            for(int i = 0; i < size; i++){
                if(this.innerArray[i] == null){
                    result = i;
                }
            }
        }

        return result;
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

    // Removes the element at the specified position in this list.
    // Shifts any subsequent elements to the left (subtracts one from their indices).
    @Override
    public T remove(int index) {
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Illegal index: " + index);
        }
        @SuppressWarnings("unchecked")
        T returnedValue = (T) this.innerArray[index];
        fastRemove(this.innerArray, index);
        size--;
        return returnedValue;
    }

    // Removes the first occurrence of the specified element from this list, if it is present.
    // If the list does not contain the element, it is unchanged. More formally,
    // removes the element with the lowest index i such that Objects.equals(o, get(i))
    // (if such an element exists).
    // Returns true if this list contained the specified element
    // (or equivalently, if this list changed as a result of the call).
    @Override
    public boolean remove(Object object) {
        if(object == null){
            throw new NullPointerException("Null Object!");
        }
        if(size == 0){
            throw new NoSuchElementException("Empty List!");
        }
        for(int i = 0; i < size; i++){
            if(this.innerArray[i].equals(object)){
                fastRemove(this.innerArray, i);
                size--;
                return true;
            }
        }
        return false;
    }

    // TODO
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    // Removes from this list all of its elements
    // that are contained in the specified collection.
    @Override
    public boolean removeAll(Collection<?> collection) {
        // TODO
        return false;
    }

    // Removes and returns the first element of this collection (optional operation).
    @Override
    public T removeFirst() {
        if(size == 0){
            throw new NoSuchElementException("Empty List!");
        }
        @SuppressWarnings("unchecked")
        T returnedValue = (T) this.innerArray[0];
        fastRemove(this.innerArray, 0);
        size--;
        return returnedValue;
    }

    // Removes all the elements of this collection that satisfy the given predicate (optional operation).
    // Errors or runtime exceptions thrown during iteration or by the predicate are relayed to the caller.
    public boolean removeIf(Predicate<? super T> predicate) {
        if(predicate == null){
            throw new NullPointerException("Null Predicate!");
        }
        int countOfRemoved = 0;
        for(int i = 0; i < size; i++){
            if(predicate.test(get(i))){
                fastRemove(this.innerArray, i);
                countOfRemoved++;
            }
        }
        size -= countOfRemoved;
        return countOfRemoved > 0;
    }

    // Removes and returns the last element of this collection (optional operation).
    @Override
    public T removeLast() {
        if(size == 0){
            throw new NoSuchElementException("Empty List!");
        }
        @SuppressWarnings("unchecked")
        T returnedValue = (T) this.innerArray[size - 1];
        fastRemove(this.innerArray, size - 1);
        size--;
        return returnedValue;
    }

    // Removes from this list all the elements whose index is between fromIndex,
    // inclusive, and toIndex, exclusive. Shifts any succeeding elements to the left
    // (reduces their index). This call shortens the list by (toIndex - fromIndex) elements.
    // (If toIndex==fromIndex, this operation has no effect.)
    @Override
    public void removeRange(int fromIndex, int toIndex) {
        if(fromIndex < 0 || fromIndex > toIndex || fromIndex >= size){
            throw new IllegalArgumentException("Illegal index!");
        }
        for(int i = fromIndex; i <= toIndex; i++){
            fastRemove(this.innerArray, i);
        }
        size = size - (toIndex - fromIndex);
    }

    // TODO
    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    // Replaces the element at the specified position in this list with the specified element.
    @Override
    public T set(int index, T element) {
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Illegal index: " + index);
        }
        @SuppressWarnings("unchecked")
        T returnedValue = (T) this.innerArray[index];
        this.innerArray[index] = element;
        return returnedValue;
    }

    @Override
    public int size() {
        return size;
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

    // Trims the capacity of this ArrayList instance to be the list's current size.
    // An application can use this operation to minimize the storage of an ArrayList instance.
    public void trimToSize() {
        if(size < this.innerArray.length){
            this.innerArray = (size == 0) ?
                    EMPTY_ARRAY : Arrays.copyOf(this.innerArray, size);
        }
    }

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

    private void fastRemove(Object[] array, int index){
        final int newSize = array.length - 1;
        if(newSize > index){
            System.arraycopy(array, index + 1, array, index, newSize - index);
        }
        array[newSize] = null;
    }

    // TODO : implement some other methods which i haven't covered for now
}


