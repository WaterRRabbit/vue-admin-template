package com.example.vue;


import org.junit.Test;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collections;

public class VueAdminTemplateApplicationTests {

    // https://www.jianshu.com/p/44395ef6406f

    @Test
    public void contextLoads() throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        String rootPath = System.getProperty("user.dir");
        String path = rootPath.concat("/src/test/tmp/");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String sourceCode =
                "class Solution {\n" +
                        "    public int f1() {\n" +
                "        return 1;\n" +
                "    }\n" +
                "}\n" +
                "public class Hello {\n" +
                "    " +
                "public static void main(String[] args) {\n" +
                "        " +
                "System.out.println(new Solution().f());\n" +
                "    " +
                "}\n" +
                "}";
        DiagnosticCollector<JavaFileObject> diagnosticListeners = new DiagnosticCollector<>();
        JavaFileObject fileObject = new JavaFileObjectFromString("Hello",
                sourceCode);
        JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnosticListeners,
                Arrays.asList("-d", path), null,
                Collections.singletonList(fileObject));
        boolean compileSuccess = task.call();
        if (!compileSuccess) {
            //输出诊断信息
            for(Diagnostic<? extends JavaFileObject> diagnostic : diagnosticListeners.getDiagnostics()) {
                //可以在此处自定义编译诊(错误)断信息的输出格式
                System.out.format("Error %s", diagnostic);
            }
            System.out.println("编译失败");
        } else {
            //动态执行 (反射执行)
            System.out.println("编译成功");
            URL[] urls = new URL[] {new URL("file:/" + path)};
            URLClassLoader classLoader = new URLClassLoader(urls);
            Class dynamicClass = classLoader.loadClass("Hello");
            @SuppressWarnings("unchecked")
            Method method = dynamicClass.getDeclaredMethod("main", String[].class);
            String[] arguments = {null};
            try {
                method.invoke(dynamicClass, (Object) arguments);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private static class JavaFileObjectFromString extends SimpleJavaFileObject {

        final String code;

        public JavaFileObjectFromString(String className,  String code) {
            super(URI.create(className.replaceAll("\\.", "/") + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return this.code;
        }
    }
}
