package com.solvd.persistence;

import com.solvd.domain.Stand;
import org.apache.ibatis.annotations.Param;

public interface StandRepository extends GenericRepository<Stand> {
    void addClient(@Param("standId") Long standId, @Param("clientId") Long clientId);

    void removeClient(@Param("standId") Long standId);
}
