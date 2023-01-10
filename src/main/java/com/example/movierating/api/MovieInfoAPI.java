package com.example.movierating.api;

import com.example.movierating.dto.MovieDTO;
import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Slf4j
@Component
public class MovieInfoAPI {
    //영화진흥원 API 발급키
    private KobisOpenAPIRestService service = new KobisOpenAPIRestService("4bec3d02a13bdfeaaa379dc98bd6e57d");

    public ArrayList<MovieDTO> getBoxOffice() throws Exception {
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
        ArrayList<MovieDTO> movieList = new ArrayList<>();
        for(int i=0;i<dailyBoxOfficeArr.size();i++) {
            object = (JSONObject) dailyBoxOfficeArr.get(i);
            MovieDTO movie = getMovieInfo((String) object.get("movieCd"));
            movieList.add(movie);
        }

        return movieList;
    }

    public MovieDTO getMovieInfo(String movieCd) throws Exception {
        String genreNm = "";
        String directors = "";
        String actors = "";

        JSONParser parser = new JSONParser();
        String movieResponse = service.getMovieInfo(true, movieCd);
        JSONObject object = (JSONObject) parser.parse(movieResponse);
        JSONObject movieInfoResult = (JSONObject) object.get("movieInfoResult");
        object = (JSONObject) movieInfoResult.get("movieInfo");
        JSONArray genresArr = (JSONArray) object.get("genres");
        for(int j=0;j<genresArr.size();j++) {
            JSONObject object2 = (JSONObject) genresArr.get(j);
            if (j!=0) {
                genreNm = genreNm + ", " + object2.get("genreNm");
            } else {
                genreNm = genreNm + object2.get("genreNm");
            }
        }
        JSONArray directorsArr = (JSONArray) object.get("directors");
        for(int j=0;j<directorsArr.size();j++) {
            JSONObject object3 = (JSONObject) directorsArr.get(j);
            if (j!=0) {
                directors = directors + ", " + object3.get("peopleNm");
            } else {
                directors = directors + object3.get("peopleNm");
            }
        }
        JSONArray actorsArr = (JSONArray) object.get("actors");
        for(int j=0;j<actorsArr.size();j++) {
            if(j==4) {
                break;
            }
            JSONObject object3 = (JSONObject) actorsArr.get(j);
            if (j!=0) {
                actors = actors + ", " + object3.get("peopleNm");
            } else {
                actors = actors + object3.get("peopleNm");
            }
        }
        // 네이버 영화 검색 API로 이미지 주소 받아오기
        NaverMovieSearchAPI naverMovieSearchAPI = new NaverMovieSearchAPI();
        String image = naverMovieSearchAPI.search((String) object.get("movieNm"), directors, (String) object.get("prdtYear"));

        MovieDTO movie = MovieDTO.builder()
                .movieCd((String) object.get("movieCd"))
                .movieNm((String) object.get("movieNm"))
                .openDt((String) object.get("openDt"))
                .genreNm(genreNm)
                .directors(directors)
                .actors(actors)
                .image(image)
                .build();

        return movie;
    }

    public ArrayList<MovieDTO> searchMovie(String query, String query2) throws Exception {
        String[] movieTypeCd = new String[1];
        // 장편 영화만 검색
        movieTypeCd[0] = "220101";
        String searchResponse = service.getMovieList(true, "", "30", query, query2, "", "", "1960", "", "", movieTypeCd);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(searchResponse);
        JSONObject movieListResult = (JSONObject) object.get("movieListResult");
        JSONArray movieArr = (JSONArray) movieListResult.get("movieList");
        ArrayList<MovieDTO> movieList = new ArrayList<>();
        for(int i=0;i<movieArr.size();i++) {
            object = (JSONObject) movieArr.get(i);
            MovieDTO movie = getMovieInfo((String) object.get("movieCd"));
            movieList.add(movie);
        }

        return movieList;
    }
}