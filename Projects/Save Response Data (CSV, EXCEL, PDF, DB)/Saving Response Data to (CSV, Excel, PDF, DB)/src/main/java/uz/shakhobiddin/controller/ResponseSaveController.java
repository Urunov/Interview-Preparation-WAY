package uz.shakhobiddin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.shakhobiddin.service.ResponseSaveService;

@RestController
@RequestMapping("/save")
public class ResponseSaveController {
    private final ResponseSaveService responseSaveService;

    public ResponseSaveController(ResponseSaveService responseSaveService) {
        this.responseSaveService = responseSaveService;
    }

    @GetMapping("/fetch-and-save")
    public ResponseEntity<String> fetchAndSave(@RequestParam String url) {
        responseSaveService.fetchAndSaveData(url);
        return ResponseEntity.ok("Data saved successfully!");
    }

}
