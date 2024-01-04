public class IntDoubleList {
    private IntDoubleListElement head;
    private IntDoubleListElement tail;

    public IntDoubleList() {
        // The the beginning, the list is empty
        head = null;
        tail = null;
    }

    // inserting elements
    public void append(int info) {
        IntDoubleListElement newElement = new IntDoubleListElement(info);
        // If the list is empty
        if (head == null) {
            head = newElement;
            tail = newElement;
        } else {
            // If the list is non-empty, reach last element and add newElement
            tail.next = newElement;
            newElement.prev = tail;
            tail = newElement;
        }
    }

    public int size() {
        IntDoubleListElement temp = head;
        int j = 0;
        // count the number of elements until null is reached.
        while (temp != null) {
            temp = temp.next;
            j++;
        }
        return j;
    }

    public int get(int pos) {
        if (pos < 0 || this.size() <= pos) {
            System.out.println("Position exceeds list!");
            return 0;
        }

        IntDoubleListElement temp = head;
        for (int i = 0; i < pos; i++, temp = temp.next)
            ;

        return temp.getInfo();
    }

    public void remove(int pos) {
        IntDoubleListElement temp = head;
        // if pos > size, report error
        if (this.size() <= pos || pos < 0) {
            System.out.println("Position ist groesser als Liste!");
            // if pos==0, update head
        } else if (pos == 0) {
            head = head.next;
            if (head != null)
                head.prev = null;
            else
                // If list is now empty, also tail must be nullified.
                tail = null;
        } else {
            // If pos !=0 the element at pos -1 must be linked to the element at pos + 1
            int j = 0;
            while (j < pos - 1) {
                temp = temp.next;
                j++;
            }
            temp.next = temp.next.next;
            if (temp.next != null)
                temp.next.prev = temp;
            else
                // If temp.next == null, the end of the list has been reached
                tail = temp;
        }
    }

    public String toString() {
        IntDoubleListElement temp = head;
        StringBuilder ret = new StringBuilder();
        // List iteration
        while (temp != null) {
            // Strings iteratively concatenated
            ret.append(temp.getInfo());
            if (temp.next != null)
                // comma added when temp is not last element
                ret.append(',');
            temp = temp.next;
        }
        return ret.toString();
    }

    public boolean isEqual(IntDoubleList other) {
        IntDoubleListElement mytemp = head;
        IntDoubleListElement othertemp = other.head;

        while (mytemp != null) {
            if (!mytemp.isEqual(othertemp))
                return false;
            mytemp = mytemp.next;
            othertemp = othertemp.next;
        }
        return othertemp == null;
    }

    public int sum() {
        IntDoubleListElement temp = head;
        int ret = 0;
        // iterating over the list
        while (temp != null) {
            // iteratively summing
            ret = ret + temp.getInfo();
            temp = temp.next;
        }
        return ret;
    }

    public IntDoubleListElement getFirstElement() {
        return head;
    }

    public IntDoubleListElement getLastElement() {
        return tail;
    }

    // copying internal elements
    public IntDoubleList copy() {
        IntDoubleList ret = new IntDoubleList();

        // temp allows to iterate over the elements
        IntDoubleListElement temp = head;
        while (temp != null) {
            // store value into copy
            ret.append(temp.getInfo());
            temp = temp.next;
        }
        return ret;
    }

    public IntDoubleListElement[] search(int intValue) {
        IntDoubleListElement[] results = new IntDoubleListElement[this.size()];
        int j = 0;
        // temp allows to iterate over the elements
        IntDoubleListElement temp = head;
        while (temp != null) {
            // store element into results array
            if (temp.getInfo() == intValue) {
                results[j] = temp;
                j++;
            }
            temp = temp.next;
        }
        IntDoubleListElement[] ret = new IntDoubleListElement[j];
        for (int k = 0; k < j; k++)
            ret[k] = results[k];
        return ret;
    }
}