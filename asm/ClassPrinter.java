import org.objectweb.asm.*;
import java.util.*;

public class ClassPrinter extends ClassVisitor {
    List<String> types; 
    List<String> classes; 
    
    public ClassPrinter(ClassWriter writer, List<String> classes) {
        super(Opcodes.ASM4, writer);
        this.classes = classes;
        this.types = new ArrayList(classes.size());
        for (String c : classes) {
            types.add("L" + c + ";");
        }
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println(" " + name + desc);
        return new OffheapMethodVisitor(Opcodes.ASM4, super.visitMethod(access, name, desc, signature, exceptions), types, classes);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        if (types.contains(desc)) {
            System.out.println(name + " ~~> long");   
        } else {
            System.out.println(name + " unmodified");   
        }
        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public void visitEnd() {
        System.out.println("}");
        super.visitEnd();
    }
}

class OffheapMethodVisitor extends MethodVisitor {
    List<String> types; 
    List<String> classes; 

    public OffheapMethodVisitor(int api, MethodVisitor mv, List<String> types, List<String> classes) {
        super(api, mv);
        this.types = types;
        this.classes = classes;
    }

    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        if (types.contains(desc)) {
            System.out.println("visitFieldInsn(" + opcode + "," + owner + "," + name + "," + desc + " ~~> long)");
        } else {
            System.out.println("visitFieldInsn(" + opcode + "," + owner + "," + name + "," + desc + ")");
        }
        super.visitFieldInsn(opcode, owner, name, desc);
    }

    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
        if (types.contains(desc)) {
            System.out.println("! visitLocalVariable(" + name + "," + desc + "," + signature + "," + start + "," + end + "," + index + ")");
        } else {
            System.out.println("visitLocalVariable(" + name + "," + desc + "," + signature + "," + start + "," + end + "," + index + ")");
        }
        super.visitLocalVariable(name, desc, signature, start, end, index);
    }

    public void visitMethodInsn(int opcode, String owner, String name, String desc) {
        if (classes.contains(owner)) {
            System.out.println("! visitMethodInsn(" + opcode + "," + owner + "," + name + "," + desc + ")");
        } else {
            System.out.println("visitMethodInsn(" + opcode + "," + owner + "," + name + "," + desc + ")");
        }
        super.visitMethodInsn(opcode, owner, name, desc);
    }
}
