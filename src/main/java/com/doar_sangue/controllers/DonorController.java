package com.doar_sangue.controllers;

import com.doar_sangue.controllers.dto.DonorDTO;
import com.doar_sangue.controllers.helpers.DonorHelper;
import com.doar_sangue.entities.Donor;
import com.doar_sangue.services.DonorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/donors")
@Tag(name = "Donor EndPoint", description = "Operações relacionadas aos doadores")
public class DonorController {

    private static final Logger log = LoggerFactory.getLogger(DonorController.class);

    private final DonorService donorService;
    private final DonorHelper donorHelper;

    public DonorController(DonorService donorService, DonorHelper donorHelper) {
        this.donorService = donorService;
        this.donorHelper = donorHelper;
    }

    @PostMapping("/upload")
    @Operation(summary = "Upload de arquivos contendo uma lista de doadores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Upload de arquivos contendo uma lista de doadores"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> uploadDonors(@RequestParam("file") MultipartFile file) {
        log.info("Upload de arquivos contendo uma lista de doadores!");
        return ResponseEntity.ok(donorService.uploadDonors(file));
    }

    @PostMapping
    @Operation(summary = "Cria um doador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Criado com sucesso o doador"),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos campos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> postDonor(@Valid @RequestBody DonorDTO donorDto) {
        log.info("Cadastra doador!");

        Donor savedDonor = donorService.saveDonor(donorHelper.toEntity(donorDto));

        if (savedDonor == null)
            return ResponseEntity.badRequest()
                    .body("Erro ao cadastrar porque esse doador não pode doar!");

        DonorDTO savedDonorDTO = donorHelper.toModel(savedDonor);
        return ResponseEntity.ok(savedDonorDTO);
    }

    @GetMapping
    @Operation(summary = "Retorna a lista de doadores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista de doadores"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<DonorDTO>> getAllDonors() {
        log.info("Retorna a lista de doadores!");
        List<DonorDTO> donors = donorHelper.toModel(donorService.getAllDonors());
        return ResponseEntity.ok(donors);
    }

    @GetMapping("/imc")
    @Operation(summary = "Retorna IMC por faixa etária")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna IMC por faixa etária"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Map<String, Double>> getIMCByAgeGroup() {
        log.info("Retorna IMC por faixa etária!");
        return ResponseEntity.ok(donorService.getAverageIMCByAgeGroup());
    }

    @GetMapping("/obesos")
    @Operation(summary = "Retorna porcentagem de doadores obesos por faixa etária")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna porcentagem de doadores obesos por faixa etária"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Map<String, Double>> getPercentObesos() {
        log.info("Retorna porcentagem de doadores obesos por faixa etária!");
        return ResponseEntity.ok(donorService.calcPercentObesos());
    }

    @GetMapping("/por-estado")
    @Operation(summary = "Retorna quantidade de doadores por estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna quantidade de doadores por estado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Map<String, Integer>> getDonorsByState() {
        log.info("Retorna quantidade de doadores por estado!");
        return ResponseEntity.ok(donorService.donorsByState());
    }

    @GetMapping("/quantidade-receptor")
    @Operation(summary = "Retorna a quantidade de doadores por cada tipo de sangue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a quantidade de doadores por cada tipo de sangue"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Map<String, Integer>> getDonorsByReceptors() {
        log.info("Retorna a quantidade de doadores por cada tipo de sangue!");
        return ResponseEntity.ok(donorService.contDonorsByReceptors());
    }

    @GetMapping("/idade-media-tipo-sanguineo")
    @Operation(summary = "Retorna a média de idade por cada tipo de sangue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a média de idade por cada tipo de sangue"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Map<String, Double>> getCalcAVGAgeByTypeSanguineo() {
        log.info("Retorna a média de idade por cada tipo de sangue!");
        return ResponseEntity.ok(donorService.calcAVGAgeByTypeSanguineo());
    }
}


