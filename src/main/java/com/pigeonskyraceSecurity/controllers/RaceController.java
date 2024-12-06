package com.pigeonskyraceSecurity.controllers;

import com.pigeonskyraceSecurity.dtos.RaceDTO;
import com.pigeonskyraceSecurity.services.CompetitionService;
import com.pigeonskyraceSecurity.services.RaceService;
import com.pigeonskyraceSecurity.utils.ApiResponse;
import com.pigeonskyraceSecurity.utils.ResponseUtil;
import com.pigeonskyraceSecurity.utils.mappers.dtos.BreederMapper;
import jakarta.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/races")
public class RaceController {

    @Autowired
    private BreederMapper breederMapper;
    @Autowired
    private RaceService raceService;

    @Autowired
    private CompetitionService competitionService;

    @PostMapping("save")
    public ResponseEntity<ApiResponse<RaceDTO>> save(
            @RequestBody RaceDTO raceDTO,
            HttpServletRequest request) {

        return ResponseEntity.ok(ResponseUtil.success(
                raceService.save(raceDTO),
                "Race saved successfully",
                request.getRequestURI()
        ));
    }


    @PatchMapping("close/{id}")
    public ResponseEntity<ApiResponse<RaceDTO>> update(@PathVariable String id, HttpServletRequest request) {
        RaceDTO raceDTO = raceService.close(id);
        return ResponseEntity.ok(ResponseUtil.success(raceDTO, "Race closed successfully", request.getRequestURI()));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RaceDTO>>> findAll(HttpServletRequest request) {
        return ResponseEntity.ok(ResponseUtil.success(raceService.findAll(), "Races retrieved successfully", request.getRequestURI()));
    }


    @PostMapping("save-all")
    public ResponseEntity<ApiResponse<List<RaceDTO>>> saveAll(@RequestBody List<RaceDTO> raceDTOs, HttpServletRequest request) {
        return ResponseEntity.ok(ResponseUtil.success(raceService.saveAll(raceDTOs), "Races saved successfully", request.getRequestURI()));
    }
}