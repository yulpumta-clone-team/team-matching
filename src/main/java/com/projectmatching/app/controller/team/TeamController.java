package com.projectmatching.app.controller.team;


import com.projectmatching.app.config.resTemplate.ResponeException;
import com.projectmatching.app.config.resTemplate.ResponseTemplate;
import com.projectmatching.app.constant.ServiceConstant;
import com.projectmatching.app.domain.team.dto.TeamRequestDto;
import com.projectmatching.app.domain.team.dto.TeamResponseDto;
import com.projectmatching.app.service.team.TeamService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.projectmatching.app.constant.ResponseTemplateStatus.EMPTY_TEAM_NAME;
import static com.projectmatching.app.constant.ResponseTemplateStatus.SUCCESS;


@Slf4j
@RequiredArgsConstructor
@RestController
public class TeamController {
    private final TeamService teamService;

    /**
     *
     * team 생성
     */
    @ApiOperation(value = "team 생성 API", notes = "team을 생성합니다.")
    @PostMapping("/team")
    public ResponseTemplate<Long> save(@RequestBody TeamRequestDto requestDto){
        if(requestDto.getName()==null) return ResponseTemplate.of(EMPTY_TEAM_NAME);

        try {
            Long result = teamService.save(requestDto);
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
    public ResponseTemplate<Page<TeamResponseDto>> getTeams(@PageableDefault(size=ServiceConstant.PAGING_SIZE) Pageable pageable){
        try{
            return ResponseTemplate.of(SUCCESS, teamService.getTeams(pageable));
        }catch (ResponeException e){
            return ResponseTemplate.of(e.getStatus());
        }
    }
}
