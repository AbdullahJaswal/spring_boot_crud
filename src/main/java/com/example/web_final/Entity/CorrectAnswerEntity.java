package com.example.web_final.Entity;

import javax.persistence.*;

@Entity
@Table(name = "correct_answer", schema = "exam-db")
public class CorrectAnswerEntity
{
    private Integer correctAnswerID;
    private QuestionEntity question;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "correct_answer_id", nullable = false)
    public Integer getCorrectAnswerID()
    {
        return correctAnswerID;
    }

    public void setCorrectAnswerID(Integer correctAnswerID)
    {
        this.correctAnswerID = correctAnswerID;
    }

    @OneToOne(fetch = FetchType.LAZY, optional = false)
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
