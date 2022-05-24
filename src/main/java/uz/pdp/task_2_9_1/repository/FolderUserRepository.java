package uz.pdp.task_2_9_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_2_9_1.entity.FolderUser;

import java.util.UUID;

public interface FolderUserRepository extends JpaRepository<FolderUser, UUID> {
}
