package FactoryMethodPatternExample;

/** Product interface — common type for all documents. */
public interface Document {
    void open();
    String getType();
}
