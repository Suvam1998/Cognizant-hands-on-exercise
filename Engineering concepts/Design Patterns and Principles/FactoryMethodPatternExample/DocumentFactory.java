package FactoryMethodPatternExample;

/**
 * Creator — declares the factory method createDocument().
 * Subclasses decide which concrete Document to instantiate.
 */
public abstract class DocumentFactory {

    /** The factory method. */
    public abstract Document createDocument();

    /** Template operation that uses the product created by the subclass. */
    public Document newDocument() {
        Document doc = createDocument();
        System.out.println("Factory produced a " + doc.getType() + " document.");
        return doc;
    }
}

/** Concrete creators — one per product type. */
class WordDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new WordDocument(); }
}

class PdfDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new PdfDocument(); }
}

class ExcelDocumentFactory extends DocumentFactory {
    public Document createDocument() { return new ExcelDocument(); }
}
