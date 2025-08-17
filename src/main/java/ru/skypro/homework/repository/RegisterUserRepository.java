package ru.skypro.homework.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.RegisterUserModel;

public interface RegisterUserRepository extends JpaRepository<RegisterUserModel,Integer> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO register_user_model (role,password,username)VALUES (?1,?2,?3)", nativeQuery = true)
    void saveRegister(Role role, String password, String userName);
    /**
     * Создаем SQL запрос для поиска id с указанным userName,password,role
     */
    @Query(value = "SELECT id FROM register_user_model WHERE username= ?1 AND password=?2", nativeQuery = true)
    int informationIdToParameter(String userName,String password);
      /**
     * Создаем SQL запрос для поиска строки по userName и password
     */
    @Query(value = "SELECT COUNT(*) FROM register_user_model WHERE user_name= ?1 AND password= ?2", nativeQuery = true)
    int availabilityInDatabase(String userName,String password);
    /**
     * Создаем SQL запрос для поиска строки с указанным userName
     */
    @Query(value = "SELECT * FROM register_user_model WHERE username= ?1", nativeQuery = true)
    RegisterUserModel informationAboutUser(String userName);
    /**
     * Создаем SQL запрос для поиска строки с указанным userName
     */
    @Query(value = "SELECT * FROM register_user_model WHERE id= ?1", nativeQuery = true)
    RegisterUserModel informationAboutId(int id);
}
