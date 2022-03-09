package com.projectmatching.app.controller.team;


import com.projectmatching.app.config.resTemplate.ResponeException;
import com.projectmatching.app.config.resTemplate.ResponseTemplate;
import com.projectmatching.app.constant.ServiceConstant;
import com.projectmatching.app.domain.common.Paging;
import com.projectmatching.app.domain.team.dto.TeamRequestDto;
import com.projectmatching.app.domain.team.dto.TeamResponseDto;
import com.projectmatching.app.service.team.TeamService;
import com.projectmatching.app.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.projectmatching.app.constant.ResponseTemplateStatus.EMPTY_TEAM_NAME;
import static com.projectmatching.app.constant.ResponseTemplateStatus.SUCCESS;
import static com.projectmatching.app.constant.ServiceConstant.PAGING_SIZE;


@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class TeamController {
    private final TeamService teamService;
    private final UserService userService;

    /**
     *
     * team 생성
     */
    @ApiOperation(value = "team 생성 API", notes = "team을 생성합니다.")
    @PostMapping("/team")
    public ResponseTemplate<Long> save(@RequestBody TeamRequestDto requestDto){
        if(requestDto.getT_name()==null) return ResponseTemplate.of(EMPTY_TEAM_NAME);

        try {
            String email = userService.getAuthUserEmail();
            Long result = teamService.save(requestDto, email);
            return ResponseTemplate.of(SUCCESS, result);
        }catch(ResponeException e){
            return ResponseTemplate.of(e.getStatus());
        }
    }

    /**
     * team 카드들 조회
     */
    @ApiOperation(value = "team 카드 조회 API", notes = "팀 리스트를 조회합니다.")
    @GetMapping("/team")
    public ResponseTemplate<List<TeamResponseDto>> getTeams(@RequestParam(name="Page") int lastPage){
        try{
            Paging paging = new Paging(lastPage,PAGING_SIZE, Sort.by("created_at").descending());
            return ResponseTemplate.of(SUCCESS, teamService.getTeams(paging));
        }catch (ResponeException e){
            return ResponseTemplate.of(e.getStatus());
        }
    }

    /**
     * team 삭제
     */
    @ApiOperation(value = "team 게시글 삭제 API", notes = "팀 게시글을 삭제합니다.")
    @DeleteMapping("/team")
    public ResponseTemplate<String> deleteTeam(@PathVariable Long team_id){
        try{
            teamService.delete(team_id);
            String result = "팀 삭제에 성공하였습니다.";
            return ResponseTemplate.of(SUCCESS, result);
        }catch (ResponeException e){
            return ResponseTemplate.of(e.getStatus());
        }
    }
}
