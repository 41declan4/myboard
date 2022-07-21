# 게시판 (CRUD, 로그인, 회원가입, 댓글) 만들기
- 전체 코드 다운로드 후 알집 풀고 인텔리제이 or STS로 파일 실행
- src -> main -> resources -> application.yml
- databases: url: jdbc:mysql://localhost:3306/board?serverTimezone=Asia/Seoul
- MySQL에 board스키마 생성 후 
- jpa: ddl-auto: update -> create로 변경 후 프로그램 실행
- 실핼 후 ddl-auto: update로 변경 필수(create일 경우 계속 데이터베이스 리셋)

# 개발에 사용한 기술
- IDE(통합개발 환경): IntelliJ(무료버전), VSCode, MySQL(workbench)
- 개발 환경 : Java11, SpringBoot, Maven, yml
- Database : Spring Data JPA, MySQL(workbench)
- View : HTML,CSS, JSP(JSTL), Bootstrap(4.5)
- Login & Security : Spring Security

# 구현한 기능 
- 게시글 추가(C) : repository(board) save를 이용해서 게시글 작성 기능 작업 완료
- 게시글 보기(R) : repository(board) findByAll을 이용해서 게시글 전체불러 오기 기능 완료
- 게시글 수정(U) : PathVariable를 통해 findById로 해당 게시글 영속성 컨테이너에 불러온 뒤 getter&setter를 이용해 게시글 수정 후 Transactional을 통한 더티체킹으로 게시글 수정 기능 작업 완료 
- 게시글 삭제(D) : PathVariable를 통해 deleteById로 해당 게시글 삭제 기능 완료
- 게시글 페이징 :  Pageable을 이용해 Page 타입으로 반환 받은 후 Pageable에 있는 메서드을 통해 각 content, number, size, totalPages, totalElements isFirst, hasNaxt 등 을 이용해 Model을 통해 클라이언트 서버에 전달 후 작업 완료
- 게시글 검색 : RequestParam을 이용한 검색어를 전달 한 후 Pageable과 같이 전달 (repository에서 Like Query를 통해 직접 interface 작성 후 사용)
- 댓글 보기 :  repository(reply) findByAll을 이용해서 게시글 전체불러 오기 기능 완료
- 댓글 작성 : repository(reply) save를 이용해서 게시글 작성 기능 작업 완료 
- 댓글 삭제 : PathVariable를 통해 deleteById로 해당 게시글의 댓글 삭제 기능 완료
- 회원가입 : @RequestBody를 통해 Json타입으로 회원가입 처리 완료 javascript의 Ajax 기술 사용, Security를 이용한 로그인해야 되기때문에 비밀번호는 암호화가 걸려야해서 BCryptPasswordEncoder를 통한 비밀번호 암호화 작업 기능 완료
- 로그인 처리 : Security를 이용해서 로그인 처리 작업 로그인페이지 Security에 만들어져있는 프론트단을 사용하지 않고 WebSecurityConfigurerAdapter를 상속 받아 configurer 오보라이딩해서 권환 및 로그인 페이지 로그인 기능 처리 등 한번에 처리

# 구현한 API 기능
- 게시글 추가(POST) : 
- 게시글 보기(GET) : 
- 게시글 수정(PUT) : 
- 게시글 삭제(DELETE) : 
- 댓글 보기 :  
- 댓글 작성 : 
- 댓글 삭제 : 
- 회원가입 : 
- 로그인 처리 : 

# Database 테이블 구현(ORM 방식)
- User : Long id, String username, String password, String email, String role, Timestamp createDate
- Board : Long id, String title, String content, int count; User user, List<Reply> replys, Timestamp createDate
- Reply : Long id, String comment, User user, Board board, Timestamp createDate

# Database 테이블 구현(ORM 방식) 하면서 힘들었던 점
- JPA 연관관계의 공부하게 되었고 1:N , N:1 등을 통해 테이블간에 맵핑해야 되는점을 배웠다
- 맵핑 어노테이션인 @ManyToOne @OneToMany를 통한 테이블간에 맵핑을 진행했다.
- 여기서 중요한점은 연관관계의 주인을 정확히 해줘야한다. 테이블의 하나의 row는 하나의 값만 가질 수 있기 때문에 board테이블의 reply은 연관관계의 주인이 되면 안되므로 mappedBy를 지정해줘야한다
- 내가 작업한 게시판 상세페이지에서는 댓글을 지연로딩 할 이유가없으므로 지연로딩(LAZY) 보다는 즉시로딩(EAGER)을 사용했다.
- OneToMany의 경우 default로 FetchType이 LAZY이기때문에 EAGER로 변경해줬다.
- 연관관계로인한 무한참조 방지하기 위해 JsonIgnoreProperties를 이용했다.
- board에도 reply을 맵핑하고있고 reply도 board를 맵핑하고있어서 계속해서 맵핑이 이뤄지고 있다 그것을 방지하기 위해 Board 테이블 쪽에서 reply을 딱 한번만 호출 한다.

