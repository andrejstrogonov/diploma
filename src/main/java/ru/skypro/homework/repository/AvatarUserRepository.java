package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.AvatarUserModel;
@Repository
public interface AvatarUserRepository extends JpaRepository<AvatarUserModel,Integer> {
}
