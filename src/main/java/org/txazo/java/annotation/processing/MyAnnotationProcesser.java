//package org.txazo.java.annotation.processing;
//
//import javax.annotation.processing.*;
//import javax.lang.model.SourceVersion;
//import javax.lang.model.element.Element;
//import javax.lang.model.element.TypeElement;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * 注解编译处理
// */
//public class MyAnnotationProcesser extends AbstractProcessor {
//
//    @Override
//    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        for (TypeElement e : annotations) {
//            System.out.println(String.format("Annotation: %s", e.toString()));
//        }
//
//        for (Element e : roundEnv.getRootElements()) {
//            System.out.println(String.format("Element: %s", e.toString()));
//        }
//
//        System.out.println("MyAnnotationProcesser process");
//        return true;
//    }
//
//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        Set<String> types = new HashSet<>();
//        types.add(Data.class.getCanonicalName());
//        return types;
//    }
//
//    @Override
//    public SourceVersion getSupportedSourceVersion() {
//        return SourceVersion.latestSupported();
//    }
//
//}
