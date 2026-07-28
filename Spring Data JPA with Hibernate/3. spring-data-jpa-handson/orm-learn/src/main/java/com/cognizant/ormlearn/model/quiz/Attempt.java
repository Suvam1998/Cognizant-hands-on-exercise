package com.cognizant.ormlearn.model.quiz;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "attempt")
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "at_id")
    private int id;

    @Column(name = "at_date")
    private Date attemptDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "at_user_id")
    private User user;

    @OneToMany(mappedBy = "attempt")
    private Set<AttemptQuestion> attemptQuestions;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getAttemptDate() { return attemptDate; }
    public void setAttemptDate(Date attemptDate) { this.attemptDate = attemptDate; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Set<AttemptQuestion> getAttemptQuestions() { return attemptQuestions; }
    public void setAttemptQuestions(Set<AttemptQuestion> attemptQuestions) {
        this.attemptQuestions = attemptQuestions;
    }
}
