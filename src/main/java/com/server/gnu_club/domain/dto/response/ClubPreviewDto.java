package com.server.gnu_club.domain.dto.response;

import com.server.gnu_club.domain.Club;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class ClubPreviewDto {

    private Long clubPk;
    private String clubName;

    public ClubPreviewDto(Club club) {
        this.clubPk = club.getPk();
        this.clubName = club.getClubName();
    }
}
