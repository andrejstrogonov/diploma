package ru.skypro.homework.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.AdModel;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<AdModel,Integer> {
    /**
     * Создаем SQL запрос для записи новой строки с данными в указанных колонках
     **/
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO ad_model (author,title,description,price)VALUES (?1,?2,?3,?4)", nativeQuery = true)
    void saveAd(int id, String title, String description, int price);

    /**
     * Создаем SQL запрос для поиска строки с указанными title, description,price
     **/
    @Query(value = "SELECT pk FROM ad_model WHERE author= ?1 AND title= ?2 AND description= ?3 AND price= ?4", nativeQuery = true)
    int findPk(int id, String title, String description, int price);

    /**
     * Создаем SQL запрос для перезаписи содержимого ячейки image
     **/
    @Transactional
    @Modifying
    @Query(value = "UPDATE ad_model SET image=?1 WHERE pk=?2", nativeQuery = true)
    void updateImage(String image, int pk);

    /**
     * Создаем SQL запрос для вывода модели по указанному pk
     */
    @Query(value = "SELECT * FROM ad_model WHERE pk= ?1", nativeQuery = true)
    AdModel findPkObject(int pk);

    /**
     * Создаем SQL запрос для поиска ссылки на user_model по указанному pk
     */
    @Query(value = "SELECT author FROM ad_model WHERE pk=?1", nativeQuery = true)
    int author(int pk);

        /**
     * Создаем SQL запрос для поиска ссылки на аватар по указанному pk
     */
    @Query(value = "SELECT image FROM ad_model WHERE pk=?1", nativeQuery = true)
    String findPkImageAvatar(int pk);

    /**
     * Создаем SQL запрос для удаления товара по указанному pk
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE from ad_model WHERE pk= ?1", nativeQuery = true)
    void deleteAd(int pk);

    /**
     * Создаем SQL запрос для проведения корректировки значений  title, description, price по указанному pk
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE ad_model SET title=?1, description=?2, price=?3 WHERE pk=?4", nativeQuery = true)
    void updatingUserInformationAd(String title, String description, int price, int pk);

    /**
     * Создаем SQL запрос для поиска списка объявлений по указанному author
     */
    @Query(value = "SELECT * FROM ad_model WHERE author=?1", nativeQuery = true)
    List<AdModel> findListPkAdModel(int author);

    /**
     * Создаем SQL запрос для поиска ссылки author по указанному pk
     */
    @Query(value = "SELECT author FROM ad_model WHERE pk =?1", nativeQuery = true)
    int findAuthorToPk(int pk);
    /**
     * Создаем SQL запрос для создания списка ad
     */
    @Query(value = "SELECT * FROM ad_model", nativeQuery = true)
    List<AdModel> findAllAd();
}
