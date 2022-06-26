package com.fluffytrio.giftrio.advent;

import com.fluffytrio.giftrio.advent.dto.AdventRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdventService {

    private final AdventRepository adventRepository;

    @Transactional
    public Advent addAdvent(AdventRequestDto adventRequestDto){
        return adventRepository.save(adventRequestDto.toEntity());
    }

    @Transactional
    public Optional<Advent> getAdvent(Long adventId){
        return adventRepository.findById(adventId);
    }

    @Transactional
    public List<Advent> getAdvents(){
        return adventRepository.findAll();
    }

    @Transactional
    public Advent updateAdvent(AdventRequestDto adventRequestDto){
        Optional<Advent> originAdvent = adventRepository.findById(adventRequestDto.toEntity().getId());
//        if(! originAdvent.isPresent()){
//            return Exception;
//        }
        return adventRepository.save(adventRequestDto.toEntity());
    }

    @Transactional
    public boolean deleteAdvent(Long adventId){
        Optional<Advent> advent = adventRepository.findById(adventId);
        if(advent.isPresent()){
            advent.get().delete();
            adventRepository.save(advent.get());
            return true;
        }
        return false;
    }
}
