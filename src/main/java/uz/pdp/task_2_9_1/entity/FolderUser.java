package uz.pdp.task_2_9_1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.task_2_9_1.entity.enums.TaskPermission;
import uz.pdp.task_2_9_1.entity.template.AbsUUIDEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FolderUser extends AbsUUIDEntity {
    @ManyToOne
    @JoinColumn(nullable = false)
    private Folder folder;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    private TaskPermission taskPermission;
}
