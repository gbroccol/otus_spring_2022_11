package ru.otus.spring.service.impl;

import java.util.List;

public interface ServiceCRUD<T> {

   void add(T element);
   T findById(Long id);
   List<T> findAll();
   void deleteById(Long id);

}
