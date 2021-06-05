package com.aramat.wish2eat.resources;

import com.aramat.wish2eat.dto.StoreDTO;
import com.aramat.wish2eat.service.StoreService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<StoreDTO> insert(@RequestBody @Valid StoreDTO dto){
        dto = service.insert(dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StoreDTO> update(@PathVariable Long id, @Valid @RequestBody StoreDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<StoreDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
