package com.example.annotationprocessorlib;

import com.example.annotationlib.TestSelfAnnotation;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
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
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

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

    //PackageElement TypeElement VariableElement ExecutableElement

    /**
     * @param set
     * @param roundEnvironment
     * @return
     */
    private static final String RANDOM_SUFFIX = "$$BindView";


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(TestSelfAnnotation.class);

        for (final Element element : elementsAnnotatedWith) {

            if (element.getKind() == ElementKind.FIELD) {

                try {

                    TestSelfAnnotation annotation = element.getAnnotation(TestSelfAnnotation.class);

                    TypeElement enclosingElement = (TypeElement) element.getEnclosingElement();
                    ClassName className = ClassName.get(enclosingElement);

                    MethodSpec.Builder builder = MethodSpec.constructorBuilder()
                            .addModifiers(Modifier.PUBLIC)
                            .addParameter(className, "activity")
                            .addCode("$N." + element.getSimpleName() + "=" + "$N." + "findViewById("
                                    + annotation.value() + ");", "activity", "activity");

                    TypeSpec build = TypeSpec.classBuilder(getClazzName(element) + RANDOM_SUFFIX)
                            .addModifiers(Modifier.PUBLIC)
                            .addMethod(builder.build())
                            .build();

                    JavaFile javaFile = JavaFile.builder(getPackageName(element), build).build();
                    javaFile.writeTo(getFilers);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }


        return false;
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


    @Override
    public Set<String> getSupportedOptions() {
        return super.getSupportedOptions();
    }

    /**
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {

        Set<String> setClazzName = new LinkedHashSet<>();

        System.out.println("   " + TestSelfAnnotation.class.getCanonicalName());
        setClazzName.add(TestSelfAnnotation.class.getName());

        return setClazzName;
    }


    /**
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
//        return super.getSupportedSourceVersion();

        return SourceVersion.RELEASE_7;
    }

}
