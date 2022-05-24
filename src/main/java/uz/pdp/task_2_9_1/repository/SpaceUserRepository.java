package uz.pdp.task_2_9_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_2_9_1.entity.SpaceUser;

import java.util.UUID;

public interface SpaceUserRepository extends JpaRepository<SpaceUser, UUID> {
}
