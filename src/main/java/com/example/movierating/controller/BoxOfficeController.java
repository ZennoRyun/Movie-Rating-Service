package com.example.movierating.controller;

import com.example.movierating.api.MovieInfoAPI;
import com.example.movierating.dto.BoxOfficeDTO;
import com.example.movierating.dto.MovieDTO;
import com.example.movierating.entity.BoxOfficeEntity;
import com.example.movierating.service.BoxOfficeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class BoxOfficeController {
    @Autowired
    private BoxOfficeService boxOfficeService;

    @Scheduled(cron = "0 0 10 * * ?", zone = "Asia/Seoul") // 매일 오전 10시
    public void getBoxOffice() throws Exception {
        long now = System.currentTimeMillis() / 1000;
        log.info("schedule tasks using cron jobs - {}", now);
        boxOfficeService.deleteBoxOffice();
        MovieInfoAPI movieInfoAPI = new MovieInfoAPI();
        ArrayList<MovieDTO> movieList = movieInfoAPI.getBoxOffice();
        for(int i=0;i<movieList.size();i++) {
            BoxOfficeDTO dto = BoxOfficeDTO.builder()
                    .movieCd(movieList.get(i).getMovieCd())
                    .movieNm(movieList.get(i).getMovieNm())
                    .image(movieList.get(i).getImage())
                    .ranking(i+1)
                    .build();
            BoxOfficeEntity entity = BoxOfficeDTO.toEntity(dto);
            boxOfficeService.createBoxOffice(entity);
        }
    }

    @GetMapping
    public String viewBoxOffice(Model model) throws Exception {
        List<BoxOfficeEntity> movieList = boxOfficeService.retrieveAll();
        model.addAttribute("dailyBoxOfficeArr", movieList);

        return "viewBoxOffice";
    }
}
