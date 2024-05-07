package com.restapi.test.service;

import com.restapi.test.exception.AlreadyExistsExceptionHandle;
import com.restapi.test.exception.NotFoundExceptionHandle;
import com.restapi.test.model.Grocery;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GroceryService {

    private List<Grocery> groceries;


    // GET all
    public ResponseEntity<List<Grocery>> getAllDetails() {
        return new ResponseEntity<>(groceries, HttpStatus.OK);
    }

    // GET with given id
    public ResponseEntity<Grocery> getByName(String name) {
        Grocery grocery = getByNameAndCheck(name);
        return new ResponseEntity<>(grocery, HttpStatus.OK);
    }

    // throw exception if exists author or title fields, else add new grocery
    public ResponseEntity<Grocery> addToGrocery(Grocery grocery) {

        checkExistsId(grocery);
        checkExistsName(grocery);

        groceries.add(grocery);
        return new ResponseEntity<>(grocery, HttpStatus.CREATED);
    }

    //
    private Grocery getByNameAndCheck(String name) {
        return getDetailByName(name)
                .orElseThrow(() -> new NotFoundExceptionHandle("Not found with name: " + name));
    }

    private Optional<Grocery> getById(String id) {
        return groceries.stream()
                .filter(t -> id.equals(String.valueOf(t.getId())))
                .findFirst();
    }

    private void checkExistsId(Grocery grocery){
        Optional<Grocery> groceryId = getById(String.valueOf(grocery.getId()));
        if (groceryId.isPresent()){
            throw new AlreadyExistsExceptionHandle("Already exists with id: " + grocery.getId());
        }
    }

    private Optional<Grocery> getDetailByName(String name) {
        return groceries.stream()
                .filter(t -> name.equals(t.getName()))
                .findFirst();
    }

    private void checkExistsName(Grocery grocery){
        Optional<Grocery> name = getDetailByName(grocery.getName());
        if (name.isPresent()){
            throw new AlreadyExistsExceptionHandle("Already exists with name: " + grocery.getName());
        }
    }


}
