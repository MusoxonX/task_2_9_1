package uz.pdp.task_2_9_1.payload;

import lombok.Data;
import uz.pdp.task_2_9_1.entity.enums.WorkspacePermissionName;

import java.util.List;
import java.util.UUID;

@Data
public class WorkspacePermissionDTO {
    private UUID workspaceId;
    private List<WorkspacePermissionName> permission;
}
