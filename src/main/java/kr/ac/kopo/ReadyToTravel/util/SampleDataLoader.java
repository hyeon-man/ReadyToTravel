package kr.ac.kopo.ReadyToTravel.util;

import kr.ac.kopo.ReadyToTravel.board.BoardRepository;
import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.PlaceEntity;
import kr.ac.kopo.ReadyToTravel.entity.board.BoardEntity;
import kr.ac.kopo.ReadyToTravel.member.MemberRepository;
import kr.ac.kopo.ReadyToTravel.root.PlaceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SampleDataLoader implements CommandLineRunner {
    private final PlaceRepository placeRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public SampleDataLoader(PlaceRepository placeRepository, MemberRepository memberRepository, BoardRepository boardRepository) {
        this.placeRepository = placeRepository;
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<PlaceEntity> placeList = new ArrayList<>();
        PlaceEntity seoul = PlaceEntity.builder()
                .comment("과거와 현재가 공존하며 하루가 다르게 변하는 서울을 여행하는 일은 매일이 새롭다. 도시 한복판에서 600년의 역사를 그대로 안고 있는 아름다운 고궁들과 더불어 대한민국의 트렌드를 이끌어나가는 예술과 문화의 크고 작은 동네들을 둘러볼 수 있는 서울은 도시 여행에 최적화된 장소다.")
                .kName("대한민국 서울")
                .eName("SEOUL")
                .fileName("/image/seoul.jpg")
                .build();
        placeList.add(seoul);

        PlaceEntity mokpo = PlaceEntity.builder()
                .comment("아름다운 한 폭의 동양화를 연상시키는 유달산에서 다도해의 경관을 한눈에 감상할 수 있다. 때묻지 않은 자연을 간직한 사랑의 섬 외달도는 전국에서 휴양하기 좋은 섬 30위 안에 선정될 만큼 아름다운 바다와 해변이 특징이다. 목포 평화광장 앞 바다에는 음악에 맞춰 빛과 물이 어우러지는 세계 최대의 춤추는 바다분수가 있어 이색적인 볼거리를 제공하고 있다. 갯벌 속의 인삼이라 불리는 세 발 낙지는 목포의 대표적인 토산품 중 하나이며 일부 지역에서만 잡히는 지역 특산품이다.")
                .kName("대한민국 목포")
                .eName("MOKPO")
                .fileName("/image/mokpo.jpg")
                .build();
        placeList.add(mokpo);

        PlaceEntity daejeon = PlaceEntity.builder()
                .comment("다양한 테마 여행이 가능한 대전광역시. 맨발로 걸을 수 있는 계족산 황톳길은 온몸으로 자연을 느끼는 여행을 할 수 있으며, 대전 근현대 전시관과 남간정사 등 대전에 있는 역사 문화 공간을 코스로 잡아도 좋다. 아이들이 좋아하는 동물원이 있는 오월드와 가볍게 산책하기 좋은 유림공원도 있어 주말 가족 나들이 코스로도 손색이 없다.")
                .kName("대한민국 대전")
                .eName("DAEJEON")
                .fileName("/image/daejeon.jpg")
                .build();
        placeList.add(daejeon);

        PlaceEntity jeonju = PlaceEntity.builder()
                .comment("한국의 멋이 살아있는 전주. 도심 한복판에 자리한 한옥마을에 들어서면 시대를 거슬러가는 기분이다. '경사스러운 터에 지어진 궁궐'이란 의미의 경기전에 들어서면 대나무가 서로 부대끼며 내는 소리에 귀 기울이게 된다. 전주 야경투어 명소의 대표인 전동성당과 한옥마을을 한눈에 내려다볼 수 있는 오목대 역시 빼놓을 수 없는 곳. 마을 전체가 미술관인 자만 벽화마을은 전주의 대표 포토 존이다.")
                .kName("대한민국 전주")
                .eName("JEONJU")
                .fileName("/image/jeonju.jpg")
                .build();
        placeList.add(jeonju);

        PlaceEntity tongyeong = PlaceEntity.builder()
                .comment("예술과 음식의 맛에 혼을 빼앗기는 통영. 보물 같은 섬 욕지도와 바다를 품은 장사도 해상공원 등 수려한 자연경관이 가득한 곳으로 많이 알려져 있지만, 시인 백석이 지나간 자리와 통영이 고향인 소설가 박경리의 발자취가 깃들어 있는 곳이기도 하다. 통영의 명물이 되었다는 충무김밥이나 대표 간식 꿀빵은 통영 여행에 빠지지 않는 주전부리 목록이다.")
                .kName("대한민국 통영")
                .eName("TONGYEONG")
                .fileName("/image/geojetongyeong.jpg")
                .build();
        placeList.add(tongyeong);

        PlaceEntity jeju = PlaceEntity.builder()
                .comment("섬 전체가 하나의 거대한 관광자원인 제주도. 에메랄드빛 물빛이 인상적인 협재 해수욕장은 제주 대표 여행지며, 파도가 넘보는 주상절리와 바다 위 산책로인 용머리 해안은 제주에서만 볼 수 있는 천혜의 자연경관으로 손꼽힌다. 드라마 촬영지로 알려진 섭지코스는 꾸준한 사랑을 받고 있으며 한라봉과 흑돼지, 은갈치 등은 제주를 대표하는 음식이다.")
                .kName("대한민국 제주도")
                .eName("JEJU")
                .fileName("/image/jeju.jpg")
                .build();
        placeList.add(jeju);

        PlaceEntity pohang = PlaceEntity.builder()
                .comment("경북 동남부에 위치해 한반도에서 가장 빨리 해가 뜨는 고장으로 알려진 경상북도 포항은 천혜의 해안선을 가진 해양경관의 보고이다. 특히 감포에서 구룡포까지 바닷가 도로에 펼쳐지는 풍광은 세계적인 미항인 나폴리나 시드니를 능가하는 아름답고 환상적 해양자원이다. 북부, 칠포해수욕장을 비롯한 여러 해수욕장이 있고 국립 등대박물관, 호미곶 등 해양관광자원은 포항의 대표적 관광자원이다.")
                .kName("대한민국 포항")
                .eName("POHANG")
                .fileName("/image/pohang.jpg")
                .build();
        placeList.add(pohang);

        PlaceEntity ulleung = PlaceEntity.builder()
                .comment("수백만 년 전 자연의 모습을 고스란히 간직한 울릉군. 겨울철 설경이 뛰어난 나리분지와 울릉도의 숨은 비경인 관음도는 약 14m 높이의 관음쌍굴을 보기 위해 많은 이들이 발걸음 한다. 생김새에 따른 이색 암석 형상들도 눈에 띄는데, 새끼를 등에 업은 거북바위부터 바닷물을 들이키는 코끼리바위, 영지버섯 모양의 버섯바위까지 다양해 바위 기행은 이곳에서만 즐길 수 있는 특별한 여행 테마이다. 자원의 보물 창고인 독도도 꼭 한번 다녀와야 할 여행지!")
                .kName("대한민국 울릉도")
                .eName("ULLEUNG")
                .fileName("/image/ulleung.jpg")
                .build();
        placeList.add(ulleung);

        PlaceEntity jecheon = PlaceEntity.builder()
                .comment("문화재의 집합소 충북 제천시. 청풍문화재단지를 시작으로 국내에서 가장 오래된 저수지 의림지를 비롯해 한국 천주교 전파의 진원지인 베론성지는 편히 둘러보기에도 좋다. 월악산에 둘러싸여 있는 송계계곡은 울창한 숲과 깊은 계곡이 있어 여름철 피서지로 인기가 높다.")
                .kName("대한민국 제천")
                .eName("JECHEON")
                .fileName("/image/jecheon.jpg")
                .build();
        placeList.add(jecheon);

        PlaceEntity chuncheon = PlaceEntity.builder()
                .comment("경춘선 청춘열차와 함께 일상 속 한 발짝 더 가까워진 낭만도시 춘천. 춘천의 대표 여행지로 손꼽히는 남이섬은 사계절마다 다채로운 모습으로 늘 새로운 공간을 연출하고, 김유정 작품의 무대가 되었던 실레마을을 걷다 보면 점순이 등 작품 속 인물들이 마중 나올 것만 같다. 자연 속에 파묻힌 문학과 예술의 숨결을 느끼고 싶다면 춘천 청춘열차에 올라보자.")
                .kName("대한민국 춘천")
                .eName("CHUNCHEON")
                .fileName("/image/chuncheon.jpg")
                .build();
        placeList.add(chuncheon);

        PlaceEntity namwon = PlaceEntity.builder()
                .comment("춘향의 사랑이 남겨진 남원. 남원의 대표 광한루는 춘향과 몽룡의 사랑이 시작된 곳으로 연못 위에 세워진 오작교가 운치를 더한다. 5가지의 테마로 꾸며진 춘향 테마파크와 작가 최명희의 대하소설의 무대가 되었던 <혼불> 문학관도 가볼 만하다. 지리산 바래봉은 철쭉제가 열리는 봄에 여행하기 좋다.")
                .kName("대한민국 남원")
                .eName("NAMWON")
                .fileName("/image/namwon.jpg")
                .build();
        placeList.add(namwon);

        PlaceEntity yeosu = PlaceEntity.builder()
                .comment("국제 해양관광의 중심 전남 여수시. 3천여 그루의 동백나무로 가득 찬 붉은 섬 오동도는 웰빙 트래킹 코스를 갖추고 있어 한층 더 운치 있다. 해상 케이블카를 타면 마치 바다 위를 걷는 듯한 느낌이 들며 탁 트인 바다 전망을 감상할 수 있다. 노래 가사에도 나오는 낭만적이고 황홀한 여수의 밤바다는 돌산대교와 음악분수가 함께 어우러져 멋진 야경을 선사한다. 공식 밥도둑 게장백반과 돌산 갓김치, 갈치조림 등 풍부한 먹거리까지 갖춘 인기 만점 관광지!")
                .kName("대한민국 여수")
                .eName("YEOSU")
                .fileName("/image/yeosu.jpg")
                .build();
        placeList.add(yeosu);

        PlaceEntity busan = PlaceEntity.builder()
                .comment("우리나라 제2의 수도 부산광역시. 부산 대표 관광지로 손꼽히는 해운대는 밤에는 마린시티의 야경이 더해져 더욱 화려한 해변이 된다. 감천문화마을은 사진 찍기에 좋으며, 매해 가을마다 개최되는 아시아 최대 규모의 영화제인 부산국제영화제와 함께 부산의 구석구석을 즐겨보는 것도 좋다. 전통시장 투어가 있을 만큼 먹거리가 가득한 부산의 맛기행은 필수!")
                .kName("대한민국 부산")
                .eName("BUSAN")
                .fileName("/image/busan.jpg")
                .build();
        placeList.add(busan);

        PlaceEntity gangneung = PlaceEntity.builder()
                .comment("은은한 커피향이 남다른 강원도 강릉시. 그중에도 카페거리로 유명한 안목해변은 발이 닿는 어디든 향긋한 커피 한 잔에 지평선 끝까지 펼쳐지는 바다 풍경은 덤으로 얻을 수 있다. 일출 명소로 유명한 정동진과 야경이 아름다운 경포대는 대표 여행 코스! 구름도 머물다 간다는 해발 1,100m에 위치한 강릉 안반데기 마을은 전망대에 올라 드넓게 펼쳐진 배추밭이 붉게 물드는 일출 전경이 일품이다.")
                .kName("대한민국 강릉")
                .eName("GANGNEUNG")
                .fileName("/image/gangneung.jpg")
                .build();
        placeList.add(gangneung);

        PlaceEntity gyeongju = PlaceEntity.builder()
                .comment("지붕 없는 박물관 경주. 경주는 그만큼 발길이 닿는 어느 곳이든 문화 유적지를 만날 수 있는 곳이다. 밤이면 더 빛나는 안압지를 비롯해 허허벌판에 자리를 굳건히 지키고 있는 첨성대. 뛰어난 건축미를 자랑하는 불국사 석굴암까지 어느 하나 빼놓을 수 없다. 경주 여행의 기록을 남기고 싶다면 스탬프 투어를 이용해보는 것도 좋다. 16곳의 명소를 탐방할 때마다 찍히는 도장 모으는 재미가 쏠쏠하다. 모바일 앱으로도 스탬프 투어 참여가 가능하다.")
                .kName("대한민국 경주")
                .eName("GYEONGJU")
                .fileName("/image/gyeongju.jpg")
                .build();
        placeList.add(gyeongju);

        PlaceEntity andong = PlaceEntity.builder()
                .comment("마을 전체가 유네스코 세계문화유산에 등재되어 있는 안동 하회마을. 퇴계 이황 선생의 가르침이 남아있는 도산서원과 그가 거닐던 예던길은 한적한 등산 코스로 좋다. 아기자기한 그림으로 채워진 신세동 벽화마을과 환상적인 야경을 볼 수 있는 월영교도 빼놓을 수 없다.")
                .kName("대한민국 안동")
                .eName("ANDONG")
                .fileName("/image/andong.jpg")
                .build();
        placeList.add(andong);

        PlaceEntity incheon = PlaceEntity.builder()
                .comment("살짝 비릿한 바다향이 매력적인 인천광역시. 지리적 특징을 잘 이용하여 내륙과 해안 지역의 관광이 두루 발달한 곳이다. 대표적인 해양관광지로는 을왕리 해수욕장을 비롯해 문화의 거리가 갖춰진 월미도 등이 있으며, 한국 속 작은 중국이라 불리는 차이나타운도 인천 여행지로 손꼽힌다. 이 외에도 인천 각처에 오랜 역사 유물들이 산재해 있어 역사를 테마로 여행 코스를 잡아보는 것도 좋다.")
                .kName("대한민국 인천")
                .eName("INCHEON")
                .fileName("/image/incheon.jpg")
                .build();
        placeList.add(incheon);

        PlaceEntity gunsan = PlaceEntity.builder()
                .comment("1930년대 우리나라 근대역사를 고스란히 간직한 도시 군산. 근대문화유산 투어 코스를 따라 걷다보면 곳곳에 남아있는 일본식 주택과 근대건축물들을 쉽게 볼 수 있다. 2.5km 길이의 오래된 철도가 놓인 경암동 철길마을은 출사지로도 유명. 우리나라에서 가장 오래된 빵집, 전국 5대 짬뽕 맛집, 70년 역사의 호떡집 등 군산 맛집 먹방 여행도 추천한다.")
                .kName("대한민국 군산")
                .eName("GUNSAN")
                .fileName("/image/gunsan.jpg")
                .build();
        placeList.add(gunsan);

        PlaceEntity gapyeong = PlaceEntity.builder()
                .comment("자연의 정취에 젖어들게 만드는 가평 아침고요수목원. 어디를 둘러봐도 풍경이 가득해 지루할 틈이 없다. 그중에도 가평하면 빼놓을 수 없는 쁘띠프랑스는 프랑스를 떠올리게 하는 이국적인 풍경으로 빨간 지붕이 매력적이다. 또한 매년 가을에 열리는 자라섬 국제재즈페스티벌은 석양과 음악이 어우러질 때까지 재즈의 매력에 흠뻑 취해볼 수 있다.")
                .kName("대한민국 가평")
                .eName("GAPYEONG")
                .fileName("/image/gapyeong.jpg")
                .build();
        placeList.add(gapyeong);

        PlaceEntity yeongwol = PlaceEntity.builder()
                .comment("작은 한반도가 숨어있는 영월군. 멀리서도 물속이 훤히 들여다보일 만큼 맑은 평창강이 감싸 안은 선암마을은 한반도를 빼닮아 한반도 지형이라는 이름을 갖고 있다. 이 아름다운 경관에 맞서는 신비로움이 숨어있으니 바로 고씨동굴이다. 약 5억 년 전에 형성된 것으로 추정되는 이 동굴은 석회동굴로 새로운 볼거리가 가득하다. 그 밖에 육지 속 작은 섬 청령포와 여름에는 래프팅을, 겨울이면 얼음낚시를 즐길 수 있는 동강도 인기 관광 코스!")
                .kName("대한민국 영월")
                .eName("YEONGWOL")
                .fileName("/image/yeongwol.jpg")
                .build();
        placeList.add(yeongwol);

        PlaceEntity suwon = PlaceEntity.builder()
                .comment("서울의 축소판이라도고 할 수 있는 경기도 수원시. 서울만큼이나 정치, 경제, 문화, 사회 등 다양한 부문에서 두루 발달한 도시이다. 그중에도 고고학적 가치를 지닌 수원화성은 수원의 자랑이며, 화성행궁 열차를 타고 대표 명소를 둘러보는 것도 좋다. 광교호수공원은 야경이 아름다워 밤에도 산책을 즐기는 사람들이 많으며, 수원 통닭골목은 저렴한 가격에 비해 양이 푸짐에 입소문을 타고 전국 각지에서 많은 이들이 찾아오고 있다.")
                .kName("대한민국 수원")
                .eName("SUWON")
                .fileName("/image/suwon.jpg")
                .build();
        placeList.add(suwon);

        List<MemberEntity> memberEntities = new ArrayList<>();

        MemberEntity hyeonMin = MemberEntity.builder()
                .email("zk925@naver.com")
                .memberId("admin")
                .name("김현민")
                .password("1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==")
                .phoneNum("01083143368")
                .profileIMG("hyeonman.jpg")
                .signupDate(new Date())
                .build();

        memberEntities.add(hyeonMin);

        MemberEntity jaeHyeon = MemberEntity.builder()
                .email("jaehyeon@naver.com")
                .memberId("admin2")
                .name("강재현")
                .password("1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==")
                .phoneNum("01000000000")
                .profileIMG("jaehyun.jpg")
                .signupDate(new Date())
                .build();
        memberEntities.add(jaeHyeon);

        MemberEntity jooSeon = MemberEntity.builder()
                .email("jooseon@naver.com")
                .memberId("admin3")
                .name("황주선")
                .password("1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==")
                .phoneNum("01011112222")
                .profileIMG("jooseon.jpg")
                .signupDate(new Date())
                .build();
        memberEntities.add(jooSeon);

        MemberEntity kangWon = MemberEntity.builder()
                .email("ekdrms5153@naver.com")
                .memberId("admin4")
                .name("신강원")
                .password("1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==")
                .phoneNum("01022222222")
                .profileIMG("kangwon.jpg")
                .signupDate(new Date())
                .build();
        memberEntities.add(kangWon);

        List<BoardEntity> boardEntities = new ArrayList<>();
        BoardEntity board1 = BoardEntity.builder()
                .boardNum(1L)
                .boardName("환상의 섬, 제주도 여행")
                .boardContent("제주도는 한국에서 가장 인기 있는 여행지 중 하나입니다. 황금모래로 이루어진 아름다운 해변, 신비로운 용암 동굴, 그리고 맑은 공기와 아름다운 자연 풍경이 돋보이는 풍부한 산악지대 등 다양한 관광 명소를 제공합니다. 또한, 특색 있는 제주도의 음식과 문화를 체험할 수 있는 장소들도 많이 있습니다. 제주도로의 여행은 자연과 문화를 동시에 즐길 수 있는 멋진 경험이 될 것입니다.")
                .boardDateCreate(new Date())
                .boardWriter(hyeonMin)
                .build();
        boardEntities.add(board1);

        BoardEntity board2 = BoardEntity.builder()
                .boardNum(2L)
                .boardName("충주로 떠나는 역사와 자연의 여행")
                .boardContent("충주는 역사적인 유적과 아름다운 자연 경관이 어우러진 매력적인 여행지입니다. 청주호와 남한강이 충주를 감싸고 있어서 물 관련 여행을 즐기기에도 좋습니다. 또한, 충주에는 성심당과 국립충주박물관과 같은 역사적인 유적지도 많이 있어서 문화적인 즐거움도 함께 누릴 수 있습니다. 충주로의 여행은 역사와 자연을 동시에 체험할 수 있는 특별한 경험이 될 것입니다.")
                .boardDateCreate(new Date())
                .boardWriter(kangWon)
                .build();
        boardEntities.add(board2);

        BoardEntity board3 = BoardEntity.builder()
                .boardNum(3L)
                .boardName("포항 해양도시에서 즐기는 바다 여행")
                .boardContent("포항은 매력적인 해양 도시로 유명합니다. 넓은 해변과 아름다운 바다 풍경을 감상할 수 있는 장소가 많이 있습니다. 특히, 경포대는 그 유명한 관광 명소 중 하나로, 맑은 날에는 일출과 일몰을 함께 경험할 수 있는 특별한 장소입니다. 또한, 포항에는 다양한 해양 스포츠를 즐길 수 있는 시설도 많아서 물의 즐거움을 만끽할 수 있습니다. 포항으로의 여행은 바다에서의 여유로운 시간과 함께 휴식과 즐거움을 느낄 수 있는 좋은 기회가 될 것입니다.")
                .boardDateCreate(new Date())
                .boardWriter(jooSeon)
                .build();
        boardEntities.add(board3);

        BoardEntity board4 = BoardEntity.builder()
                .boardNum(4L)
                .boardName("대전에서 만나는 과학과 문화의 도시")
                .boardContent("대전은 과학과 문화의 중심지로 알려진 도시입니다. 다양한 과학 박물관과 연구소, 전시관이 위치해 있어 과학에 대한 흥미로운 경험을 할 수 있습니다. 대전 EXPO 공원은 그 중에서도 유명한 관광 명소로, 다양한 전시와 체험 프로그램을 통해 과학과 미래에 대한 흥미를 자극합니다. 또한, 대전에는 문화와 예술을 즐길 수 있는 공연장과 박물관, 미술관 등도 많이 있습니다. 대전으로의 여행은 지적인 즐거움과 예술적 감동을 동시에 느낄 수 있는 멋진 경험이 될 것입니다.")
                .boardDateCreate(new Date())
                .boardWriter(jaeHyeon)
                .build();
        boardEntities.add(board4);

        BoardEntity board5 = BoardEntity.builder()
                .boardNum(5L)
                .boardName("자연과 즐거움이 어우러진 강릉 여행")
                .boardContent("강릉은 아름다운 자연 경관과 다양한 관광 명소로 유명한 도시입니다. 대한민국 최고의 해변인 남이섬과 명품 산수화로 유명한 오죽헌, 그리고 신선한 해산물을 맛볼 수 있는 강릉시장 등 다양한 장소를 방문할 수 있습니다. 또한, 강릉은 동해바다에서 서핑이나 해양레포츠를 즐길 수 있는 최적의 장소이기도 합니다. 자연과 즐거움이 어우러진 강릉으로의 여행은 힐링과 즐거움을 동시에 느낄 수 있는 멋진 경험이 될 것입니다.")
                .boardDateCreate(new Date())
                .boardWriter(kangWon)
                .build();
        boardEntities.add(board5);

        BoardEntity board6 = BoardEntity.builder()
                .boardNum(6L)
                .boardName("고성, 자연 속의 평화와 아름다움")
                .boardContent("고성은 강원도의 아름다운 자연 속에 위치한 평화로운 도시입니다. 청정한 산과 강, 그리고 푸른 바다가 어우러진 풍경은 매력적입니다. 여기에서는 다양한 자연 관광지를 즐길 수 있습니다. 고성 강문해변에서는 해수욕과 해돋이를 즐길 수 있으며, 고성 내동선사유적지에서는 고려시대의 역사와 문화를 체험할 수 있습니다. 또한, 고성은 맛있는 해산물과 강원도 특산품을 맛볼 수 있는 맛집과 시장도 많이 있습니다. 고성으로의 여행은 평화로운 자연과 풍부한 맛과 문화를 경험할 수 있는 특별한 시간이 될 것입니다.")
                .boardDateCreate(new Date())
                .boardWriter(jaeHyeon)
                .build();
        boardEntities.add(board6);

        BoardEntity board7 = BoardEntity.builder()
                .boardNum(7L)
                .boardName("인천의 매력을 느끼다")
                .boardContent("인천은 역사와 현대적인 도시의 매력이 공존하는 도시입니다. 인천 차이나타운에서는 중국 문화와 음식을 즐길 수 있으며, 송도 센트럴파크에서는 시원한 바다 풍경과 함께 산책을 즐길 수 있습니다. 또한, 인천 대공원에서는 자연과 놀이시설을 함께 즐길 수 있으며, 인천 테마파크에서는 다양한 엔터테인먼트를 즐길 수 있습니다. 인천으로의 여행은 다채로운 매력과 특색 있는 문화를 경험할 수 있는 멋진 시간이 될 것입니다.")
                .boardDateCreate(new Date())
                .boardWriter(hyeonMin)
                .build();
        boardEntities.add(board7);

        BoardEntity board8 = BoardEntity.builder()
                .boardNum(8L)
                .boardName("포천의 자연과 역사를 만나다")
                .boardContent("포천은 아름다운 자연 경관과 풍부한 역사 유적으로 유명한 여행지입니다. 포천 호수공원에서는 신선한 공기를 마시며 산책을 즐길 수 있으며, 포천 스카이워크에서는 아름다운 전망을 감상할 수 있습니다. 또한, 포천 소리체험박물관에서는 소리와 음악에 대한 색다른 체험을 할 수 있으며, 포천 성지숲에서는 조용한 시간을 보낼 수 있습니다. 포천으로의 여행은 자연 속에서 힐링하며 역사와 문화를 함께 체험할 수 있는 멋진 여행이 될 것입니다.")
                .boardDateCreate(new Date())
                .boardWriter(jooSeon)
                .build();
        boardEntities.add(board8);


        BoardEntity board9 = BoardEntity.builder()
                .boardNum(9L)
                .boardName("청주에서 즐기는 문화와 맛")
                .boardContent("청주는 다양한 문화와 맛을 즐길 수 있는 도시로 알려져 있습니다. 청주 박물관에서는 지역의 역사와 문화를 알아볼 수 있으며, 청주 성곽에서는 아름다운 전망을 감상할 수 있습니다. 또한, 청주의 맛집과 음식 문화를 체험할 수 있는 곳들도 많이 있습니다. 청주 향교는 전통적인 건물과 문화를 감상할 수 있는 장소로, 한국의 역사와 전통에 대해 알아갈 수 있습니다. 청주로의 여행은 다양한 맛과 문화를 즐기며 특별한 경험을 만들어갈 수 있는 멋진 여행이 될 것입니다.")
                .boardDateCreate(new Date())
                .boardWriter(kangWon)
                .build();
        boardEntities.add(board9);

        BoardEntity board10 = BoardEntity.builder()
                .boardNum(10L)
                .boardName("자연의 아름다움을 느낄 수 있는 삼척")
                .boardContent("삼척은 자연의 아름다움과 다양한 관광 명소로 유명한 도시입니다. 삼척 바다는 푸른 물과 아름다운 해안선이 돋보이며, 해수욕과 해양 스포츠를 즐길 수 있습니다. 또한, 삼척의 산과 계곡은 등산과 자연 감상을 즐길 수 있는 최적의 장소입니다. 삼척의 랜드마크인 삼척대교는 아름다운 경치를 감상할 수 있는 곳으로, 멋진 사진을 찍을 수 있습니다. 삼척으로의 여행은 자연 속에서 힐링하고 여유를 누릴 수 있는 특별한 경험을 선사해줄 것입니다.")
                .boardDateCreate(new Date())
                .boardWriter(jaeHyeon)
                .build();
        boardEntities.add(board10);

        BoardEntity board11 = BoardEntity.builder()
                .boardNum(11L)
                .boardName("문화와 역사의 도시, 세종")
                .boardContent("세종은 한국의 문화와 역사를 체험할 수 있는 도시입니다. 한글의 발명자인 세종대왕을 기리기 위해 만들어진 도시로, 세종대왕 기념관이 위치해 있습니다. 또한, 세종은 문화와 예술의 중심지로서 다양한 공연장, 미술관, 박물관 등이 있어 예술을 즐길 수 있는 장소들이 많습니다. 세종으로의 여행은 한국의 역사와 문화를 체험하며 깊이 있고 의미 있는 여행을 즐길 수 있을 것입니다.")
                .boardDateCreate(new Date())
                .boardWriter(hyeonMin)
                .build();
        boardEntities.add(board11);

        BoardEntity board12 = BoardEntity.builder()
                .boardNum(12L)
                .boardName("역사와 문화가 어우러진 천안")
                .boardContent("천안은 역사와 문화가 어우러진 매력적인 도시입니다. 천안의 대표적인 관광지로는 천안행궁과 병천면세점이 있습니다. 천안행궁은 조선시대 왕실의 거처로서 역사적인 가치를 지니고 있으며, 병천면세점은 쇼핑을 즐길 수 있는 명소입니다. 또한, 천안에는 다양한 문화예술 공연장과 박물관, 전시관 등도 있어 예술과 문화를 즐길 수 있는 장소들이 많습니다. 천안으로의 여행은 역사와 문화를 만끽하며 특별한 경험을 할 수 있을 것입니다.")
                .boardDateCreate(new Date())
                .boardWriter(kangWon)
                .build();
        boardEntities.add(board12);

        BoardEntity board13 = BoardEntity.builder()
                .boardNum(13L)
                .boardName("자연과 산책로로 떠나는 당진 여행")
                .boardContent("당진은 아름다운 자연과 다양한 산책로로 유명한 여행지입니다. 당진 해안도로는 해안 풍경과 함께 산책과 자전거 타기를 즐길 수 있는 멋진 장소입니다. 또한, 당진의 자연 속에 위치한 호수와 공원들은 휴식과 여유를 즐길 수 있는 최적의 장소입니다. 당진으로의 여행은 자연 속에서 힐링과 즐거움을 동시에 느낄 수 있는 멋진 경험이 될 것입니다.")
                .boardDateCreate(new Date())
                .boardWriter(hyeonMin)
                .build();
        boardEntities.add(board13);

        BoardEntity board14 = BoardEntity.builder()
                .boardNum(14L)
                .boardName("해변과 해산물로 맛보는 부산 여행")
                .boardContent("부산은 아름다운 해변과 풍부한 해산물로 유명한 도시입니다. 해운대, 광안리, 동백섬 등 부산의 해변은 매력적인 휴양지로 알려져 있습니다. 또한, 부산은 싱싱한 해산물을 맛볼 수 있는 다양한 식당과 시장이 있어 미식가들에게도 좋은 선택지입니다. 부산으로의 여행은 바다와 해산물을 즐기며 편안한 시간을 보낼 수 있는 멋진 경험을 선사할 것입니다.")
                .boardDateCreate(new Date())
                .boardWriter(jooSeon)
                .build();
        boardEntities.add(board14);

        BoardEntity board15 = BoardEntity.builder()
                .boardNum(15L)
                .boardName("다채로운 문화와 현대적인 도시의 매력, 서울")
                .boardContent("서울은 다채로운 문화와 현대적인 도시의 매력으로 많은 이들에게 사랑받는 여행지입니다. 궁궐과 전통 시장, 현대적인 쇼핑몰과 거리 등 다양한 관광 명소를 제공합니다. 또한, 서울은 한국의 역사와 문화를 체험할 수 있는 박물관과 전시관도 많이 있어 학문적인 즐거움을 느낄 수 있습니다. 서울로의 여행은 다양한 매력과 활동을 즐길 수 있는 멋진 경험을 선사할 것입니다.")
                .boardDateCreate(new Date())
                .boardWriter(jaeHyeon)
                .build();
        boardEntities.add(board15);


        placeRepository.saveAll(placeList);
        memberRepository.saveAll(memberEntities);
        boardRepository.saveAll(boardEntities);
    }
}