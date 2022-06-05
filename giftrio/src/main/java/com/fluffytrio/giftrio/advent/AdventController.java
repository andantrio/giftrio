package com.fluffytrio.giftrio.advent;

import com.fluffytrio.giftrio.advent.dto.AdventRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/advents")
public class AdventController {

    private final AdventService adventService;

    @GetMapping("/{adventId}")
    public Optional<Advent> getAdvent(@PathVariable Long adventId){
        return adventService.getAdvent(adventId);
    }

    @GetMapping()
    public List<Advent> getAdvents(){
        return adventService.getAdvents();
    }

    @PostMapping()
    public Advent addAdvent(@RequestBody AdventRequestDto adventRequestDto){
        return adventService.addAdvent(adventRequestDto);
    }

    @PutMapping("/{adventId}")
    public Advent patchAdvent(@RequestBody AdventRequestDto adventRequestDto){
        return adventService.patchAdvent(adventRequestDto);
    }

    @DeleteMapping("/{adventId}")
    public boolean deleteAdvent(@PathVariable Long adventId){
        return adventService.deleteAdvent(adventId);
    }
}
