package com.fluffytrio.giftrio.advent;

import com.fluffytrio.giftrio.advent.dto.AdventRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/advent")
public class AdventController {

    private final AdventService adventService;

    @PostMapping()
    public Advent addAdvent(@RequestBody AdventRequestDto adventRequestDto){
        return adventService.addAdvent(adventRequestDto);
    }

}
