package com.ifmo.epampractice.service;

import java.util.List;

public interface IDAO<T> {

    void addObject(T object);

    List<T> getAll();

    T getById(int id);

    void updateByObject(T object);

    void removeByObject(T object);

    void removeById(int id);
}
