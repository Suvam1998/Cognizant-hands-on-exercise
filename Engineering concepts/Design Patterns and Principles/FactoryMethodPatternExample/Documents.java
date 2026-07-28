package FactoryMethodPatternExample;

/** Concrete products. Grouped in one file for brevity. */
class WordDocument implements Document {
    public void open() { System.out.println("Opening a Word document (.docx)"); }
    public String getType() { return "Word"; }
}

class PdfDocument implements Document {
    public void open() { System.out.println("Opening a PDF document (.pdf)"); }
    public String getType() { return "PDF"; }
}

class ExcelDocument implements Document {
    public void open() { System.out.println("Opening an Excel document (.xlsx)"); }
    public String getType() { return "Excel"; }
}
