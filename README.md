# 영화뭐봐?

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">
<br>
영화 정보, 리뷰 제공 서비스
<br>
<br>

## 📋 Project Description
- 스프링부트로 서버 구축
- 영화진흥위원회 오픈 API에서 영화 정보, 네이버 영화 오픈 API에서 영화 포스터를 REST 방식으로 가져옴
- Scheduler를 사용하여 간헐적으로 영화 API를 호출 -> 성능 향상
- Thymeleaf 뷰 템플릿 사용
- Transaction 설정
- 박스오피스/영화 정보/영화 검색 구현
- 리뷰(조회/작성) 구현
<br>

## 🛠 Project Architecture
![설계도](https://user-images.githubusercontent.com/81897623/215094796-982c55a5-897c-4bbb-adbb-df9e86db068e.PNG)
<br>

## :scroll: DFD(Data Flow Diagram)
![DFD](https://user-images.githubusercontent.com/81897623/215116872-7efbbdd9-c0f2-4a77-9c74-c832693ab6c0.PNG)
<br>

## :camera: Screenshot
### 박스오피스
![박스오피스](https://user-images.githubusercontent.com/81897623/215107782-d43bb925-749f-45be-bdae-25db468404c5.PNG)
<br>
### 영화 검색
![영화 검색](https://user-images.githubusercontent.com/81897623/215108405-c454326d-8ea9-4297-9a6d-425ef480e9d1.PNG)
<br>
### 영화 정보, 리뷰
![영화 정보, 리뷰](https://user-images.githubusercontent.com/81897623/215107820-52af002b-f1a2-42e9-999f-31631872c9bf.PNG)
<br>

## :running: Run server
- AWS(EC2, RDS)로 배포
- Deploy Link : http://3.37.108.190:8080 (현재는 배포 중단)
