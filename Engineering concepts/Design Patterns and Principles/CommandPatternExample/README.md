# Exercise 9: Command Pattern

**Intent:** Encapsulate a request as an object, letting you parameterize the
invoker with different requests and decouple the sender from the receiver.

**Participants**
- Command: `Command` (`execute()`).
- Concrete commands: `LightOnCommand`, `LightOffCommand` — bind an action to a
  receiver.
- Receiver: `Light` (`on()`, `off()`).
- Invoker: `RemoteControl` — holds a command and calls `execute()` on button
  press, without knowing the receiver.

**Run**
```bash
javac CommandPatternExample/*.java
java  CommandPatternExample.CommandTest
```
