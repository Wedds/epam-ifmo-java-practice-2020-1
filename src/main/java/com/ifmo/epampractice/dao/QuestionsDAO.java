package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Questions;
import com.ifmo.epampractice.service.DatabaseService;

import java.util.List;

public class QuestionsDAO extends DatabaseService  {

    //create
    void add(Questions questions){}

    //read
    List<Questions> getAll(){return null;}

    Questions getById(int id){return null;}

    Questions getByTestId(int testId){return null;}

    //update
    void update (Questions questions){}

    //delete
    void remove(Questions questions){}

}
