package com.service;

import com.entity.Card;
import com.repository.JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
//    private CardRepository repository;
    private JdbcRepository repository;

    @Override
    public int insert(Card card) {
        return repository.insert(card);
    }

    @Override
    public int deleteByName(String name) {
        Card card = findByName(name);
        if(card != null){
            repository.deleteRowsByName(card.getName());
            return 1;
        }
        return 0;
    }

    @Override
    public List<Card> findAll() {
        return repository.findAll();
    }

    @Override
    public Card findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public int update(Card card) {
        return repository.update(card);
    }

}
