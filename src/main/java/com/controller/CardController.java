package com.controller;

import com.entity.Card;
import com.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/api"})
//@RequestMapping({"/card-portal/api"})
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public int create(@RequestBody Card card){
        return cardService.insert(card);
    }

    @GetMapping(path = {"/{name}"})
    public Card findOne(@PathVariable("name") String name){
        return cardService.findByName(name);
    }

    @PutMapping
    public int update(@RequestBody Card card){
        return cardService.update(card);
    }

    @DeleteMapping(path ={"/{name}"})
    public int delete(@PathVariable("name") String name) {
        return cardService.deleteByName(name);
    }

    @GetMapping
    public List<Card> findAll(){
        return cardService.findAll();
    }

}
