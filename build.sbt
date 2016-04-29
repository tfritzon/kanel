enablePlugins(JniPlugin)

jniNativeCompiler := "gcc"
jniGccFlags ++= Seq("-g")
jniUseCpp11 := false;

jniNativeClasses := Seq(
  "kanel.Heap"
)
jniLibraryName := "libHeap"

jniLibSuffix :=
  (System.getProperty("os.name").toLowerCase.replace(' ', '_').replace('.', '_') match {
    case os if os.contains("mac")   => "dylib"
    case os if os.contains("win")   => "dll"
    case _  => "so"
  })

TaskKey[Unit]("check") := {
  val files = jniSourceFiles.value
  require(files.length == 1, "missing source files")
  require(files.head.getName.endsWith("heap.c"))
  require((jniBinPath.value / s"lib${jniLibraryName.value}.${jniLibSuffix.value}").exists)
}

lazy val root = (project in file(".")).settings(name := "kanel",
     	      					version := "0.1")
