package com.solvd.bank.persistence;

import com.solvd.bank.domain.Position;

public interface PositionRepository {
    void create(Position position);

    void deleteById(Integer positionId);

    Position findById(Integer positionId);
}