package com.example.movierating.api;

import com.example.movierating.entity.MovieEntity;
import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
@Component
public class MovieInfoAPI {
    //영화진흥원 API 발급키
    private KobisOpenAPIRestService service = new KobisOpenAPIRestService("4bec3d02a13bdfeaaa379dc98bd6e57d");

    public ArrayList getBoxOffice() throws Exception {
        LocalDateTime time = LocalDateTime.now().minusDays(1);
        String targetDt =  time.format(DateTimeFormatter.ofPattern("yyyMMdd"));
        //ROW 개수
        String itemPerPage = "10";
        //다양성영화(Y)/상업영화(N)/전체(default)
        String multiMovieYn = "";
        //한국영화(K)/외국영화(F)/전체(default)
        String repNationCd = "";
        //상영지역별 코드/전체(default)
        String wideAreaCd = "";
        // 일일 박스오피스 서비스 호출 (boolean isJson, String targetDt, String itemPerPage,String multiMovieYn, String repNationCd, String wideAreaCd)
        String dailyResponse = service.getDailyBoxOffice(true, targetDt, itemPerPage, multiMovieYn, repNationCd, wideAreaCd);
        // JSON 파싱
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(dailyResponse);
        JSONObject boxOfficeResult = (JSONObject) object.get("boxOfficeResult");
        JSONArray dailyBoxOfficeArr = (JSONArray) boxOfficeResult.get("dailyBoxOfficeList");
        ArrayList<MovieEntity> movieList = new ArrayList<>();
        for(int i=0;i<dailyBoxOfficeArr.size();i++) {
            object = (JSONObject) dailyBoxOfficeArr.get(i);
            MovieEntity movie = getMovieInfo((String) object.get("movieCd"));
            movieList.add(movie);
        }
        return movieList;
    }
    public MovieEntity getMovieInfo(String movieCd) throws Exception {
        String directors = "";
        String actors = "";
        JSONParser parser = new JSONParser();
        String movieResponse = service.getMovieInfo(true, movieCd);
        JSONObject object = (JSONObject) parser.parse(movieResponse);
        JSONObject movieInfoResult = (JSONObject) object.get("movieInfoResult");
        object = (JSONObject) movieInfoResult.get("movieInfo");
        JSONArray genresArr = (JSONArray) object.get("genres");
        JSONObject object2 = (JSONObject) genresArr.get(0);
        JSONArray directorsArr = (JSONArray) object.get("directors");
        for(int j=0;j<directorsArr.size();j++) {
            JSONObject object3 = (JSONObject) directorsArr.get(j);
            if (j!=0) {
                directors = directors + ", " + (String) object3.get("peopleNm");
            }
            else {
                directors = directors + (String) object3.get("peopleNm");
            }
        }
        JSONArray actorsArr = (JSONArray) object.get("actors");
        for(int j=0;j<actorsArr.size();j++) {
            if(j==3) {
                break;
            }
            JSONObject object3 = (JSONObject) actorsArr.get(j);
            if (j!=0) {
                actors = actors + ", " + (String) object3.get("peopleNm");
            }
            else {
                actors = actors + (String) object3.get("peopleNm");
            }
        }
        // 네이버 영화 검색 API로 이미지 주소 받아오기
        NaverMovieSearchAPI naverMovieSearchAPI = new NaverMovieSearchAPI();
        String image = naverMovieSearchAPI.search((String) object.get("movieNm"));

        MovieEntity movie = MovieEntity.builder()
                .movieCd((String) object.get("movieCd"))
                .movieNm((String) object.get("movieNm"))
                .openDt((String) object.get("openDt"))
                .genreNm((String) object2.get("genreNm"))
                .directors(directors)
                .actors(actors)
                .image(image)
                .build();
        return movie;
    }
}