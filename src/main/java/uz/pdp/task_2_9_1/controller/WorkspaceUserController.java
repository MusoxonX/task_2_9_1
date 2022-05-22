package uz.pdp.task_2_9_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_2_9_1.entity.User;
import uz.pdp.task_2_9_1.payload.ApiResponse;
import uz.pdp.task_2_9_1.payload.MemberDTO;
import uz.pdp.task_2_9_1.security.CurrentUser;
import uz.pdp.task_2_9_1.service.WorkspaceUserService;

@RestController
@RequestMapping("/api/workspaceUser")
public class WorkspaceUserController {

    @Autowired
    WorkspaceUserService workspaceUserService;

    /**
     * WORLSPACE GA MEMBER YOKI GUEST QO'SHISH
     *
     * @param workspaceId
     * @param memberDTO
     * @param user
     * @return
     */
    @PostMapping("/{workspaceId}")
    public HttpEntity<?> addUserOrMember(
            @PathVariable Long workspaceId,
            @RequestBody MemberDTO memberDTO,
            @CurrentUser User user){
        ApiResponse apiResponse = workspaceUserService.editOwner(workspaceId, memberDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    /**
     * WORKSPACE MEMBERLARINI KO'RISH
     *
     * @param workspaceId
     * @return
     */

    @GetMapping("/{workspaceId}")
    public HttpEntity<?> getMembers(@PathVariable Long workspaceId,@CurrentUser User user){
        ApiResponse apiResponse =workspaceUserService.getMembers(workspaceId,user);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
