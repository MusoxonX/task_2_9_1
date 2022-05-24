package uz.pdp.task_2_9_1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.task_2_9_1.entity.template.AbsUUIDEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Space extends AbsUUIDEntity {
    private String name;

    private String color;

    private String initialLetter;

    private String icon;

    private String accessType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment avatar;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Workspace workspace;

    @PrePersist
    @PreUpdate
    public void setInitialLetterMyMethod() {
        this.initialLetter = name.substring(0, 1);
    }

    public Space(String name, String color, Attachment avatar, User owner, Workspace workspace) {
        this.name = name;
        this.color = color;
        this.avatar = avatar;
        this.owner = owner;
        this.workspace = workspace;
    }
}
