package com.pigeonskyraceSecurity.controllers;

import com.pigeonskyraceSecurity.dtos.PigeonDTO;
import com.pigeonskyraceSecurity.services.PigeonService;
import com.pigeonskyraceSecurity.utils.ApiResponse;
import com.pigeonskyraceSecurity.utils.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pigeons")
public class PigeonController {
    private final PigeonService pigeonService;

    @Autowired
    public PigeonController(PigeonService pigeonService) {
        this.pigeonService = pigeonService;
    }

    @PostMapping("save")
    public ResponseEntity<ApiResponse<PigeonDTO>> save(@RequestBody @Valid PigeonDTO pigeonDTO, HttpServletRequest request) {
        String breederId = (String) request.getSession().getAttribute("breederId");
        PigeonDTO createdPigeon = pigeonService.save(pigeonDTO.withBreederId(breederId));
        return ResponseEntity.ok(ResponseUtil.success(createdPigeon, "Pigeon saved successfully", request.getRequestURI()));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PigeonDTO>>> findAll(HttpServletRequest request) {
        return ResponseEntity.ok(ResponseUtil.success(pigeonService.findAll(), "Pigeons retrieved successfully", request.getRequestURI()));
    }

    @PostMapping("save-all")
    public ResponseEntity<ApiResponse<List<PigeonDTO>>> saveAll(@RequestBody List<PigeonDTO> pigeonDTOs, HttpServletRequest request) {
        String breederId = (String) request.getSession().getAttribute("breederId");
        pigeonDTOs = pigeonDTOs.stream().map(pigeonDTO -> pigeonDTO.withBreederId(breederId)).toList();
        return ResponseEntity.ok(ResponseUtil.success(pigeonService.saveAll(pigeonDTOs), "Pigeons saved successfully", request.getRequestURI()));
    }
}
