package com.example.movierating.api;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class NaverMovieSearchAPI {

    public String search(String query, String directors, String prdtYear) throws ParseException {
        try { // 검색어 인코딩
            query = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
        // URL 세팅
        String apiURL = "https://openapi.naver.com/v1/search/movie?query=" + query + "&display=100";
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", "dPLQ0H0Y4xXS9xSTlDMu");
        requestHeaders.put("X-Naver-Client-Secret", "PXTQZUHRYs");
        String responseBody = get(apiURL,requestHeaders);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject)parser.parse(responseBody);
        JSONArray item = (JSONArray)obj.get("items");
        JSONObject tmp;
        String image = "";
        String[] directorsArr = directors.split(", ");

        loopOut:
        for (int i = 0; i < item.size(); i++) {
            tmp = (JSONObject) item.get(i);
            if(!directors.equals("") && !Objects.equals(tmp.get("director").toString(), "")) {
                String[] directorsArr2 = tmp.get("director").toString().substring(0, tmp.get("director").toString().length() - 1).split("[|]");
                for (int j = 0; j < directorsArr.length; j++) {
                    for (int k = 0; k < directorsArr2.length; k++) {
                        if (Objects.equals(directorsArr[j], directorsArr2[k])) {
                            image = (String) tmp.get("image");
                            break loopOut;
                        }
                    }
                }
            }
            if(prdtYear.equals(tmp.get("pubDate"))) {
                image = (String) tmp.get("image");
                break;
            }
        }
        if(Objects.equals(image, "")) {
            image = "https://ssl.pstatic.net/static/movie/2012/06/dft_img203x290.png";
        }

        return image;
    }

    private String get(String apiUrl, Map<String, String> requestHeaders){ // Get 호출
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private HttpURLConnection connect(String apiUrl){ // HTTP 연결
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private String readBody(InputStream body){ // 응답 결과 가공
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}