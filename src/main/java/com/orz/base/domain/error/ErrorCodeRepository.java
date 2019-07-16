package com.orz.base.domain.error;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorCodeRepository extends JpaRepository<ErrorCode, Long> {

	public ErrorCode findByCode(String code);
}
