package com.ifmo.epampractice.service;

import java.util.List;

public interface IDAO<T> {

    T addObject(T object);

    List<T> getAll();

    T getById(int id);

    void updateByObject(T object);

    void removeById(int id);
}
