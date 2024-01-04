package tree;
import penguinDate.Penguin;
import penguinDate.PenguinDateGenerator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree<T> {

    private Node<T> root;

    public Tree (T data) {
        root = new Node(data);
    }

    // I decided to import list because it's easier and is not prohibited on Artemis
    private List<T> allNodesValues(Node<T> node1){  //method which returns a list of all nodes' values (except the one on which it was called)
        List allNodesValues = new ArrayList();
        for(Node<T> n : node1.getChildren()){
            allNodesValues.add(n.getData());
            allNodesValues.addAll(this.allNodesValues(n));
        }
        return allNodesValues;
    }

    private Node<T> getNodeWithValue(Node<T> node, T value){
        if(node.getData().equals(value))
            return node;
        else{
            for (Node<T> n : node.getChildren()) {
                if(getNodeWithValue(n, value)!=null)
                    return getNodeWithValue(n, value);
            }
        }
        return null;
    }

    public void insert(T value, T parent) {
        if(this.root.getData()!=value && !this.allNodesValues(this.getRoot()).contains(value) && (this.allNodesValues(this.getRoot()).contains(parent) || this.root.getData().equals(parent))){
            Node<T> n = new Node<>(value);
            getNodeWithValue(this.root, parent).insert(n);
        }
    }

    public void remove(T value) {
        if(this.allNodesValues(this.getRoot()).contains(value)){
            getNodeWithValue(this.root, value).remove();
        }
    }

    public T LCA(T a, T b) {  //LCA is when depths and data s are same
        Node<T> nodeA = getNodeWithValue(this.root, a);
        Node<T> nodeB = getNodeWithValue(this.root, b);
        while(this.findDepth(nodeA.getData())!=this.findDepth(nodeB.getData())){
            if(this.findDepth(nodeA.getData())>this.findDepth(nodeB.getData())){
                nodeA=nodeA.getParent();
            }else if(this.findDepth(nodeA.getData())<this.findDepth(nodeB.getData())){
                nodeB=nodeB.getParent();
            }
        }
        if(nodeA.getData() == nodeB.getData()) {
            return nodeA.getData();
        }
        while(nodeA.getData()!=nodeB.getData()){
            nodeA = nodeA.getParent();
            nodeB = nodeB.getParent();
        }
        return nodeA.getData();
    }

    private int findDepth(T n){
        Node<T> m = getNodeWithValue(this.root, n);
        int depth = 0;
        while(!m.getData().equals(this.root.getData())){
            m = m.getParent();
            depth++;
        }
        return depth;
    }

    public int distanceBetweenNodes(T a, T b) {
        Node<T> nodeA1 = getNodeWithValue(this.root, a);
        Node<T> nodeB1 = getNodeWithValue(this.root, b);
        int distance=0;
        while(!nodeA1.getData().equals(LCA(a,b))){
            nodeA1 = nodeA1.getParent();
            distance++;
        }
        while(!nodeB1.getData().equals(LCA(a,b))){
            nodeB1 = nodeB1.getParent();
            distance++;
        }
        return distance;
    }


    private boolean containsKey(Node<T> root, T key) {  // method containsKey which takes root as a parameter
        if (root.getData().equals(key)) {
            return true;
        }
        for (Node<T> n : root.getChildren()) {
            if(containsKey(n, key))
                return true;
        }
        return false;
    }

    public boolean containsKey(T key) {
        return containsKey(this.root, key);
    }

    public void traversal() {
        traversal(this.getRoot());
    }
    private void traversal(Node<T> root1) {  //preorder traversal
        if(root1 == null)
            return;
        else
            System.out.print(root1.getData() + " ");
        for (Node<T> n : root1.getChildren()) {
            traversal(n);
        }
    }


    public Node<T> getRoot() {
        return this.root;
    }

}