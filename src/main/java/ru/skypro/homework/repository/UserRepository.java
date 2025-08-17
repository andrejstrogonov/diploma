package ru.skypro.homework.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.RegisterUserModel;
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
     * Создаем SQL запрос на добавление строки при проведении авторизации
     */
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_model (id_register,first_name,last_name,phone)VALUES (?1,?2,?3,?4)", nativeQuery = true)
    void saveRule(Integer idRegister,String firstName, String lastName, String phone);

     /**
     * Создаем SQL запрос для записи значений user_name и password в новую строку (используется при отсутствии
     * информации в БД по авторизованному пользователю
     */
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO register_user_model (user_name,password)VALUES (?1,?2)", nativeQuery = true)
    void saveUserPassword(String userName,String password);
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
     * Создаем SQL запрос для поиска модели по указанному id
     */
    @Query(value = "SELECT * FROM user_model WHERE id= ?1", nativeQuery = true)
    UserModel userModel(int id);

       @Query(value = "SELECT register_id FROM user_model WHERE ppk= ?1", nativeQuery = true)
    int informationAboutIdRegist(int ppk);
}
