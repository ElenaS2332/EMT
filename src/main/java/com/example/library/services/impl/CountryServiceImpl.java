package com.example.library.services.impl;

import com.example.library.models.Country;
import com.example.library.models.dto.CountryDto;
import com.example.library.models.exceptions.CountryNotFoundException;
import com.example.library.repositories.CountryRepository;
import com.example.library.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        Country country = new Country(name, continent);
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        Country country = new Country(countryDto.getName(), countryDto.getContinent());
        return Optional.of(countryRepository.save(country));    }

    @Override
    public Optional<Country> delete(Long id) {
        Country country = findById(id);
        countryRepository.delete(country);
        return Optional.of(country);
    }

    @Override
    public Optional<Country> update(Long id, String name, String continent) {
        Country country = findById(id);
        country.setName(name);
        country.setContinent(continent);
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public Optional<Country> update(Long id, CountryDto countryDto) {
        Country country = findById(id);
        country.setName(country.getName());
        country.setContinent(country.getContinent());
        return Optional.of(countryRepository.save(country));
    }

}
