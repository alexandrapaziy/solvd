package com.solvd.bank.persistence.implMyBatis;

import com.solvd.bank.domain.Position;
import com.solvd.bank.persistence.PositionRepository;
import com.solvd.bank.persistence.config.Config;
import org.apache.ibatis.session.SqlSession;

public class PositionRepositoryImplMyBatis implements PositionRepository {
    @Override
    public void create(Position position) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            PositionRepository positionRepository = sqlSession.getMapper(PositionRepository.class);
            positionRepository.create(position);
        }
    }

    @Override
    public void deleteById(Integer positionId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            PositionRepository positionRepository = sqlSession.getMapper(PositionRepository.class);
            positionRepository.deleteById(positionId);
        }
    }

    @Override
    public Position findById(Integer positionId) {
        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            PositionRepository positionRepository = sqlSession.getMapper(PositionRepository.class);
            return positionRepository.findById(positionId);
        }
    }
}