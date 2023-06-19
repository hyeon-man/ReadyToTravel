package kr.ac.kopo.ReadyToTravel.util;

import kr.ac.kopo.ReadyToTravel.entity.MemberEntity;
import kr.ac.kopo.ReadyToTravel.entity.PlaceEntity;
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

    public SampleDataLoader(PlaceRepository placeRepository, MemberRepository memberRepository) {
        this.placeRepository = placeRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<PlaceEntity> placeList = new ArrayList<>();
        PlaceEntity seoul = PlaceEntity.builder()
                .comment("과거와 현재가 공존하며 하루가 다르게 변하는 서울을 여행하는 일은 매일이 새롭다. 도시 한복판에서 600년의 역사를 그대로 안고 있는 아름다운 고궁들과 더불어 대한민국의 트렌드를 이끌어나가는 예술과 문화의 크고 작은 동네들을 둘러볼 수 있는 서울은 도시 여행에 최적화된 장소다.")
                .kName("대한민국 서울")
                .eName("SEOUL")
                .fileName("https://www.dropbox.com/s/xli91dh23u9xndf/seoul.jpg?dl=1")
                .build();
        placeList.add(seoul);

        PlaceEntity mokpo = PlaceEntity.builder()
                .comment("아름다운 한 폭의 동양화를 연상시키는 유달산에서 다도해의 경관을 한눈에 감상할 수 있다. 때묻지 않은 자연을 간직한 사랑의 섬 외달도는 전국에서 휴양하기 좋은 섬 30위 안에 선정될 만큼 아름다운 바다와 해변이 특징이다. 목포 평화광장 앞 바다에는 음악에 맞춰 빛과 물이 어우러지는 세계 최대의 춤추는 바다분수가 있어 이색적인 볼거리를 제공하고 있다. 갯벌 속의 인삼이라 불리는 세 발 낙지는 목포의 대표적인 토산품 중 하나이며 일부 지역에서만 잡히는 지역 특산품이다.")
                .kName("대한민국 목포")
                .eName("MOKPO")
                .fileName("https://www.dropbox.com/s/cf0xz9pwmfz2bv2/mokpo.jpg?dl=1")
                .build();
        placeList.add(mokpo);

        PlaceEntity daejeon = PlaceEntity.builder()
                .comment("다양한 테마 여행이 가능한 대전광역시. 맨발로 걸을 수 있는 계족산 황톳길은 온몸으로 자연을 느끼는 여행을 할 수 있으며, 대전 근현대 전시관과 남간정사 등 대전에 있는 역사 문화 공간을 코스로 잡아도 좋다. 아이들이 좋아하는 동물원이 있는 오월드와 가볍게 산책하기 좋은 유림공원도 있어 주말 가족 나들이 코스로도 손색이 없다.")
                .kName("대한민국 대전")
                .eName("DAEJEON")
                .fileName("https://www.dropbox.com/s/5yvo134a9bqgcks/daejeon.jpg?dl=1")
                .build();
        placeList.add(daejeon);

        PlaceEntity jeonju = PlaceEntity.builder()
                .comment("한국의 멋이 살아있는 전주. 도심 한복판에 자리한 한옥마을에 들어서면 시대를 거슬러가는 기분이다. '경사스러운 터에 지어진 궁궐'이란 의미의 경기전에 들어서면 대나무가 서로 부대끼며 내는 소리에 귀 기울이게 된다. 전주 야경투어 명소의 대표인 전동성당과 한옥마을을 한눈에 내려다볼 수 있는 오목대 역시 빼놓을 수 없는 곳. 마을 전체가 미술관인 자만 벽화마을은 전주의 대표 포토 존이다.")
                .kName("대한민국 전주")
                .eName("JEONJU")
                .fileName("https://www.dropbox.com/s/rwogorlkgxf3x1f/jeonju.jpg?dl=1")
                .build();
        placeList.add(jeonju);

        PlaceEntity tongyeong = PlaceEntity.builder()
                .comment("예술과 음식의 맛에 혼을 빼앗기는 통영. 보물 같은 섬 욕지도와 바다를 품은 장사도 해상공원 등 수려한 자연경관이 가득한 곳으로 많이 알려져 있지만, 시인 백석이 지나간 자리와 통영이 고향인 소설가 박경리의 발자취가 깃들어 있는 곳이기도 하다. 통영의 명물이 되었다는 충무김밥이나 대표 간식 꿀빵은 통영 여행에 빠지지 않는 주전부리 목록이다.")
                .kName("대한민국 통영")
                .eName("TONGYEONG")
                .fileName("https://www.dropbox.com/s/mfm4xqbh77wt19v/tongyeong.jpg?dl=1")
                .build();
        placeList.add(tongyeong);

        PlaceEntity jeju = PlaceEntity.builder()
                .comment("섬 전체가 하나의 거대한 관광자원인 제주도. 에메랄드빛 물빛이 인상적인 협재 해수욕장은 제주 대표 여행지며, 파도가 넘보는 주상절리와 바다 위 산책로인 용머리 해안은 제주에서만 볼 수 있는 천혜의 자연경관으로 손꼽힌다. 드라마 촬영지로 알려진 섭지코스는 꾸준한 사랑을 받고 있으며 한라봉과 흑돼지, 은갈치 등은 제주를 대표하는 음식이다.")
                .kName("대한민국 제주도")
                .eName("JEJU")
                .fileName("https://www.dropbox.com/s/mbwg409dcj2c9ji/jeju.jpg?dl=1")
                .build();
        placeList.add(jeju);

        PlaceEntity pohang = PlaceEntity.builder()
                .comment("경북 동남부에 위치해 한반도에서 가장 빨리 해가 뜨는 고장으로 알려진 경상북도 포항은 천혜의 해안선을 가진 해양경관의 보고이다. 특히 감포에서 구룡포까지 바닷가 도로에 펼쳐지는 풍광은 세계적인 미항인 나폴리나 시드니를 능가하는 아름답고 환상적 해양자원이다. 북부, 칠포해수욕장을 비롯한 여러 해수욕장이 있고 국립 등대박물관, 호미곶 등 해양관광자원은 포항의 대표적 관광자원이다.")
                .kName("대한민국 포항")
                .eName("POHANG")
                .fileName("https://www.dropbox.com/s/j6sq2xbwnb5c89p/pohang.jpg?dl=1")
                .build();
        placeList.add(pohang);

        PlaceEntity ulleung = PlaceEntity.builder()
                .comment("수백만 년 전 자연의 모습을 고스란히 간직한 울릉군. 겨울철 설경이 뛰어난 나리분지와 울릉도의 숨은 비경인 관음도는 약 14m 높이의 관음쌍굴을 보기 위해 많은 이들이 발걸음 한다. 생김새에 따른 이색 암석 형상들도 눈에 띄는데, 새끼를 등에 업은 거북바위부터 바닷물을 들이키는 코끼리바위, 영지버섯 모양의 버섯바위까지 다양해 바위 기행은 이곳에서만 즐길 수 있는 특별한 여행 테마이다. 자원의 보물 창고인 독도도 꼭 한번 다녀와야 할 여행지!")
                .kName("대한민국 울릉도")
                .eName("ULLEUNG")
                .fileName("https://www.dropbox.com/s/stcz4lkwwmullc1/ulleung.jpg?dl=1")
                .build();
        placeList.add(ulleung);

        PlaceEntity jecheon = PlaceEntity.builder()
                .comment("문화재의 집합소 충북 제천시. 청풍문화재단지를 시작으로 국내에서 가장 오래된 저수지 의림지를 비롯해 한국 천주교 전파의 진원지인 베론성지는 편히 둘러보기에도 좋다. 월악산에 둘러싸여 있는 송계계곡은 울창한 숲과 깊은 계곡이 있어 여름철 피서지로 인기가 높다.")
                .kName("대한민국 제천")
                .eName("JECHEON")
                .fileName("https://www.dropbox.com/s/1lgqiyrhyazxb5u/jecheon.jpg?dl=1")
                .build();
        placeList.add(jecheon);

        PlaceEntity chuncheon = PlaceEntity.builder()
                .comment("경춘선 청춘열차와 함께 일상 속 한 발짝 더 가까워진 낭만도시 춘천. 춘천의 대표 여행지로 손꼽히는 남이섬은 사계절마다 다채로운 모습으로 늘 새로운 공간을 연출하고, 김유정 작품의 무대가 되었던 실레마을을 걷다 보면 점순이 등 작품 속 인물들이 마중 나올 것만 같다. 자연 속에 파묻힌 문학과 예술의 숨결을 느끼고 싶다면 춘천 청춘열차에 올라보자.")
                .kName("대한민국 춘천")
                .eName("CHUNCHEON")
                .fileName("https://www.dropbox.com/s/rlgd2wfd6gjkmu3/chuncheon.jpg?dl=1")
                .build();
        placeList.add(chuncheon);

        PlaceEntity namwon = PlaceEntity.builder()
                .comment("춘향의 사랑이 남겨진 남원. 남원의 대표 광한루는 춘향과 몽룡의 사랑이 시작된 곳으로 연못 위에 세워진 오작교가 운치를 더한다. 5가지의 테마로 꾸며진 춘향 테마파크와 작가 최명희의 대하소설의 무대가 되었던 <혼불> 문학관도 가볼 만하다. 지리산 바래봉은 철쭉제가 열리는 봄에 여행하기 좋다.")
                .kName("대한민국 남원")
                .eName("NAMWON")
                .fileName("https://www.dropbox.com/s/bbbhjj9828w184b/namwon.jpg?dl=1")
                .build();
        placeList.add(namwon);

        PlaceEntity yeosu = PlaceEntity.builder()
                .comment("국제 해양관광의 중심 전남 여수시. 3천여 그루의 동백나무로 가득 찬 붉은 섬 오동도는 웰빙 트래킹 코스를 갖추고 있어 한층 더 운치 있다. 해상 케이블카를 타면 마치 바다 위를 걷는 듯한 느낌이 들며 탁 트인 바다 전망을 감상할 수 있다. 노래 가사에도 나오는 낭만적이고 황홀한 여수의 밤바다는 돌산대교와 음악분수가 함께 어우러져 멋진 야경을 선사한다. 공식 밥도둑 게장백반과 돌산 갓김치, 갈치조림 등 풍부한 먹거리까지 갖춘 인기 만점 관광지!")
                .kName("대한민국 여수")
                .eName("YEOSU")
                .fileName("https://www.dropbox.com/s/cfncgt8n6k0if1y/yeosu.jpg?dl=1")
                .build();
        placeList.add(yeosu);

        PlaceEntity busan = PlaceEntity.builder()
                .comment("우리나라 제2의 수도 부산광역시. 부산 대표 관광지로 손꼽히는 해운대는 밤에는 마린시티의 야경이 더해져 더욱 화려한 해변이 된다. 감천문화마을은 사진 찍기에 좋으며, 매해 가을마다 개최되는 아시아 최대 규모의 영화제인 부산국제영화제와 함께 부산의 구석구석을 즐겨보는 것도 좋다. 전통시장 투어가 있을 만큼 먹거리가 가득한 부산의 맛기행은 필수!")
                .kName("대한민국 부산")
                .eName("BUSAN")
                .fileName("https://www.dropbox.com/s/u9h94s9jovlumkm/busan.jpg?dl=1")
                .build();
        placeList.add(busan);

        PlaceEntity gangneung = PlaceEntity.builder()
                .comment("은은한 커피향이 남다른 강원도 강릉시. 그중에도 카페거리로 유명한 안목해변은 발이 닿는 어디든 향긋한 커피 한 잔에 지평선 끝까지 펼쳐지는 바다 풍경은 덤으로 얻을 수 있다. 일출 명소로 유명한 정동진과 야경이 아름다운 경포대는 대표 여행 코스! 구름도 머물다 간다는 해발 1,100m에 위치한 강릉 안반데기 마을은 전망대에 올라 드넓게 펼쳐진 배추밭이 붉게 물드는 일출 전경이 일품이다.")
                .kName("대한민국 강릉")
                .eName("GANGNEUNG")
                .fileName("https://www.dropbox.com/s/nq1wmwnlhk56bfd/gangneung.jpg?dl=1")
                .build();
        placeList.add(gangneung);

        PlaceEntity gyeongju = PlaceEntity.builder()
                .comment("지붕 없는 박물관 경주. 경주는 그만큼 발길이 닿는 어느 곳이든 문화 유적지를 만날 수 있는 곳이다. 밤이면 더 빛나는 안압지를 비롯해 허허벌판에 자리를 굳건히 지키고 있는 첨성대. 뛰어난 건축미를 자랑하는 불국사 석굴암까지 어느 하나 빼놓을 수 없다. 경주 여행의 기록을 남기고 싶다면 스탬프 투어를 이용해보는 것도 좋다. 16곳의 명소를 탐방할 때마다 찍히는 도장 모으는 재미가 쏠쏠하다. 모바일 앱으로도 스탬프 투어 참여가 가능하다.")
                .kName("대한민국 경주")
                .eName("GYEONGJU")
                .fileName("https://www.dropbox.com/s/cqdqbfgthcn3mpu/gyeongju.jpg?dl=1")
                .build();
        placeList.add(gyeongju);

        PlaceEntity andong = PlaceEntity.builder()
                .comment("마을 전체가 유네스코 세계문화유산에 등재되어 있는 안동 하회마을. 퇴계 이황 선생의 가르침이 남아있는 도산서원과 그가 거닐던 예던길은 한적한 등산 코스로 좋다. 아기자기한 그림으로 채워진 신세동 벽화마을과 환상적인 야경을 볼 수 있는 월영교도 빼놓을 수 없다.")
                .kName("대한민국 안동")
                .eName("ANDONG")
                .fileName("https://www.dropbox.com/s/okdx7spa2ki75i9/andong.jpg?dl=1")
                .build();
        placeList.add(andong);

        PlaceEntity incheon = PlaceEntity.builder()
                .comment("살짝 비릿한 바다향이 매력적인 인천광역시. 지리적 특징을 잘 이용하여 내륙과 해안 지역의 관광이 두루 발달한 곳이다. 대표적인 해양관광지로는 을왕리 해수욕장을 비롯해 문화의 거리가 갖춰진 월미도 등이 있으며, 한국 속 작은 중국이라 불리는 차이나타운도 인천 여행지로 손꼽힌다. 이 외에도 인천 각처에 오랜 역사 유물들이 산재해 있어 역사를 테마로 여행 코스를 잡아보는 것도 좋다.")
                .kName("대한민국 인천")
                .eName("INCHEON")
                .fileName("https://www.dropbox.com/s/7e334mi999s5ly3/incheon.jpg?dl=1")
                .build();
        placeList.add(incheon);

        PlaceEntity gunsan = PlaceEntity.builder()
                .comment("1930년대 우리나라 근대역사를 고스란히 간직한 도시 군산. 근대문화유산 투어 코스를 따라 걷다보면 곳곳에 남아있는 일본식 주택과 근대건축물들을 쉽게 볼 수 있다. 2.5km 길이의 오래된 철도가 놓인 경암동 철길마을은 출사지로도 유명. 우리나라에서 가장 오래된 빵집, 전국 5대 짬뽕 맛집, 70년 역사의 호떡집 등 군산 맛집 먹방 여행도 추천한다.")
                .kName("대한민국 군산")
                .eName("GUNSAN")
                .fileName("https://www.dropbox.com/s/wjtmm5ika6lpct1/gunsan.jpg?dl=1")
                .build();
        placeList.add(gunsan);

        PlaceEntity gapyeong = PlaceEntity.builder()
                .comment("자연의 정취에 젖어들게 만드는 가평 아침고요수목원. 어디를 둘러봐도 풍경이 가득해 지루할 틈이 없다. 그중에도 가평하면 빼놓을 수 없는 쁘띠프랑스는 프랑스를 떠올리게 하는 이국적인 풍경으로 빨간 지붕이 매력적이다. 또한 매년 가을에 열리는 자라섬 국제재즈페스티벌은 석양과 음악이 어우러질 때까지 재즈의 매력에 흠뻑 취해볼 수 있다.")
                .kName("대한민국 가평")
                .eName("GAPYEONG")
                .fileName("https://www.dropbox.com/s/8i349s7vj1ox7up/gapyeong.jpg?dl=1")
                .build();
        placeList.add(gapyeong);

        PlaceEntity yeongwol = PlaceEntity.builder()
                .comment("작은 한반도가 숨어있는 영월군. 멀리서도 물속이 훤히 들여다보일 만큼 맑은 평창강이 감싸 안은 선암마을은 한반도를 빼닮아 한반도 지형이라는 이름을 갖고 있다. 이 아름다운 경관에 맞서는 신비로움이 숨어있으니 바로 고씨동굴이다. 약 5억 년 전에 형성된 것으로 추정되는 이 동굴은 석회동굴로 새로운 볼거리가 가득하다. 그 밖에 육지 속 작은 섬 청령포와 여름에는 래프팅을, 겨울이면 얼음낚시를 즐길 수 있는 동강도 인기 관광 코스!")
                .kName("대한민국 영월")
                .eName("YEONGWOL")
                .fileName("https://www.dropbox.com/s/n2yko0l560b06aq/yeongwol.jpg?dl=1")
                .build();
        placeList.add(yeongwol);

        PlaceEntity suwon = PlaceEntity.builder()
                .comment("서울의 축소판이라도고 할 수 있는 경기도 수원시. 서울만큼이나 정치, 경제, 문화, 사회 등 다양한 부문에서 두루 발달한 도시이다. 그중에도 고고학적 가치를 지닌 수원화성은 수원의 자랑이며, 화성행궁 열차를 타고 대표 명소를 둘러보는 것도 좋다. 광교호수공원은 야경이 아름다워 밤에도 산책을 즐기는 사람들이 많으며, 수원 통닭골목은 저렴한 가격에 비해 양이 푸짐에 입소문을 타고 전국 각지에서 많은 이들이 찾아오고 있다.")
                .kName("대한민국 수원")
                .eName("SUWON")
                .fileName("https://www.dropbox.com/s/jodnrbrelvro332/suwon.jpg?dl=1")
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
                .email("jaeHyeon@naver.com")
                .memberId("admin2")
                .name("강재현")
                .password("1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==")
                .phoneNum("01000000000")
                .profileIMG("jaehyun.jpg")
                .signupDate(new Date())
                .build();
        memberEntities.add(jaeHyeon);

        MemberEntity jooSeon = MemberEntity.builder()
                .email("jooSeon@naver.com")
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

        placeRepository.saveAll(placeList);
        memberRepository.saveAll(memberEntities);
    }
}