package com.server.gnu_club.domain.dto.response;

import com.server.gnu_club.domain.JoinedClub;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class JoinedClubDto {

    private Long joinedClubPk;
    private String clubName;

    public JoinedClubDto(JoinedClub joinedClub) {
        this.joinedClubPk = joinedClub.getPk();
        this.clubName = joinedClub.getClub().getClubName();
    }
}
