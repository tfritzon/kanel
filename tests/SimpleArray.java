import java.util.UUID;

class UUIDString {
    Object uuid;

    public native Object alloc(int n);
    
    public UUIDString(String s) {
	this.uuid = alloc(s.length());
	System.out.println(s.length() + ": " + this.uuid);
    }
}

public class SimpleArray {
    static UUIDString[] a = new UUIDString[10];

    public static void main(String[] argv) {
	for(int i= 0; i < 10; i++) {
	    a[i] = new UUIDString(UUID.randomUUID().toString());
	}
    }

    static {
	System.loadLibrary("UUIDString");
    }
}

    