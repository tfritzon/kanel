public class STn {

    public static native float foo(float a, float b);

    public static void main(String[] argv) {
	float x = 0;
	for (int i = 1; i < 1E8; i++) {
	    x = STn.foo(i, x);
	}
	System.out.println(x);
    }

    static {
	System.loadLibrary("STn");
    }
}
