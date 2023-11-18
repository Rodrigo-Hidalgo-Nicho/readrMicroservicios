package com.service.api.gateway.api.rest.language;

import com.service.api.gateway.services.language.LanguageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/gateway/languages", produces = "application/json")
public class LanguageController {
    private final LanguageService languageService;


    public LanguageController(LanguageService languageService) { this.languageService = languageService; }

    @GetMapping
    public ResponseEntity<Page<Object>> getAllLanguages(Pageable pageable){
        return ResponseEntity.ok(this.languageService.getAllLanguages(pageable));
    }
}
