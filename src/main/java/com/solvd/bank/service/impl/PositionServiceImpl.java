package com.solvd.bank.service.impl;

import com.solvd.bank.domain.Position;
import com.solvd.bank.domain.exception.ServiceException;
import com.solvd.bank.persistence.PositionRepository;
import com.solvd.bank.service.PositionService;

public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public void createPosition(Position position) {
        try {
            positionRepository.create(position);
        } catch (Exception e) {
            throw new ServiceException("Failed to create position", e);
        }
    }

    @Override
    public void deletePosition(Integer positionId) {
        try {
            positionRepository.deleteById(positionId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete position", e);
        }
    }

    @Override
    public Position getPositionById(Integer positionId) {
        try {
            return positionRepository.findById(positionId);
        } catch (Exception e) {
            throw new ServiceException("Failed to get position by id", e);
        }
    }
}