package uz.pdp.task_2_9_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_2_9_1.entity.Folder;
import uz.pdp.task_2_9_1.entity.Space;

import java.util.UUID;

public interface FolderRepository extends JpaRepository<Folder, UUID> {
    boolean existsByNameAndSpaceId(String name, UUID space_id);
}
