package com.aramat.wish2eat.resources;

import com.aramat.wish2eat.dto.LoginDTO;
import com.aramat.wish2eat.dto.StoreDTO;
import com.aramat.wish2eat.dto.StoreInsertDTO;
import com.aramat.wish2eat.dto.UserDTO;
import com.aramat.wish2eat.service.StoreService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(tags = "Store")
@RestController
@RequestMapping(value = "/store")
public class StoreResource {
    @Autowired
    private StoreService service;

    @GetMapping
    public ResponseEntity<List<StoreDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StoreDTO> findById(@PathVariable Long id){
        StoreDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<StoreDTO> insert(@RequestBody @Valid StoreInsertDTO dto){
        StoreDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StoreDTO> update(@PathVariable Long id, @Valid @RequestBody StoreInsertDTO dto) {
        StoreDTO newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<StoreDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<StoreDTO> userLogin(@Valid @RequestBody LoginDTO dto) {
        return ResponseEntity.ok().body(service.findByEmailAndPassword(dto));
    }

}
