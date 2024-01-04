package tree;

import java.util.ArrayList;
import java.util.List;

public class Node<T>{
    private List<Node<T>> children;
    private Node<T> parent;
    private T data;

    public Node(T data) {
        this.data = data;
        children = new ArrayList<>();
    }
    public void insert(Node<T> value) {
        this.children.add(value);
        value.parent=this;
    }
    public boolean isLeaf() {
        return this.children.size() == 0;
    }


    public int size() {
        if (this.isLeaf())
            return 1;
        else {
            int size = 0;
            for(Node<T> n : this.children){
                size += n.size();
            }
            return size+1;
        }
    }

    public void remove() {
        for(Node<T> n : this.children){
            n.setParent(this.parent);
            this.parent.getChildren().addAll(this.children);
        }
        this.parent.children.remove(this);
        this.data=null;
    }

    public void traversal(Node<T> root) {  //preorder traversal
        if(root == null)
            return;
        else
            System.out.print(root.data + " ");
        for(Node<T> n : root.children){
            traversal(n);
        }
    }


    public List<Node<T>> getChildren() {
        return children;
    }

    public Node<T> getParent() {
        return parent;
    }

    public T getData() {
        return data;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

}