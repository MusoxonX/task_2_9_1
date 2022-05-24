package uz.pdp.task_2_9_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_2_9_1.entity.User;
import uz.pdp.task_2_9_1.payload.ApiResponse;
import uz.pdp.task_2_9_1.payload.FolderDTO;
import uz.pdp.task_2_9_1.security.CurrentUser;
import uz.pdp.task_2_9_1.service.FolderService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/folder")
public class FolderController {
    @Autowired
    FolderService folderService;

    /**
     * FOLDER QO'SHISH
     * @param folderDTO
     * @param user
     * @return
     */
    @PostMapping
    public HttpEntity<?> addFolder(@Valid @RequestBody FolderDTO folderDTO, @CurrentUser User user){
        ApiResponse apiResponse = folderService.addFolder(folderDTO,user);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    /**
     * FOLDER EDIT QILISH
     * @param folderId
     * @param folderDTO
     * @return
     */
    @PutMapping("/{folderId}")
    public HttpEntity<?> editFolder(@PathVariable UUID folderId, @RequestBody FolderDTO folderDTO){
        ApiResponse apiResponse = folderService.editService(folderDTO,folderId);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    /**
     * FOLDER DELETE QILISH
     * @param folderId
     * @return
     */
    @DeleteMapping("/{folderId}")
    public HttpEntity<?> deleteFolder(@PathVariable UUID folderId){
        ApiResponse apiResponse = folderService.deleteFolder(folderId);
        return ResponseEntity.status(apiResponse.isSuccess()?409:404).body(apiResponse);
    }

    /**
     * FOLDERLAR LISTINI OLISH
     * @return
     */
    @GetMapping
    public HttpEntity<?> getFolderList(){
        ApiResponse apiResponse = folderService.getFolderList();
        return ResponseEntity.ok(apiResponse);
    }
}
