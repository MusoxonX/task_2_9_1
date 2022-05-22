package uz.pdp.task_2_9_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.task_2_9_1.entity.Workspace;
import uz.pdp.task_2_9_1.entity.WorkspacePermission;
import uz.pdp.task_2_9_1.entity.WorkspaceRole;
import uz.pdp.task_2_9_1.entity.enums.WorkspaceRoleName;
import uz.pdp.task_2_9_1.payload.ApiResponse;
import uz.pdp.task_2_9_1.payload.WorkspacePermissionDTO;
import uz.pdp.task_2_9_1.payload.WorkspaceRoleDTO;
import uz.pdp.task_2_9_1.repository.WorkspacePermissionRepository;
import uz.pdp.task_2_9_1.repository.WorkspaceRepository;
import uz.pdp.task_2_9_1.repository.WorkspaceRoleRepository;


@Service
public class WorkspaceRoleServiceImpl implements WorkspaceRoleService{

    @Autowired
    WorkspaceRoleRepository workspaceRoleRepository;
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    WorkspacePermissionRepository workspacePermissionRepository;

    /**
     * WORKSPACE ROLE QO'SHISH
     *
     * @param workspaceRoleDTO
     * @return
     */
    @Override
    public ApiResponse addRole(WorkspaceRoleDTO workspaceRoleDTO) {
        Workspace workspace = workspaceRepository.findById(workspaceRoleDTO.getWorkspaceId()).orElseThrow(() -> new ResourceNotFoundException("workspace"));
        WorkspaceRole workspaceRole = new WorkspaceRole(workspace,workspaceRoleDTO.getName(), WorkspaceRoleName.ROLE_MEMBER);
        workspaceRoleRepository.save(workspaceRole);
        return new ApiResponse("Workspace added",true);
    }

    @Override
    public ApiResponse addPermission(WorkspacePermissionDTO workspacePermissionDTO) {
        WorkspaceRole workspaceRole = workspaceRoleRepository.findById(workspacePermissionDTO.getWorkspaceId()).orElseThrow(() -> new ResourceNotFoundException("workspaceRole"));
        WorkspacePermission workspacePermission = new WorkspacePermission(workspaceRole,workspacePermissionDTO.getPermission());
        workspacePermissionRepository.save(workspacePermission);
        return new ApiResponse("Workspace permission added to role",true);
    }
}
