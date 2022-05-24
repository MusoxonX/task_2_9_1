package uz.pdp.task_2_9_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_2_9_1.entity.User;
import uz.pdp.task_2_9_1.payload.ApiResponse;
import uz.pdp.task_2_9_1.payload.SpaceDto;
import uz.pdp.task_2_9_1.security.CurrentUser;
import uz.pdp.task_2_9_1.service.SpaceService;

import java.util.UUID;

@RestController
@RequestMapping("/api/space")
public class SpaceController {

    @Autowired
    SpaceService spaceService;

    /**
     * SPACE QO'SHISH
     * @param spaceDto
     * @param user
     * @return
     */
    @PostMapping
    public HttpEntity<?> addSpace(@RequestBody SpaceDto spaceDto, @CurrentUser User user){
        ApiResponse apiResponse =spaceService.addSpace(spaceDto,user);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    /**
     * SPACE EDIT QILISH
     * @param spaceId
     * @param spaceDto
     * @return
     */
    @PutMapping("/{spaceId}")
    public HttpEntity<?> editSpace(@PathVariable UUID spaceId,SpaceDto spaceDto){
        ApiResponse apiResponse=spaceService.editSpace(spaceId,spaceDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    /**
     * SPACE DELETE QILISH
     * @param spaceId
     * @return
     */
    @DeleteMapping("/{spaceId}")
    public HttpEntity<?> deleteSpace(@PathVariable UUID spaceId){
        ApiResponse apiResponse=spaceService.deleteSpace(spaceId);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    /**
     * SPACELAR LISTINI OLISH
     * @return
     */
    @GetMapping
    public HttpEntity<?> getSpaceList(){
        ApiResponse apiResponse = spaceService.getSpaceList();
        return ResponseEntity.ok(apiResponse);
    }
}
