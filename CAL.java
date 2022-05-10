public class CAL <V> {
    
    private FakeGeneric[] list;

    public CAL() {

        list = new FakeGeneric[0];
    }

    public V get(int index) {
        return (V) list[index].get();
    }

    public void set(int index, V element) {
        list[index] = new FakeGeneric<V>(element);
    }

    public void remove(int index) {
        if(index >= list.length) return;
        list[index] = null;
        FakeGeneric[] temp = new FakeGeneric[list.length-1];
        int ind = 0;
        for(int i = 0; i < list.length; i++) {
            if(list[i] == null) continue;
            temp[ind] = list[i];
            ind++;
        }
        list = temp;
    }

    public void add(V element) {
        FakeGeneric[] newList = new FakeGeneric[list.length+1];
        for(int i=0; i<list.length; i++) {
            newList[i] = list[i];
        }
        newList[list.length] = new FakeGeneric<V>(element);
        list = newList;
    }

    public int size() {return list.length;}
}