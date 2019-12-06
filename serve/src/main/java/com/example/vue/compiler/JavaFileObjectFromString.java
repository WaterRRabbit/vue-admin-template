package com.example.vue.compiler;

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;

public class JavaFileObjectFromString extends SimpleJavaFileObject {

    private final String code;

    public JavaFileObjectFromString(String className,  String code) {
        super(URI.create(className.replaceAll("\\.", "/") + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return this.code;
    }

}
