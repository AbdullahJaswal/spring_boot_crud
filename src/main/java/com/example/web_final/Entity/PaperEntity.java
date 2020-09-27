package com.example.web_final.Entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "paper", schema = "exam-db")
public class PaperEntity
{
    private Integer paperId;
    private String paperName;
    private Timestamp endDate;
    private Timestamp startDate;
    private String status;
    private SubjectEntity subject;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paper_id", nullable = false)
    public Integer getPaperId()
    {
        return paperId;
    }

    public void setPaperId(Integer paperId)
    {
        this.paperId = paperId;
    }

    @Basic
    @Column(name = "paper_name", nullable = false, length = 255)
    public String getPaperName()
    {
        return paperName;
    }

    public void setPaperName(String paperName)
    {
        this.paperName = paperName;
    }

    @Basic
    @Column(name = "end_date", nullable = false)
    public Timestamp getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Timestamp endDate)
    {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "start_date", nullable = false)
    public Timestamp getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Timestamp startDate)
    {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 255)
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id", nullable = false)
    public SubjectEntity getSubject()
    {
        return subject;
    }

    public void setSubject(SubjectEntity subject)
    {
        this.subject = subject;
    }
}
