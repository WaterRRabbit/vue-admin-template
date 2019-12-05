package com.example.vue;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
public class VueAdminTemplateApplicationTests {

    // https://www.jianshu.com/p/44395ef6406f

    @Test
    public void contextLoads() throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        String rootPath = System.getProperty("user.dir");
        String path = rootPath.concat("/src/test/tmp");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String sourceCode = "package com.test;" +
                "public class Hello {" +
                "public static void main(String[] args) {" +
                "System.out.println(\"Hello World !\");" +
                "}" +
                "}";
        JavaFileObject fileObject = new JavaFileObjectFromString("com.test.Hello",
                sourceCode);
        JavaCompiler.CompilationTask task = compiler.getTask(null, null, null,
                Arrays.asList("-d", file.getAbsolutePath()), null,
                Collections.singletonList(fileObject));
        boolean compileSuccess = task.call();
        if (!compileSuccess) {
            System.out.println("编译失败");
        } else {
            System.out.println(file.getAbsolutePath());
            //动态执行 (反射执行)
            System.out.println("编译成功");
            URL[] urls = new URL[] {new URL("file:/" + file.getAbsolutePath())};
            URLClassLoader classLoader = new URLClassLoader(urls);
            Class dynamicClass = classLoader.loadClass("com.test.Hello");
            Method method = dynamicClass.getDeclaredMethod("main", String[].class);
            String[] arguments = {null};
            method.invoke(dynamicClass, arguments);
        }
    }


    private static class JavaFileObjectFromString extends SimpleJavaFileObject {

        String code;

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
