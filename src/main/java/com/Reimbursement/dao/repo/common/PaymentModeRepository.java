package com.Reimbursement.dao.repo.common;

import com.Reimbursement.models.commonModel.PaymentMode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentModeRepository  extends JpaRepository<PaymentMode, Integer> {
}
