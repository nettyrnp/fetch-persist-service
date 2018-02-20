package com.service;

import com.entity.Card;
import com.entity.Row;

import java.util.List;

public interface CardService {

    int insert(Card card);

    int deleteByName(String name);

    List<Card> findAll();

    Card findByName(String name);

    int update(Card card);

}
