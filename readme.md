## Mock system time with jmockit

When running there must be 3 command line configurations (from a junit run in the IDE, from maven, etc). The order of the configurations does not matter.

### Prevent inlining
The strategy is based on mocking System.currentTimeMillis. Since this is a small method and gets called often, the VM will inline it, substituting our mocked method. To prevent this:

    -XX:CompileCommand=quiet
    -XX:CompileCommand=dontinline,java/lang/System.currentTimeMillis
    
The first line prevents the VM from the CompileCommand in the second line, which causes a WARNING in the logs.

### Compatibility with java 9+
To make jmockit suport java 9+, some of its internal workings were changed, so we need to tell the VM we start with a MockSystem instance. See https://stackoverflow.com/questions/54453727/java11-and-jmockit-1-43-java-lang-unsatisfiedlinkerror-happened-when-jmockit-moc

    -Dfakes=org.dms.mocktime.MockSystem

### Jmockit tooling
The regular way to start jmockit. See https://jmockit.github.io/tutorial/Introduction.html#runningTests

    -javaagent:${settings.localRepository}/org/jmockit/jmockit/${jmockit.version}/jmockit-${jmockit.version}.jar

**Note on maven**: since surefire 2.20 the inlining option will cause a WARNING. This is why we are muting the CompileCommand log. See https://issues.apache.org/jira/browse/SUREFIRE-1359 

**Running inside eclipse**: The command line options can be forced by configuring Java->Installed JREs->Edit..., And inside, put the 3 configurations (with maven placeholders resolved!) in the "Default VM arguments" dialog.

**IntelliJ**: TBD
