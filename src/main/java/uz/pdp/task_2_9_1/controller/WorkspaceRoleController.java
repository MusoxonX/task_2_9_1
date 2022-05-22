package uz.pdp.task_2_9_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_2_9_1.entity.User;
import uz.pdp.task_2_9_1.payload.ApiResponse;
import uz.pdp.task_2_9_1.payload.WorkspacePermissionDTO;
import uz.pdp.task_2_9_1.payload.WorkspaceRoleDTO;
import uz.pdp.task_2_9_1.security.CurrentUser;
import uz.pdp.task_2_9_1.service.WorkspaceRoleService;

@RestController
@RequestMapping("/api/workspaceRole")
public class WorkspaceRoleController {

    @Autowired
    WorkspaceRoleService workspaceRoleService;

    /**
     * WORKSPACE QO'SHISH
     *
     * @param workspaceRoleDTO
     * @return
     */
    @PostMapping
    public HttpEntity<?> addRole(@RequestBody WorkspaceRoleDTO workspaceRoleDTO){
        ApiResponse apiResponse = workspaceRoleService.addRole(workspaceRoleDTO);
        return ResponseEntity.status(apiResponse.isSuccess()? 201:409).body(apiResponse);
    }

    /**
     * WORKSPACEROLE GA PERMISSION QO'SHISH
     * @param workspacePermissionDTO
     * @return
     */
    @PostMapping("/addPermission")
    public HttpEntity<?> addPermission(@RequestBody WorkspacePermissionDTO workspacePermissionDTO){
        ApiResponse apiResponse = workspaceRoleService.addPermission(workspacePermissionDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
}
