package uz.pdp.task_2_9_1.payload;

import lombok.Data;
import uz.pdp.task_2_9_1.entity.Space;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class FolderDTO {
    @NotNull
    private String name;
    @NotNull
    private UUID spaceId;

    private String accessType;

    private boolean archived;

    @NotNull
    private String color;
}
