package com.pigeonskyraceSecurity.controllers;

import com.pigeonskyraceSecurity.dtos.RankingDTO;
import com.pigeonskyraceSecurity.services.RankingService;
import com.pigeonskyraceSecurity.utils.ApiResponse;
import com.pigeonskyraceSecurity.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/ranking")
public class RankingController {
    private final RankingService rankingService;

    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }
    @PostMapping("addPigeonToRace")
    public ResponseEntity<ApiResponse<RankingDTO>> addPigeonToRace(@RequestBody RankingDTO rankingDTO, HttpServletRequest requestUrl) {


        RankingDTO rankingCreated = rankingService.addPigeonToRace(rankingDTO);
        return ResponseEntity.ok(ResponseUtil.success(rankingCreated, "Pigeon added succefully to race !!", requestUrl.getRequestURI()));
    }
    @PostMapping("save-all")
    public ResponseEntity<ApiResponse<List<RankingDTO>>> saveAll(HttpServletRequest request) throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csvData/ranking.csv");
//        List<RankingDTO> rankingList = rankingService.saveAll(file);
//        rankingDTOs = rankingDTOs.stream().toList();
        return ResponseEntity.ok(ResponseUtil.success(rankingService.saveAll(file), "Rankings saved successfully", request.getRequestURI()));
    }
}
