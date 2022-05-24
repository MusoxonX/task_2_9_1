package uz.pdp.task_2_9_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_2_9_1.entity.View;

import java.util.List;
import java.util.UUID;

public interface ViewRepository extends JpaRepository<View, UUID> {
}
