import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class Heap {
    Unsafe unsafe;
    long base;
    long top;

    public Heap(int size) {
	this.unsafe = getUnsafe();
	this.base = unsafe.allocateMemory(size);
	this.top = base;
    }

    public long alloc(int bytes) {
	long tmp = top;
	top += bytes;
	return tmp;
    }

    public Object getObject(long thiz, int offset) {
	return Heap.l_to_a(unsafe.getLong(thiz+offset));
    }

    public void setObject(long thiz, int offset, Object o) {
	unsafe.putLong(thiz+offset, Heap.a_to_l(o));
    }

    public int getInt(long thiz, int offset) {
	return unsafe.getInt(thiz+offset);
    }

    public void setInt(long thiz, int offset, int value) {
	unsafe.putInt(thiz+offset, value);
    }

    static Unsafe getUnsafe() {
	try {
	    Field f = Unsafe.class.getDeclaredField("theUnsafe");
	    f.setAccessible(true);
	    return (Unsafe)f.get(null);
	} catch (Exception e) { 
	    e.printStackTrace();
	}
	return null;
    }

    public static native long a_to_l(Object o);
    public static native Object l_to_a(long o);

    static {
	System.loadLibrary("Heap");
    }
}
