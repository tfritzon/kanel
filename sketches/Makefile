JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_75.jdk/Contents/Home

all: UUIDString.jnilib Heap.jnilib libSTn.jnilib

libSTn.jnilib: libSTn.o
	gcc -dynamiclib -o libSTn.jnilib libSTn.o

libSTn.o: libSTn.c STn.h
	gcc -Wall -c libSTn.c -I${JAVA_HOME}/include -I${JAVA_HOME}/include/darwin

STn.h: STn.class
	javah STn

STn.class:
	javac STn.java

Heap.jnilib: Heap.h Heap.c
	gcc -Wall -c Heap.c -I${JAVA_HOME}/include -I${JAVA_HOME}/include/darwin
	gcc -dynamiclib -o libHeap.jnilib Heap.o

Heap.h: Heap.class
	javah Heap

Heap.class: Heap.java
	javac Heap.java

UUIDString.jnilib: UUIDString.h UUIDString.c
	gcc -c UUIDString.c -I${JAVA_HOME}/include -I${JAVA_HOME}/include/darwin
	gcc -dynamiclib -o libUUIDString.jnilib UUIDString.o

UUIDString.h: UUIDString.class
	javah UUIDString

UUIDString.class:
	javac SimpleArray.java

clean:
	rm -f *.jnilib *.class *.so *~ *.o *.log *.h.gch UUIDString.h Heap.h STn.h
