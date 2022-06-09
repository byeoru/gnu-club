package com.server.gnu_club.domain.dto.response;

import com.server.gnu_club.domain.Club;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Getter @Setter
@NoArgsConstructor(access = PROTECTED)
public class ClubDetailDto {

    private Long clubPk;
    private String clubName;
    private String intro;
    private String backgroundImgUrl;
    private int totalMemberCnt;
    private int currentMemberCnt;
    private Boolean bCheckIn;

    public ClubDetailDto(Club club, Boolean checkIn, int currentMemberCnt) {
        this.clubPk = club.getPk();
        this.clubName = club.getClubName();
        this.intro = club.getIntro();
        this.backgroundImgUrl = club.getBackgroundImgUrl();
        this.totalMemberCnt = club.getTotalMemberCnt();
        this.currentMemberCnt = currentMemberCnt;
        this.bCheckIn = checkIn;
    }
}
