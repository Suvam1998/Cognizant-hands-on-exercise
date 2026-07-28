# Exercise 2: Factory Method Pattern

**Intent:** Define an interface for creating an object, but let subclasses decide
which concrete class to instantiate — the client depends only on the product
interface, never on `new WordDocument()` etc.

**Participants**
- Product: `Document` (interface).
- Concrete products: `WordDocument`, `PdfDocument`, `ExcelDocument`.
- Creator: `DocumentFactory` (abstract, declares `createDocument()`).
- Concrete creators: `WordDocumentFactory`, `PdfDocumentFactory`,
  `ExcelDocumentFactory`.

**Run**
```bash
javac FactoryMethodPatternExample/*.java
java  FactoryMethodPatternExample.FactoryMethodTest
```
