package uz.pdp.task_2_9_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_2_9_1.entity.Space;

import java.util.Optional;
import java.util.UUID;

public interface SpaceRepository extends JpaRepository<Space, UUID> {
    Optional<Space> findByOwnerIdAndName(UUID owner_id, String name);
}
