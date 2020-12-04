# messages

Formatting messages with unique code identifier and parameter types

Features:

* Use typed message identification with enums
* Supports parameter with descriptions
* Autogenerate typed message documentation
* Logback support
  * PIDdefiner
  * ThreadIdConverter
  * LoggerContextNameDefiner
## Compile

```
$ mvn clean package
```

## Usage

Please, see test cases what you can do with type messages and machine readable log formatting

see src/main/java/com/bee42/messages/exception/ErrorMessages.java

### Define your Messages types

```
public enum ErrorMessages implements MessageType {

  GENERAL_ERROR("SYSTEM_001", "A general error occurred.", "Nicht weiter spezifizierter technischer Fehler."),
  NOT_FOUND_ERROR("SYSTEM_002", "The requested resource {} does not exist.",
      "Diese Fehlermeldung wird ausgegeben, wenn versucht wird auf eine Resource zuzugreifen,"
          + " die nicht existiert.",
      new Parameter[] { new Parameter("resource", "Name der Ressource") });
...
```
### Log Exception

```
  LOG.error(new MessageException(ErrorMessages.GENERAL_ERROR));
```

```
{ "date": "2020-12-04T18:50:35.395+0100", "version": "1.2", "address": "-", "pid": "82150", "thread": "main", "system": "MESSAGE", "correlationID": "-", "requestID": "-", "level": "ERROR", "logger": "c.b.m.MessageLoggingTest", "message": "SYSTEM_001: A general error occurred.", "stacktrace": "com.bee42.messages.exception.MessageException: SYSTEM_001: A general error occurred.
        at com.bee42.messages.MessageLoggingTest.testFormat(MessageLoggingTest.java:46)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
        at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
        at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
        at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
        at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
        at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
        at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
        at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
        at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
        at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
        at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
        at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
        at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
        at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
        at org.apache.maven.surefire.junit4.JUnit4Provider.execute(JUnit4Provider.java:364)
        at org.apache.maven.surefire.junit4.JUnit4Provider.executeWithRerun(JUnit4Provider.java:272)
        at org.apache.maven.surefire.junit4.JUnit4Provider.executeTestSet(JUnit4Provider.java:237)
        at org.apache.maven.surefire.junit4.JUnit4Provider.invoke(JUnit4Provider.java:158)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:428)
        at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:162)
        at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:562)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:548)
"}
```
### Log Exception with parameter

```
  LOG.error(new MessageException(ErrorMessages.NOT_FOUND_ERROR, "test1"));
```

```
{ "date": "2020-12-04T18:50:35.396+0100", "version": "1.2", "address": "-", "pid": "82150", "thread": "main", "system": "MESSAGE", "correlationID": "-", "requestID": "-", "level": "ERROR", "logger": "c.b.m.MessageLoggingTest", "message": "SYSTEM_002: The requested resource [test1] does not exist.", "stacktrace": "com.bee42.messages.exception.MessageException: SYSTEM_002: The requested resource test1 does not exist.
        at com.bee42.messages.MessageLoggingTest.testFormat(MessageLoggingTest.java:47)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:59)
        at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
        at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:56)
        at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
        at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
        at org.junit.runners.BlockJUnit4ClassRunner$1.evaluate(BlockJUnit4ClassRunner.java:100)
        at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:366)
        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:103)
        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:63)
        at org.junit.runners.ParentRunner$4.run(ParentRunner.java:331)
        at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:79)
        at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:329)
        at org.junit.runners.ParentRunner.access$100(ParentRunner.java:66)
        at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:293)
        at org.junit.runners.ParentRunner$3.evaluate(ParentRunner.java:306)
        at org.junit.runners.ParentRunner.run(ParentRunner.java:413)
        at org.apache.maven.surefire.junit4.JUnit4Provider.execute(JUnit4Provider.java:364)
        at org.apache.maven.surefire.junit4.JUnit4Provider.executeWithRerun(JUnit4Provider.java:272)
        at org.apache.maven.surefire.junit4.JUnit4Provider.executeTestSet(JUnit4Provider.java:237)
        at org.apache.maven.surefire.junit4.JUnit4Provider.invoke(JUnit4Provider.java:158)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:428)
        at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:162)
        at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:562)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:548)
"}
```

## Todo

* add more valuable documentation and examples
  * Standard HTTP Error codes
* Formatting Error message documentation as asciidoc table
* Add a JSON parameter log formatting
  * add a block with parameter key value pairs
* Use modern java features
* Add annotation base logger injection
* Add the message lib to maven central
* Use other logger framework

Regards
<peter.rossbach@bee42.com> (Pathfinder)