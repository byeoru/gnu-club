package com.server.gnu_club.api;

import com.server.gnu_club.domain.dto.ResultDto;
import com.server.gnu_club.domain.dto.request.AccountDto;
import com.server.gnu_club.domain.dto.request.PasswordChangeDto;
import com.server.gnu_club.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ManagerApiController {

    private final ManagerService managerService;

//    /**
//     * 매니저 회원가입
//     */
//    @PostMapping("/api/manager/signup")
//    public ResultDto<Long> signup(@RequestBody AccountDto accountDto) {
//        Boolean bDuplicated = managerService.isIdDuplicated(accountDto.getSignInId());
//
//        // ID 중복 체크
//        if (bDuplicated) {
//            return new ResultDto<>(1, null);
//        }
//        Long managerPk = managerService.signup(accountDto);
//        return new ResultDto<>(1, managerPk);
//    }

    /**
     * 매니저 로그인
     */
    @PostMapping("/api/manager/signIn")
    public ResultDto<Long> signIn(@RequestBody AccountDto accountDto) {
        Long managerId = managerService.signIn(accountDto);
        return new ResultDto<>(1, managerId);
    }

    /**
     * 매니저 비밀번호 변경
     */
    @PutMapping("/api/manager/{managerPk}/password")
    public ResultDto<Boolean> putPassword(@PathVariable Long managerPk, @RequestBody PasswordChangeDto passwordChangeDto) {
        Boolean bCorrectPassword = managerService.isCorrectPassword(managerPk, passwordChangeDto.getCurrentPassword());

        // 입력한 현재 비밀번호가 일치하면
        if (bCorrectPassword) {
            managerService.putPassword(managerPk, passwordChangeDto.getNewPassword());
            return new ResultDto<>(1, true);
        }

        return new ResultDto<>(1, false);
    }

    /**
     * 매니저 프로필사진 변경
     */
    @PutMapping("/api/manager/{managerPk}/profile_image")
    public ResultDto<Boolean> putProfileImage(@PathVariable Long managerPk,
                                              @RequestPart MultipartFile image) {

        managerService.putProfileImage(managerPk, image);
        return new ResultDto<>(1, true);
    }
}
