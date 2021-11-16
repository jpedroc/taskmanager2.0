package br.gov.basis.taskmanager.taskservice.web.rest;

import br.gov.basis.taskmanager.taskservice.service.OwnerService;
import br.gov.basis.taskmanager.taskservice.service.dto.OwnerDTO;
import br.gov.basis.taskmanager.taskservice.service.dto.listing.OwnerListDTO;
import br.gov.basis.taskmanager.taskservice.service.filter.OwnerFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
@Slf4j
public class OwnerResource {

    private final OwnerService ownerService;

    @GetMapping
    public ResponseEntity<List<OwnerDTO>> findAll() {
        List<OwnerDTO> owners = ownerService.find();
        log.info("Owners listados com sucesso: {}", owners.size());
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> findById(@PathVariable("id") Integer id) {
        OwnerDTO owner = ownerService.findOne(id);
        log.info("Owner recuperado com sucesso: {}", owner.getId());
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OwnerDTO> update(@RequestBody OwnerDTO ownerDTO) {
        OwnerDTO owner = ownerService.save(ownerDTO);
        log.info("Owner alterado com sucesso: {}", owner.getId());
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<OwnerListDTO>> search(@RequestBody OwnerFilter filter, Pageable pageable) {
        Page<OwnerListDTO> tasks = ownerService.search(filter, pageable);
        log.info("Todas as tasks foram listadas!");
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OwnerDTO> create(@RequestBody OwnerDTO ownerDTO) {
        OwnerDTO owner = ownerService.save(ownerDTO);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }
}
