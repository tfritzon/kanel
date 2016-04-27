public class STj {

    public static float foo(float a, float b) {
	return (a+b)/a;
    }

    public static void main(String[] argv) {
	float x = 0;
	for (int i = 1; i < 1E8; i++) {
	    x = STj.foo(i, x);
	}
	System.out.println(x);
    }
}
