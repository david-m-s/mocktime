Mock system time with jmockit

Will be mocking System.currentTimeMillis. Since this is a small method and gets called often, the VM will inline it. To prevent this:
                        -XX:CompileCommand=dontinline,java/lang/System.currentTimeMillis

To make jmockit suport java 9+, some of its internal magic wass changed, so we need to tell the VM we start with a MockSystem instance. See https://stackoverflow.com/questions/54453727/java11-and-jmockit-1-43-java-lang-unsatisfiedlinkerror-happened-when-jmockit-moc
                        -Dfakes=org.dms.mocktime.MockSystem

The regular way to start jmockit. See https://jmockit.github.io/tutorial/Introduction.html#runningTests
                        -javaagent:${settings.localRepository}/org/jmockit/jmockit/${jmockit.version}/jmockit-${jmockit.version}.jar
