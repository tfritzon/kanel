class OffHeap_Foo2 {
    final static byte off_a = 0;
    final static byte off_b = 8;

    static long _alloc(Heap heap) {
	return heap.alloc(off_b+8);
    }
    
    static void _init(Heap heap, long thiz, Integer a, Integer b) {
	heap.setObject(thiz, off_a, a);
	heap.setObject(thiz, off_b, b);
    }

    static Integer getFirst(Heap heap, long thiz) {
	Object a = heap.getObject(thiz, off_a);
	return (Integer)a;
    }

    static Integer getLast(Heap heap, long thiz) {
	return (Integer)heap.getObject(thiz, off_b);
    }
}

class Foo2 {
    Integer a, b;

    public Foo2(Integer a, Integer b) {
	this.a = a;
	this.b = b;
    }

    public Integer getFirst() {
	return a;
    }

    public Integer getLast() {
	return b;
    }
}

public class IntTest2 {

    public static void main(String[] argv) {
	Heap heap = new Heap(1024);

	// Java Heap
	Foo2 foo = new Foo2(42, 4711);
	System.out.println("Foo: " + foo.getFirst() + ", " + foo.getLast());

	// Kanelheap
	long oh_foo = OffHeap_Foo2._alloc(heap);
	OffHeap_Foo2._init(heap, oh_foo, new Integer(42), new Integer(4711));
	System.out.println(oh_foo + ": " + OffHeap_Foo2.getFirst(heap, oh_foo) + ", " + OffHeap_Foo2.getLast(heap, oh_foo));
    }
}
