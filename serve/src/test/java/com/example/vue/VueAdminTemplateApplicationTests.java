package com.example.vue;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

@SpringBootTest
public class VueAdminTemplateApplicationTests {

    @Test
    public void contextLoads() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        String rootPath = System.getProperty("user.dir");
        String path = rootPath.concat("/src/test/tmp");

        System.out.println(rootPath);
    }

}
