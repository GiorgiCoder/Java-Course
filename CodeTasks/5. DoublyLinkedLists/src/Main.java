public class Main {
    public static void main (String[] args) {
        IntDoubleList lst = new IntDoubleList();
        lst.append(7);
        lst.append(12);
        String t = lst.toString();
        System.out.print(t);

    }
}