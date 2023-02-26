# 영화뭐봐?

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">
<br>
영화 정보, 리뷰 제공 서비스
<br>
<br>

## 📋 Project Description
- 스프링부트로 서버 구축
- 영화진흥위원회 오픈 API에서 박스오피스/영화 정보/영화 검색 결과, 네이버 영화 오픈 API에서 영화 포스터를 REST 방식으로 가져옴
- Scheduler를 사용하여 간헐적으로 영화 API를 호출 -> 성능 향상
- Thymeleaf 뷰 템플릿 사용
- Transaction 설정
- Spring Security를 활용한 회원가입/로그인/로그아웃 구현
- 박스오피스/영화 정보/영화 검색 구현
- 리뷰(조회/작성) 구현
<br>

## 🛠 Project Architecture
![설계도](https://user-images.githubusercontent.com/81897623/215094796-982c55a5-897c-4bbb-adbb-df9e86db068e.PNG)
<br>

## :scroll: DFD(Data Flow Diagram)
![DFD](https://user-images.githubusercontent.com/81897623/215153127-b6bc1663-1233-46f6-9e8b-f86c3ed9ac89.PNG)
<br>

## :camera: Screenshot
### 회원가입
![회원가입](https://user-images.githubusercontent.com/81897623/221391810-9ad2e8fe-60f4-45bd-81f3-0d356d9672e7.png)
<br>
### 로그인
![로그인](https://user-images.githubusercontent.com/81897623/221391827-e02b7359-820e-42c4-a0d3-9fb7d629f851.png)
<br>
### 박스오피스
![박스오피스](https://user-images.githubusercontent.com/81897623/221391773-27808c3e-3d2a-472f-aad3-10464de4ee38.png)
<br>
### 영화 검색
![영화 검색](https://user-images.githubusercontent.com/81897623/221391795-6243354d-4ee4-463f-9f55-f0c89b4a7c45.png)
<br>
### 영화 정보 및 리뷰
![영화 정보 및 리뷰](https://user-images.githubusercontent.com/81897623/221391783-94c453d0-8b25-4d6c-9b76-61d3ca551ff8.png)
<br>

## :running: Run server
- AWS(EC2, RDS)로 배포
- Deploy Link : http://3.37.108.190:8080 (현재는 배포 중단)
