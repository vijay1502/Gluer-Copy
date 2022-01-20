package com.Vijay.Gluer.Repository;

import com.Vijay.Gluer.Model.UserRegistration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserRegistration,String>{
//    UserRegistration findAllById(String Email);
//    UserRegistration findAllByEncryptor(String encryptor);
}
