package ArrayListImpl;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ArrayListClass<T> implements ArrayListInterface<T> {

    

    // Constructs an empty list with an initial capacity of ten.
    public ArrayListClass() {

    }

    // Constructs an empty list with the specified initial capacity.
    public ArrayListClass(int initialCapacity) {

    }

    // Constructs a list containing the elements of the specified collection,
    // in the order they are returned by the collection's iterator.
    public ArrayListClass(Collection<? extends T> collection){

    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public boolean addAll(T element) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> elements) {
        return false;
    }

    @Override
    public void addFirst(T element) {

    }

    @Override
    public void addLast(T element) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Object clone() {
        return null;
    }

    @Override
    public boolean contains(Object object) {
        return false;
    }

    @Override
    public void ensureCapacity(int minCapacity) {

    }

    @Override
    public void forEach(Consumer<? super T> consumer) {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getFirst() {
        return null;
    }

    @Override
    public T getLast() {
        return null;
    }

    @Override
    public int indexOf(Object object) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public int lastIndexOf(Object object) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public boolean remove(Object object) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public boolean removeIf(Predicate<? super T> predicate) {
        return false;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public void removeRange(int fromIndex, int toIndex) {

    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return List.of();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public void trimToSize() {

    }
}


