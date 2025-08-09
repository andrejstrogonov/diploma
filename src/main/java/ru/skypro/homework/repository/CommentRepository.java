package ru.skypro.homework.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.CommentModel;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentModel, Integer> {
    /**
     * Создаем SQL запрос на добавление строки с комментарием
     */
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO comment_model (ad_pk,created_at,text)VALUES (?1,?2,?3)", nativeQuery = true)
    void saveComment(int adPk, long createAt, String text);

    /**
     * Создаем SQL запрос для поиска pk по указанным create_at и text
     */
    @Query(value = "SELECT pk FROM comment_model WHERE created_at=?1 AND text=?2", nativeQuery = true)
    int pkToCreateText(long createAt, String text);

    /**
     * Создаем SQL запрос для поиска списка pk по заданному ad_pk
     */
    @Query(value = "SELECT pk FROM comment_model WHERE ad_pk=?1", nativeQuery = true)
    List<Integer> findPkToAdPk(int adId);

    /**
     * Создаем SQL запрос для удаления строки из таблицы avatar_ad с указанным file_patch
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE from comment_model WHERE pk= ?1", nativeQuery = true)
    void deleteLine(int pk);

    /**
     * Создаем SQL запрос для поиска модели по указанному pk
     */
    @Query(value = "SELECT * FROM comment_model WHERE pk=?1", nativeQuery = true)
    CommentModel commentModel(int pk);

    /**
     * Создаем SQL запрос для проведения корректировки значения  text по указанному pk
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE comment_model SET text=?1 WHERE pk=?2", nativeQuery = true)
    void updatingCommentPk(String text, int pk);
}
