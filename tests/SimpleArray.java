import java.util.UUID;
import sun.misc.Unsafe;
import java.lang.reflect.Field;

class UUIDString {
    long uuid;

    //public native Object alloc(int n);
    
    public UUIDString(Unsafe u, String s) {
	this.uuid = u.allocateMemory(s.length());
	System.out.println(s.length() + ": " + this.uuid);
    }
}

public class SimpleArray {
    static UUIDString[] a = new UUIDString[10];

    public static void main(String[] argv) {
	Unsafe u = getUnsafe();
	for(int i= 0; i < 10; i++) {
	    a[i] = new UUIDString(u, UUID.randomUUID().toString());
	}
    }

    /*
    static {
	System.loadLibrary("UUIDString");
    }
    */
    public static Unsafe getUnsafe() {
	try {
	    Field f = Unsafe.class.getDeclaredField("theUnsafe");
	    f.setAccessible(true);
	    return (Unsafe)f.get(null);
	} catch (Exception e) { 
	    e.printStackTrace();
	}
	return null;
    }
}

    