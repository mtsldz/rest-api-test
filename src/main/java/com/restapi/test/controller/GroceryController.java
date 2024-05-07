package com.restapi.test.controller;

import com.restapi.test.exception.AlreadyExistsExceptionHandle;
import com.restapi.test.exception.NotFoundExceptionHandle;
import com.restapi.test.model.Grocery;
import com.restapi.test.service.GroceryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping
@AllArgsConstructor
public class GroceryController {

    private GroceryService groceryService;


    @GetMapping("/allGrocery")
    public ResponseEntity<List<Grocery>> getAllDetails() {
        return groceryService.getAllDetails();
    }

    @GetMapping("/allGrocery/{name}")
    public ResponseEntity<Grocery> getByName(@PathVariable String name) {
        return groceryService.getByName(name);
    }

    @PostMapping("/add")
    public ResponseEntity<Grocery> addToGrocery(@RequestBody Grocery grocery) {
        return groceryService.addToGrocery(grocery);
    }


    @ExceptionHandler(NotFoundExceptionHandle.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundExceptionHandle ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsExceptionHandle.class)
    public ResponseEntity<String> handleAlreadyExistException(AlreadyExistsExceptionHandle ex) {
        return new ResponseEntity<>(ex.getMessage(), CONFLICT);
    }

}
