package com.luisgarcia.controllers;

import com.luisgarcia.controllers.dtos.Participante;
import com.luisgarcia.services.ParticipanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Participante", description = "Endpoint de participante")
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/participante")
public class ParticipanteController {

  private final ParticipanteService service;

  @Operation(summary = "Busca de um participante", description = "Recuperar dados de Participante por Id", tags = { "Participante" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Encontrado com sucesso",
          content = @Content(schema = @Schema(implementation = Participante.class))),
      @ApiResponse(responseCode = "400", description = "Participante não encontrado", content = @Content)
  })
  @GetMapping(value = "/{id}", produces = { "application/json" })
  public ResponseEntity<Participante> getById(@Parameter(description = "Id do Participante", required = true) @PathVariable("id") @NotBlank long id) {
    return ResponseEntity.ok().body(service.getById(id));
  }

  @Operation(summary = "Listar participantes", description = "Recuperar dados de todos participantes", tags = { "Participante" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Encontrado com sucesso",
          content = @Content(schema = @Schema(implementation = Participante.class))),
      @ApiResponse(responseCode = "400", description = "Participante não encontrado", content = @Content)
  })
  @GetMapping(produces = { "application/json" })
  public ResponseEntity<List<Participante>> getAll(@ParameterObject Pageable pageable) {
    return ResponseEntity.ok().body(service.getAll(pageable));
  }

  @Operation(summary = "Remover um participante", description = "Remover participante por Id", tags = { "Participante" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Removido com sucesso",
          content = @Content(schema = @Schema(implementation = Participante.class))),
      @ApiResponse(responseCode = "400", description = "Participante não encontrado", content = @Content)
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@Parameter(description = "Id do Participante", required = true) @PathVariable("id") @NotBlank long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Incluir novo Participante", description = "Inclusão de novo participante", tags = { "Participante" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Criado com sucesso",
          content = @Content(schema = @Schema(implementation = Participante.class))),
  })
  @PostMapping(consumes = { "application/json" })
  public ResponseEntity<Participante> create(@Valid @RequestBody final Participante participanteRequest) {
    return ResponseEntity.ok().body(service.create(participanteRequest));
  }

  @Operation(summary = "Atualizar participante", description = "Atualizar dados de Participante por Id", tags = { "Participante" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Alterado com sucesso",
          content = @Content(schema = @Schema(implementation = Participante.class))),
      @ApiResponse(responseCode = "400", description = "Participante não encontrado", content = @Content)
  })
  @PutMapping(value="/{id}", consumes = "application/json")
  public ResponseEntity<Participante> update(@Parameter(description = "Id do Participante", required = true) @PathVariable("id") @NotBlank long id, @Valid @RequestBody final Participante participanteRequest) {
    return ResponseEntity.ok().body(service.update(id, participanteRequest));
  }

}
