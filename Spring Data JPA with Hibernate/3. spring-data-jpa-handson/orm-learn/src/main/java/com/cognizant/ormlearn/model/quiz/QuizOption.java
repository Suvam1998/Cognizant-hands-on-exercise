package com.cognizant.ormlearn.model.quiz;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/** An answer option for a question (table "options"). */
@Entity
@Table(name = "options")
public class QuizOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "o_id")
    private int id;

    @Column(name = "o_text")
    private String text;

    /** Points awarded for this option (0 for wrong, full/partial for correct). */
    @Column(name = "o_score")
    private double score;

    @Column(name = "o_is_correct")
    private boolean correct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "o_q_id")
    private Question question;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
    public boolean isCorrect() { return correct; }
    public void setCorrect(boolean correct) { this.correct = correct; }
    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    @Override
    public String toString() {
        return "QuizOption [id=" + id + ", text=" + text + ", score=" + score
                + ", correct=" + correct + "]";
    }
}
