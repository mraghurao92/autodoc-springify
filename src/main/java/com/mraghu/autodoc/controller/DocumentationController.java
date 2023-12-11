package com.mraghu.autodoc.controller;

import com.mraghu.autodoc.model.CustomRequestContext;
import com.mraghu.autodoc.service.CodeDocumentationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/documentation")
public class DocumentationController {

    private final CodeDocumentationService codeDocumentationService;

    private final CustomRequestContext customRequestContext;

    public DocumentationController(CodeDocumentationService codeDocumentationService, CustomRequestContext customRequestContext) {
        this.codeDocumentationService = codeDocumentationService;
        this.customRequestContext = customRequestContext;
    }

    /**
     * Generates documentation for a given file path.
     *
     * @param filePath the path of the file to generate documentation for
     * @return the response entity containing the generated documentation
     */

    @PostMapping("/generate")
    public ResponseEntity<String> generateDocumentation(@RequestBody String filePath) {
        try {
            String documentation = codeDocumentationService.generateDocumentation(filePath);
            return ResponseEntity.ok(documentation);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error generating documentation: " + e.getMessage());
        }
    }

    /**
     * Retrieves the code documentation by invoking the "printHello" method from the {@link codeDocumentationService}.
     *
     * @return a {@link ResponseEntity} object containing the code documentation fetched from the service
     */
    @GetMapping("/test")
    public ResponseEntity<String> test() {

        String documentation = codeDocumentationService.printHello();

        return ResponseEntity.ok(documentation);
    }
}
