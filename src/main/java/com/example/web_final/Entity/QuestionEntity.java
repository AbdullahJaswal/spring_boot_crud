package com.example.web_final.Entity;

import javax.persistence.*;

@Entity
@Table(name = "questions", schema = "exam-db")
public class QuestionEntity
{
    private Integer questionId;
    private String question;
    private PaperEntity paper;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTION_ID", nullable = false)
    public Integer getQuestionId()
    {
        return questionId;
    }

    public void setQuestionId(Integer questionId)
    {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "QUESTION", nullable = false, length = 255)
    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PAPER_ID", referencedColumnName = "paper_id", nullable = false)
    public PaperEntity getPaper()
    {
        return paper;
    }

    public void setPaper(PaperEntity paper)
    {
        this.paper = paper;
    }
}
