#include <stdlib.h>
#include <stdio.h>
#include "UUIDString.h"

JNIEXPORT jobject JNICALL
Java_UUIDString_alloc(JNIEnv * env, jobject this, jint n)
{
  void * s = calloc(n, 2);
  printf("%d: %p\n", n, s);
  return (jobject)s;
}
