import java.util.Iterator;

public class IndexedTree<T> implements Iterable<T>{

    private Node<T> tree;
    public int size(){
        return Leaf.leafList.size();
    }

    public T get(int i){
        return (T) Leaf.leafList.get(i).getValue();
    }

    public void update(int i, T x){
        Leaf.leafList.get(i).setValue(x);
    }

    public void insert(int i, T x){
        if(i>=0 && i<size()){
            Leaf.leafList.get(i).insert(i,x);
        } else{
            Leaf.leafList.get(size()).insert(i, x);

        }
    }

    public void remove(int i){
        if(i<size()) {
            Leaf.leafList.remove(i);
        }else
            throw new IndexOutOfBoundsException();
    }


    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
        return iterator;
    }

    //resubmiting

}
