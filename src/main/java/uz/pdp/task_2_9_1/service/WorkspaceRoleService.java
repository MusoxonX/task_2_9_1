package uz.pdp.task_2_9_1.service;

import uz.pdp.task_2_9_1.payload.ApiResponse;
import uz.pdp.task_2_9_1.payload.WorkspacePermissionDTO;
import uz.pdp.task_2_9_1.payload.WorkspaceRoleDTO;

public interface WorkspaceRoleService {

    ApiResponse addRole(WorkspaceRoleDTO workspaceRoleDTO);

    ApiResponse addPermission(WorkspacePermissionDTO workspacePermissionDTO);
}
