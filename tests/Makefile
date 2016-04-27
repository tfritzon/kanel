JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_75.jdk/Contents/Home

all: UUIDString.so

UUIDString.so: UUIDString.h UUIDString.c
	gcc -c UUIDString.c -I${JAVA_HOME}/include -I${JAVA_HOME}/include/darwin
	gcc -dynamiclib -o libUUIDString.jnilib UUIDString.o

UUIDString.h: UUIDString.class
	javah UUIDString

UUIDString.class:
	javac SimpleArray.java

clean:
	rm -f UUIDString.h *.jnilib *.class *.so *~ *.o
