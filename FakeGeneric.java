public class FakeGeneric <V> {
    private V value;

    public FakeGeneric(V value) {
        this.value = value;
    }

    public V get() {
        return value;
    }

    public void set(V value) {
        this.value = value;
    }
}
