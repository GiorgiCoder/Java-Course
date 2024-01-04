final class Inner<T> implements Node<T>{
    Node<T> right;
    Node<T> left;
    int lsize;
    int rsize;


    public int size(){
        return this.lsize + this.rsize;
    }

    @Override
    public T get(int i) {
        return (T) Leaf.leafList.get(i).getValue();
    }

    @Override
    public void update(int i, T x) {
        Leaf.leafList.get(i).setValue(x);
    }

    @Override
    public Node<T> insert(int i, T x) {
        if(i>=0 && i<size()){
            Leaf.leafList.get(i).insert(i,x);
            return this;
        }else if(i<0){
            return Leaf.leafList.get(i).insert(0,x);
        }else{
            Leaf.leafList.get(size()).insert(i, x);
            return this;
        }
    }

    @Override
    public Node<T> remove(int i) {
        if(i<size()) {
            Node<T> n = Leaf.leafList.get(i);
            Leaf.leafList.remove(i);
            return n;
        }else
            throw new IndexOutOfBoundsException();
    }
}