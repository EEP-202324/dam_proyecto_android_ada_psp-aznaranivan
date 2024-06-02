package com.example.autoescuela;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

interface AutoescuelaRepository extends CrudRepository<Autoescuela, Long>, PagingAndSortingRepository<Autoescuela, Long> {
    Autoescuela findByIdAndOwner(Long id, String owner);

    Page<Autoescuela> findByOwner(String owner, PageRequest pageRequest);
}