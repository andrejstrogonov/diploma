package ru.skypro.homework.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {
    /**
     * Создаем SQL запрос для поиска строки с указанным username
     */
    @Query(value = "SELECT * FROM user_model WHERE username= ?1 AND role=?2", nativeQuery = true)
    UserModel informationAboutUserName(String userName,String role);

    /**
     * Создаем SQL запрос для поиска строки содержащей указанный пароль
     * */
    @Query(value = "SELECT * FROM user_model WHERE password= ?1", nativeQuery = true)
    UserModel findIdPassword(String password);

    /**
     * Создаем SQL запрос для проведения корректировки пароля по указанному id
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE user_model SET password=?1 WHERE username=?2", nativeQuery = true)
    void updatePassword(String password, String userName);

    /**
     * Создаем SQL запрос для проведения корректировки значений  first_name, last_name, phone по указанному id
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE user_model SET first_name=?1, last_name=?2, phone=?3 WHERE id=?4", nativeQuery = true)
    void updatingUserInformationAuthorised(String firstName,String lastName,String phone,int idRegister);

       /**
     * Создаем SQL запрос для проведения корректировки ссылки на avatar по указанному user_name
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE user_model SET image=?1 WHERE id=?2", nativeQuery = true)
    void updateImage(String image, int idRegister);
    /**
     * Создаем SQL запрос для поиска автора по указанному id
     */
    @Query(value = "SELECT * FROM user_model WHERE id= ?1", nativeQuery = true)
    UserModel userModelFindId(int id);
    /**
     * Создаем SQL запрос для записи новой строки с данными в указанных колонках
     **/
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_model (username,password,role)VALUES (?1,?2,?3)", nativeQuery = true)
    void saveAdd(String name, String password, String role);

}
