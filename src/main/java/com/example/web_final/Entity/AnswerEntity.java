package com.example.web_final.Entity;

import javax.persistence.*;

@Entity
@Table(name = "answer", schema = "exam-db")
public class AnswerEntity
{
    private Integer answerId;
    private String answer;
    private QuestionEntity question;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Answer_ID", nullable = false)
    public Integer getAnswerId()
    {
        return answerId;
    }

    public void setAnswerId(Integer answerId)
    {
        this.answerId = answerId;
    }

    @Basic
    @Column(name = "Answer", nullable = false, length = 255)
    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Question_ID", referencedColumnName = "QUESTION_ID", nullable = false)
    public QuestionEntity getQuestion()
    {
        return question;
    }

    public void setQuestion(QuestionEntity question)
    {
        this.question = question;
    }
}
