package com.fluffytrio.giftrio.advent;

import com.fluffytrio.giftrio.advent.dto.AdventRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdventService {

    private final AdventRepository adventRepository;

    @Transactional
    public Advent addAdvent(AdventRequestDto adventRequestDto){
        return adventRepository.save(adventRequestDto.toEntity());
    }
}
