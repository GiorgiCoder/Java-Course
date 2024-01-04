import java.util.ArrayList;
import java.util.List;

final class Leaf<T> implements Node<T>{

    T value;
    public static List<Leaf> leafList = new ArrayList<>();

    public int size(){
        return 1;
    }

    public Leaf(T value) {
        this.value = value;
        leafList.add(this);
    }

    @Override
    public T get(int i){
        if(i==0)
            return this.value;
        else
            throw new IndexOutOfBoundsException();
    }

    @Override
    public void update(int i, T x){
        if(i==0){
            this.value = x;
        }else
            throw new IndexOutOfBoundsException();
    }

    @Override
    public Node<T> insert(int i, T x){
        if(i<=0){
            Inner<T> newNode1 = new Inner<>();
            newNode1.left = new Leaf<>(x);
            newNode1.right = this;
            return newNode1;
        }else{
            Inner<T> newNode2 = new Inner<>();
            newNode2.left = this;
            newNode2.right = new Leaf<>(x);
            return newNode2;
        }
    }

    @Override
    public Node<T> remove(int i){
        if(i==0){
            return null;
        }else
            throw new IndexOutOfBoundsException();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
