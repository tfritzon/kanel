import java.lang.instrument.*;
import java.security.*;
import java.util.*;
import org.objectweb.asm.*;

public class Agent {
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
                @Override
                public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {

                    // Intercept the call to the class Stuff
                    List<String> classes = Arrays.asList(agentArgs.split(":"));
                    
                    if (classes.contains(s)) {
                        // ASM Code
                        ClassReader reader = new ClassReader(bytes);
                        ClassWriter writer = new ClassWriter(reader, 0);
                        ClassPrinter visitor = new ClassPrinter(writer, classes);
                        reader.accept(visitor, 0);
                        return writer.toByteArray();
                    }

                    return null;
                }
            });
    }
}
