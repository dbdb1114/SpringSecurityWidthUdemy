package com.eazybytes.repository;


import com.eazybytes.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CrudRepository는 SpringDataJPA framework의 내장 인터페이스로
 * 모든 CRUD에 자동적으로 코드를 생성하게 도와준다.
 *  CrudRepository<Customer ( 적용하고자 하는 model 객체타입 ) ,Long ( primary key 타입 )>
 * */
@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {

    List<Customer> findByEmail(String email);

}
