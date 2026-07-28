# Exercise 5: Decorator Pattern

**Intent:** Attach additional responsibilities to an object dynamically.
Decorators provide a flexible alternative to subclassing for extending behavior.

**Participants**
- Component: `Notifier` (interface, `send()`).
- Concrete component: `EmailNotifier`.
- Base decorator: `NotifierDecorator` (implements `Notifier`, wraps a `Notifier`).
- Concrete decorators: `SMSNotifierDecorator`, `SlackNotifierDecorator` — each
  calls the wrapped notifier, then adds its own channel.

Channels are composed at runtime, e.g.
`new SlackNotifierDecorator(new SMSNotifierDecorator(new EmailNotifier()))`
sends over Email + SMS + Slack.

**Run**
```bash
javac DecoratorPatternExample/*.java
java  DecoratorPatternExample.DecoratorTest
```
