class OffHeap_Foo {
    final static byte off_a = 0;
    final static byte off_b = 4;

    static long _alloc(Heap heap) {
	return heap.alloc(off_b+4);
    }
    
    static void _init(Heap heap, long thiz, int a, int b) {
	heap.setInt(thiz, off_a, a);
	heap.setInt(thiz, off_b, b);
    }

    static int getFirst(Heap heap, long thiz) {
	return heap.getInt(thiz, off_a);
    }

    static int getLast(Heap heap, long thiz) {
	return heap.getInt(thiz, off_b);
    }
}

class Foo {
    int a, b;

    public Foo( int a, int b) {
	this.a = a;
	this.b = b;
    }

    public int getFirst() {
	return a;
    }

    public int getLast() {
	return b;
    }
}

public class IntTest1 {

    public static void main(String[] argv) {
	Heap heap = new Heap(1024);

	// Java Heap
	Foo foo = new Foo(42, 4711);
	System.out.println("Foo: " + foo.getFirst() + ", " + foo.getLast());

	// Kanelheap
	long oh_foo = OffHeap_Foo._alloc(heap);
	OffHeap_Foo._init(heap, oh_foo, 42, 4711);
	System.out.println(oh_foo + ": " + OffHeap_Foo.getFirst(heap, oh_foo) + ", " + OffHeap_Foo.getLast(heap, oh_foo));
    }
}
