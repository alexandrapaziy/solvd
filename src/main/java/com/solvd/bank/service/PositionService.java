package com.solvd.bank.service;

import com.solvd.bank.domain.Position;

public interface PositionService {
    void createPosition(Position position);

    void deletePosition(Integer positionId);

    Position getPositionById(Integer positionId);
}