package com.example.annotationprocessorlib;

import com.example.annotationlib.TestSelfAnnotation;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

//该注解是为了在编译时为该module生成声明文件
@AutoService(Processor.class)
public class MyAnnotationProcessor extends AbstractProcessor {

    private Map<String, String> getOptions;

    private Messager getMessagers;

    private Filer getFilers;

    private Elements elementUtils;

    private Types typeUtils;

    private SourceVersion getSourceVersion;

    private Locale getLocale;

    /**
     * @param set
     * @param roundEnvironment
     * @return
     */
    private static final String RANDOM_SUFFIX = "$$BindView";


    private HashMap<String, HashMap<String, String>> entryKey = new HashMap<>();

    private HashMap<String, Element> elementIndexMap = new HashMap<>();

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(TestSelfAnnotation.class);
        for (final Element element : elementsAnnotatedWith)
            if (element.getKind() == ElementKind.FIELD) {

                TestSelfAnnotation annotation = element.getAnnotation(TestSelfAnnotation.class);
                TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
                ClassName className = ClassName.get(enclosingElement);

                if (entryKey.containsKey(className.simpleName())) {
                    HashMap<String, String> stringStringHashMap = entryKey.get(className.simpleName());
                    stringStringHashMap.put(element.getSimpleName().toString(), annotation.value() + "");
//                    entryKey.put(element.getEnclosingElement().getSimpleName().toString(), stringStringHashMap);
                } else {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put(element.getSimpleName().toString(), annotation.value() + "");
                    elementIndexMap.put(className.simpleName(), element);
                    entryKey.put(className.simpleName(), hashMap);
                }
            }

        writeClazz(entryKey, elementIndexMap);
        return false;
    }


    private void writeClazz(HashMap<String, HashMap<String, String>> entryKey, HashMap<String, Element> elementIndexMap) {

        for (Map.Entry<String, HashMap<String, String>> map : entryKey.entrySet()) {

            String key = map.getKey();

            HashMap<String, String> value = map.getValue();

            Element element = elementIndexMap.get(key);

            TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
            ClassName className = ClassName.get(enclosingElement);
            MethodSpec.Builder builder = MethodSpec.constructorBuilder()
                    .addModifiers(Modifier.PUBLIC)
                    .addParameter(className, "activity");
            if (value.size() > 0) {
                for (HashMap.Entry<String, String> entry : value.entrySet()) {
                    CodeBlock of = CodeBlock.of("$N." + entry.getKey() + "=" + "$N." + "findViewById("
                            + entry.getValue() + ");\n", "activity", "activity");

                    builder.addCode(of);

                }
            }
            TypeSpec build = TypeSpec.classBuilder(getClazzName(element) + RANDOM_SUFFIX)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(builder.build())
                    .build();
            try {
                JavaFile javaFile = JavaFile.builder(getPackageName(element), build).build();
                javaFile.writeTo(getFilers);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }


    private String getClazzName(Element element) {

        Element enclosingElement = element.getEnclosingElement();

        return enclosingElement.getSimpleName().toString();
    }


    private String getPackageName(Element element) {

        PackageElement packageOf = elementUtils.getPackageOf(element);

        return packageOf.getQualifiedName().toString();
    }


    /**
     * @param processingEnvironment
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        getMessagers = processingEnvironment.getMessager();
        getFilers = processingEnvironment.getFiler();
        elementUtils = processingEnvironment.getElementUtils();
        typeUtils = processingEnvironment.getTypeUtils();

    }

    /**
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {

        Set<String> setClazzName = new LinkedHashSet<>();
        setClazzName.add(TestSelfAnnotation.class.getName());
        return setClazzName;
    }


    /**
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
//        return super.getSupportedSourceVersion();

        return SourceVersion.latestSupported();
    }

}
