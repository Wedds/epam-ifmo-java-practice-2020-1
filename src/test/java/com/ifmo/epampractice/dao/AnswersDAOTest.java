package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Answers;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;


public class AnswersDAOTest {
    private static final AnswersDAO ANSWERS_DAO = new AnswersDAO();

    public Answers createAnswersObject() {
        Answers answer = new Answers();
        answer.setAnswerText("It is train answer");
        answer.setQuestionId(2);
        answer.setIsCorrect(Boolean.TRUE);
        answer.setPoints(2);
        return answer;
    }

    public Answers createAnswersObjectForUpdate() {
        Answers answer = new Answers();
        answer.setAnswerText("It is really train answer");
        answer.setQuestionId(3);
        answer.setIsCorrect(Boolean.FALSE);
        answer.setPoints(-2);
        return answer;
    }

    @Test
    public void addObject() {
        Boolean controlSum;
        Answers answer = createAnswersObject();
        answer = ANSWERS_DAO.addObject(answer);
        controlSum = ANSWERS_DAO.getById(answer.getId()).isPresent();
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void updateByObject() {
        Boolean controlSum;
        Answers answerBeforeUpdate = createAnswersObject();
        answerBeforeUpdate = ANSWERS_DAO.addObject(answerBeforeUpdate);
        int id = answerBeforeUpdate.getId();
        Answers answerForUpdate = createAnswersObjectForUpdate();
        answerForUpdate.setId(id);
        ANSWERS_DAO.updateByObject(answerForUpdate);
        Optional<Answers> answerOptional = ANSWERS_DAO.getById(id);
        Answers controlAnswer = new Answers();
        if (answerOptional.isPresent()) {
            controlAnswer = answerOptional.get();
        }
        if (controlAnswer.equals(answerForUpdate)) {
            controlSum = Boolean.TRUE;
        } else {
            controlSum = Boolean.FALSE;
        }
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void removeById() {
        Boolean controlSum;
        Answers answer = createAnswersObject();
        answer = ANSWERS_DAO.addObject(answer);
        int id = answer.getId();
        ANSWERS_DAO.removeById(id);
        controlSum = ANSWERS_DAO.getById(id).isPresent();
        Assert.assertEquals(Boolean.FALSE, controlSum);
        System.out.println();
    }

    @Test
    public void getById() {
        Boolean controlSum;
        Answers answer = createAnswersObject();
        answer = ANSWERS_DAO.addObject(answer);
        Optional<Answers> answerOptional = ANSWERS_DAO.getById(answer.getId());
        Answers receivedAnswer = new Answers();
        if (answerOptional.isPresent()) {
            receivedAnswer = answerOptional.get();
        }
        if (receivedAnswer.equals(answer)) {
            controlSum = Boolean.TRUE;
        } else {
            controlSum = Boolean.FALSE;
        }
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void getAll() {
        Answers answer = createAnswersObject();
        answer = ANSWERS_DAO.addObject(answer);
        List<Answers> answersList = ANSWERS_DAO.getAll();
        Assert.assertFalse(answersList.isEmpty());
    }
}
