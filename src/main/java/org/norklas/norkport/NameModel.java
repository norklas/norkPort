package org.norklas.norkport;

import io.jstach.jstache.JStache;

@JStache(path = "index")
public class NameModel {
    private String name;

    public NameModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
