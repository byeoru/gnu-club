package com.server.gnu_club;


import com.server.gnu_club.domain.dto.request.AccountDto;
import com.server.gnu_club.service.ClubService;
import com.server.gnu_club.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DbInit {

//    private final ClubService clubService;
//    private final ManagerService managerService;
//
//    @PostConstruct
//    public void init() {
//
//        AccountDto managerDto1 = new AccountDto("manager1", "1234");
//        AccountDto managerDto2 = new AccountDto("manager2", "1234");
//        AccountDto managerDto3 = new AccountDto("manager3", "1234");
//        AccountDto managerDto4 = new AccountDto("manager4", "1234");
//        AccountDto managerDto5 = new AccountDto("manager5", "1234");
//        AccountDto managerDto6 = new AccountDto("manager6", "1234");
//        AccountDto managerDto7 = new AccountDto("manager7", "1234");
//        AccountDto managerDto8 = new AccountDto("manager8", "1234");
//        AccountDto managerDto9 = new AccountDto("manager9", "1234");
//        AccountDto managerDto10 = new AccountDto("manager10", "1234");
//        AccountDto managerDto11 = new AccountDto("manager11", "1234");
//        AccountDto managerDto12 = new AccountDto("manager12", "1234");
//
//        Long managerPk1 = managerService.signup(managerDto1);
//        Long managerPk2 = managerService.signup(managerDto2);
//        Long managerPk3 = managerService.signup(managerDto3);
//        Long managerPk4 = managerService.signup(managerDto4);
//        Long managerPk5 = managerService.signup(managerDto5);
//        Long managerPk6 = managerService.signup(managerDto6);
//        Long managerPk7 = managerService.signup(managerDto7);
//        Long managerPk8 = managerService.signup(managerDto8);
//        Long managerPk9 = managerService.signup(managerDto9);
//        Long managerPk10 = managerService.signup(managerDto10);
//        Long managerPk11 = managerService.signup(managerDto11);
//        Long managerPk12 = managerService.signup(managerDto12);
//
//        Long category1Pk = clubService.registerCategory("공연");
//        Long category2Pk = clubService.registerCategory("봉사");
//        Long category3Pk = clubService.registerCategory("생활, 문화");
//        Long category4Pk = clubService.registerCategory("외국어");
//        Long category5Pk = clubService.registerCategory("국제");
//        Long category6Pk = clubService.registerCategory("문학");
//        Long category7Pk = clubService.registerCategory("그림");
//        Long category8Pk = clubService.registerCategory("창업");
//        Long category9Pk = clubService.registerCategory("무예");
//        Long category10Pk = clubService.registerCategory("체육");
//        Long category11Pk = clubService.registerCategory("학술");
//        Long category12Pk = clubService.registerCategory("종교");
//
//
//        Long clubPk1 = clubService.registerClub(managerPk1, category1Pk, "기라성", "밴드 중심의 공연 동아리 활동을 하면서 악기를 다루고 연주하며 즐기는데 목적이 있음.");
//        Long clubPk2 = clubService.registerClub(managerPk1, category1Pk, "래펄즈", "경상대학교 내 힙합과 R&B 애호가들이 모여서 음악활동을 하는데 목적이 있음.");
//
//        Long clubPk3 = clubService.registerClub(managerPk2, category2Pk, "GNU플러스 봉사단", "연합봉사동아리로서 교내, 대외적으로 봉사활동을 하고 봉사를 통해 얻는 성취감과 자신감을 갖추는데 목적이 있음.");
//        Long clubPk4 = clubService.registerClub(managerPk2, category2Pk, "A to Z", "‘작은 변화로 세상을 바꾸다’ 대학생들이 솔선수범하여 더 나은 황경을 조성하는 힘쓰기 위한 목적이 있음.");
//
//        Long clubPk5 = clubService.registerClub(managerPk3, category3Pk, "경상바둑회(돌밭)", "바둑, 보드게임을 좋아하는 사람들이 모여 취미활동을 즐기고 각종 바둑대회에 참가 할 기회를 제공하는데 목적이 있음.");
//        Long clubPk6 = clubService.registerClub(managerPk3, category3Pk, "죽로다우회", "흔히 마시는 ‘차’에 대해 연구하고 시음하고 주변 좋은 찻집을 방문해 연구 및 시찰하는데 목적이 있음.");
//
//        Long clubPk7 = clubService.registerClub(managerPk4, category4Pk, "경상타임연구회", "타임지 해석을 통한 영어실력 향상, 동아리 활동을 통해 단체 활동 경험 및 공동체의식을 함양 시키는데 목적이 있음.");
//
//        Long clubPk8 = clubService.registerClub(managerPk5, category5Pk, "로타렉트", "연합봉사와 친목도모 그리고 환경미화를 중심적으로 활동을 하고 활동을 통한 자신감 함양에 목적이 있음.");
//
//        Long clubPk9 = clubService.registerClub(managerPk6, category6Pk, "미네르바", "바쁘게 살아가는 현대인들은 문학과 거리가 멀어져 팍팍한 삶을 살아가며 서서히 메말라 가는 독서활동과 착장활동을 통해 대학생으로서의 인문학적 소양을 향상에 도움을 주고자 하며 문학적 소양 외 다양한 토론의 장을 펼쳐 폭 넓은 사고를 할 기회를 제공 하는데 목적이 있음.");
//        Long clubPk10 = clubService.registerClub(managerPk6, category6Pk, "유니피스", "유니피스는 전국대학생연합동아리로서 평화를 위한 연구 및 활동을 그 목적으로 한다. 미래를 준비하는 20대인 우리 대학생들이 살아갈 시대에 어떻게 하면 무관심과 불신을 끝내고 평화를 달성할까를 고민하는 동아리라고 할 수 있다. 이와 더불어 고민들을 행동으로 옮겨 활동하여 실제로 평화를 위해서 움직이는데 목적이 있음.");
//
//        Long clubPk11 = clubService.registerClub(managerPk7, category7Pk, "그릴자유", "동아리 초기에는 사회문화운동과 관련된 동아리였으나, 현재는 경상대학교의 그림동아리로서 역할을 하고 있음.");
//        Long clubPk12 = clubService.registerClub(managerPk7, category7Pk, "흔적", "흑백 필름 사진에 관심있는 사람들끼리 모여 출사를 가고 사진을 인화, 현상을 하기 위함에 목적이 있음.");
//
//        Long clubPk13 = clubService.registerClub(managerPk8, category8Pk, "Apluses", "각 종 아이디어 공모전, 창업 공모전 또는 정부 보조금 사업에 도전하여 그 실패와 성공으로 기업가 정신을 함양할 수 있게 하는 것, 다양한 전공의 학생들과 팀을 만들어 사업을 진행 시키는 과정 중 본인의 전공에 국한되지 않고 다양한 분야를 체험하여 향후 진로를 선택하고 도전하는데 목적이 있음.");
//
//        Long clubPk14 = clubService.registerClub(managerPk9, category9Pk, "경상정도회", "검도 수련을 통한 친목도모 및 인격 수양 등을 통해 사회에 도움이 되는 건전한 인격체 양성에 목적이 있음.");
//        Long clubPk15 = clubService.registerClub(managerPk9, category9Pk, "카운터", "합기도 수련을 바탕으로 정신적 증진과 육체적 증진에 목적이 있음.");
//
//        Long clubPk16 = clubService.registerClub(managerPk10, category10Pk, "FC BB(풋살)", "공과대학 학생들을 중심으로 풋살이나 축구를 좋아하는 경상대학교 학생들의 친목과 단합을 위해 설립되었고, 남녀노소 누구나 즐겁게 운동하는데 목적이 있음.");
//        Long clubPk17 = clubService.registerClub(managerPk10, category10Pk, "오반칙", "‘농구’ 라는 취미활동을 중심으로 건간증진, 외부대회 참가를 위한 능력개발에 목적이 있음.");
//
//        Long clubPk18 = clubService.registerClub(managerPk11, category11Pk, "고나푸스", "회원 상호간의 친목을 도모하고 순수 아마추어 단체로서 별을 관측, 연구하여 날로 발전하는 천문지식을 일반인들에게 보급함에 목적이 있음.");
//        Long clubPk19 = clubService.registerClub(managerPk11, category11Pk, "얼사랑", "대학생활에 지친 학우들의 일상에 활기를 넣어주고 서로 친목을 도모하며 여행으로 대학생활의 추억을 쌓기 위함에 목적이 있음.");
//
//        Long clubPk20 = clubService.registerClub(managerPk12, category12Pk, "SFC", "SFC는 개혁주의 교회건설과 학원의 복음화를 위하여 운동하는 한국 내 자발적 기독교 동아리 활동을 하는데 목적이 있음.");
//        Long clubPk21 = clubService.registerClub(managerPk12, category12Pk, "가톨릭학생회", "가톨릭 학생회는 자신에게 성숙, 이웃에게 사랑, 사회에는 정의, 인류에게 평화 4가지 신념을 가지고 동아리 활동을 하는데 목적이 있음.");
//    }
}
