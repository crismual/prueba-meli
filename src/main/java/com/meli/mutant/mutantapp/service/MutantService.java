package com.meli.mutant.mutantapp.service;

import com.meli.mutant.mutantapp.entity.MutantEntity;
import com.meli.mutant.mutantapp.logger.ILoggerStrategy;
import com.meli.mutant.mutantapp.model.request.DnaRequestDTO;
import com.meli.mutant.mutantapp.model.response.StatsResponseDTO;
import com.meli.mutant.mutantapp.repository.IMutantRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.StreamSupport;


@Service
public class MutantService implements IMutantService {

    private final MutantValidator mutantValidator;

    private final IMutantRepository mutantRepository;

    private final ILoggerStrategy logStrategy;

    public MutantService(MutantValidator mutantValidator, IMutantRepository mutantRepository, ILoggerStrategy logStrategy) {
        this.mutantValidator = mutantValidator;
        this.mutantRepository = mutantRepository;
        this.logStrategy = logStrategy;
    }

    @Override
    public boolean validateDNA(DnaRequestDTO request) {

        logStrategy.logInfo("request body: "+ request.toString());

        String dna = String.join("|", request.getDna());
        String id = DigestUtils.md5Hex(dna);

        Optional<MutantEntity> mutant =  mutantRepository.findById(id);

        if(mutant.isPresent()){
            logStrategy.logInfo("dna already exist in db");
            return mutant.get().isMutant();
        }

        boolean isMutant = mutantValidator.isMutant(request.getDna());
        mutantRepository.save(new MutantEntity(id, dna, isMutant));


        logStrategy.logInfo("validation result: "+isMutant);

        return isMutant;
    }

    @Override
    public StatsResponseDTO getMutantStats() {

        logStrategy.logInfo("querying dna's");
        Iterable<MutantEntity> mutants = mutantRepository.findAll();

        logStrategy.logInfo("dna's :"+mutants);

        double countMutants = (double) StreamSupport.stream(mutants.spliterator(), false).filter(MutantEntity::isMutant).count();
        double countHumans = (double) StreamSupport.stream(mutants.spliterator(), false).filter(x -> !x.isMutant()).count();

        StatsResponseDTO response = new StatsResponseDTO((int)countMutants,(int)countHumans, countMutants/countHumans);

        logStrategy.logInfo("stats result :"+response);

        return response;
    }
}
