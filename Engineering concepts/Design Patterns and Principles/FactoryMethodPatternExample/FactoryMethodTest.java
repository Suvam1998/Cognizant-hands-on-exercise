package FactoryMethodPatternExample;

/**
 * Demonstrates creating different document types via their factories,
 * without the client ever calling `new` on a concrete document.
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        DocumentFactory[] factories = {
                new WordDocumentFactory(),
                new PdfDocumentFactory(),
                new ExcelDocumentFactory()
        };

        for (DocumentFactory factory : factories) {
            Document doc = factory.newDocument();
            doc.open();
            System.out.println();
        }
    }
}
