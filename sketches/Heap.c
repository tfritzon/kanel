#include <stdio.h>
#include "Heap.h"

JNIEXPORT jlong JNICALL
Java_Heap_a_1to_1l(JNIEnv * env, jclass class, jobject o)
{
  jobject ref = (*env)->NewGlobalRef(env, o);
  return (jlong)ref;
}  

JNIEXPORT jobject JNICALL
Java_Heap_l_1to_1a(JNIEnv * env, jclass class, jlong o)
{
  return (jobject)o;
}
