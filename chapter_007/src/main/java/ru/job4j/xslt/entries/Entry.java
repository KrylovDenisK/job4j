package ru.job4j.xslt.entries;

import javax.xml.bind.annotation.XmlElement;

public class Entry {
    @XmlElement
    private int field;
    public Entry setField(int field) {
        this.field = field;
        return this;
    }
}
