import java.util.Iterator;
import java.util.ListIterator;

public class OneWayListWithHead<T> implements IList<T> {
    private class Element{

        private T value;
        private Element next;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }

        Element(T data){
            this.value=data;
        }
    }

    protected Element head;

    public OneWayListWithHead() {}

    private Element getElement(int index){
        Element element=head;
        while(index > 0 && element != null){
            index--;
            element = element.getNext();
        }
        return element;
    }

    @Override
        public boolean isEmpty(){
        return head == null;
    }

    public void addLast(T element){

    }
    @Override
    public boolean add(T element) {
        Element newElement = new Element(element);
        if(head == null){
            head = newElement;
            return true;
        }
        Element tail = head;
        while(tail.getNext() != null)
            tail = tail.getNext();
        tail.setNext(newElement);
        return true;
    }

    @Override
     public boolean add(int index, T data) {
        if(index < 0) return false;
        Element newElem = new Element(data);
        if(index == 0)
        {
            newElem.setNext(head);
            head = newElem;
            return true;
        }
        Element actElem = getElement(index-1);
        if(actElem == null)
            return false;
        newElem.setNext(actElem.getNext());
        actElem.setNext(newElem);
        return true;
    }

    @Override
    public int indexOf(T data) {
        int pos = 0;
        Element elem = head;
        while(elem != null)
        {
            if(elem.getValue() == data)
                return pos;
            pos++;
            elem = elem.getNext();
        }
        return -1;
    }

    @Override
    public boolean contains(T data) {
        return indexOf(data) >= 0;
    }

    @Override
    public T get(int index) {
        Element actElem = getElement(index);
        return actElem == null ? null : actElem.getValue();
    }

    @Override
    public T set(int index, T data) {
        Element elem = getElement(index);
        if(elem == null)
            return null;
        T eData = elem.getValue();
        elem.setValue(data);
        return eData;
    }

    @Override
    public T remove(int index) {
        if(head==null)
            return null;
        if(index==0){
            T retValue=head.getValue();
            head=head.getNext();
            return retValue;
        }
        Element actElem=getElement(index-1);
        if(actElem==null || actElem.getNext()==null)
            return null;
        T retValue=actElem.getNext().getValue();
        actElem.setNext(actElem.getNext().getNext());
        return retValue;
    }

    @Override
    public boolean remove(T value) {
        throw new UnsupportedOperationException();
    }


    private class InnerIterator implements Iterator<T> {
        Element element;
        public InnerIterator() {
            element = head;
        }
        @Override
        public boolean hasNext() {
            return element != null;
        }
        @Override
        public T next() {
            T value = element.getValue();
            element = element.getNext();
            return value;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        head=null;
    }

    @Override
    public int size() {
        int pos=0;
        Element actElem=head;
        while(actElem!=null)
        {
            pos++;
            actElem=actElem.getNext();
        }
        return pos;
    }
}

