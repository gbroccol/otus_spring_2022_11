package ru.otus.spring.service.impl;

import java.util.List;

public interface ServiceCRUD<T> {
   List<T> findAll();
}
