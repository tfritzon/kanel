#include "STn.h"

JNIEXPORT jfloat JNICALL
Java_STn_foo(JNIEnv * env, jclass class, jfloat a, jfloat b)
{
  return (a+b)/a;
}

