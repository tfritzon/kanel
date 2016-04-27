all:
	javac -cp asm-5.0.3.jar:. Agent.java ClassPrinter.java Test.java
	jar cvfm test-agent.jar MANIFEST.MF *.class

run: all
	java -cp test-agent.jar:asm-5.0.3.jar:. -javaagent:test-agent.jar=Test Test
