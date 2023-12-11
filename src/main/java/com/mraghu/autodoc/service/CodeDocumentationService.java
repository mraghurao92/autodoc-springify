package com.mraghu.autodoc.service;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.mraghu.autodoc.model.CustomRequestContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class CodeDocumentationService {

    private final CustomRequestContext customRequestContext;

    /**
     * Constructs an instance of CodeDocumentationService with the specified CustomRequestContext.
     *
     * @param customRequestContext the CustomRequestContext object to be used by the CodeDocumentationService.
     */
    public CodeDocumentationService(CustomRequestContext customRequestContext) {
        this.customRequestContext = customRequestContext;
    }


    /**
     * Generates documentation for a Java source file.
     *
     * @param filePath The path of the Java source file to generate documentation for.
     * @return The generated documentation as a string.
     * @throws IOException If an I/O error occurs while parsing the file.
     */
    public String generateDocumentation(String filePath) throws IOException, com.github.javaparser.ParseProblemException {
        final File file = new File(filePath);
        JavaParser javaParser = new JavaParser();
        ParseResult<CompilationUnit> cu = javaParser.parse(file);
        CompilationUnit compilationUnit = cu.getResult().orElse(null);

        // Extract comments from methods
        if (compilationUnit == null) {
            throw new com.github.javaparser.ParseProblemException(cu.getProblems());
        }
        List<String> methodComments = compilationUnit.findAll(MethodDeclaration.class)
                .stream()
                .map(MethodDeclaration::getComment)
                .filter(Objects::nonNull)
                .map(content -> content.get().getContent().trim())
                .toList();

        // Format the comments into documentation (you can customize this part)
        StringBuilder documentation = new StringBuilder();
        documentation.append("# API Documentation\n\n");

        for (int i = 0; i < methodComments.size(); i++) {
            documentation.append("## Method ").append(i + 1).append("\n\n");
            documentation.append(methodComments.get(i)).append("\n\n");
        }

        return documentation.toString();
    }

    /**
     * Returns the string "Hello".
     *
     * @return the string "Hello"
     */
    public String printHello() {
        return customRequestContext.getCustomHeader();
    }

}
