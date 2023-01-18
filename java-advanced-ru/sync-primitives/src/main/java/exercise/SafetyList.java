package exercise;

class SafetyList<T> {
    // BEGIN

    private T[] array = (T[]) new Object[1];

    private int size = 0;

    public final int getSize(){
        return this.size;
    }

    public final T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public synchronized final boolean add(final T t) {
        if (array.length == size) {
            final T[] oldM = array;
            array = (T[]) new Object[this.getSize() * 2];
            System.arraycopy(oldM, 0, array, 0, oldM.length);
        }
        array[size++] = t;
        return true;
    }
    // END
}
