

package com.Reimbursement.models.empModel;

import javax.persistence.*;
import java.io.Serializable;


/**
 *
 * @author Anuj Kumar Pal
 */
@Entity
@Table(name = "employee_approver_mapping")
public class EmployeeApproverMapping implements Serializable {

    private int id, submitterEmployeeId, approverEmployeeId;
    private Employee submitter, approver;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "SubmitterEmployeeId")
    public int getSubmitterEmployeeId() {
        return submitterEmployeeId;
    }

    public void setSubmitterEmployeeId(int submitterEmployeeId) {
        this.submitterEmployeeId = submitterEmployeeId;
    }

    @Column(name = "ApproverEmployeeId")
    public int getApproverEmployeeId() {
        return approverEmployeeId;
    }

    public void setApproverEmployeeId(int approverEmployeeId) {
        this.approverEmployeeId = approverEmployeeId;
    }

}
