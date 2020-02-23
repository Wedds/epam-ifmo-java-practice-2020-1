package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Answers;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;


public class AnswersDAOTest {
    private static final AnswersDAO ANSWERS_DAO = new AnswersDAO();
    private static final String ANSWER_TEXT = "It is train answer";
    private static final int QUESTION_ID = 2;
    private static final boolean IS_CORRECT = Boolean.TRUE;
    private static final int POINTS = 2;
    private static final String ANSWER_TEXT_UPDATE = "It is really train answer";
    private static final int QUESTION_ID_UPDATE  = 3;
    private static final boolean IS_CORRECT_UPDATE  = Boolean.FALSE;
    private static final int POINTS_UPDATE  = -2;


    public Answers createAnswersObject() {
        Answers answer = new Answers();
        answer.setAnswerText(ANSWER_TEXT);
        answer.setQuestionId(QUESTION_ID);
        answer.setIsCorrect(IS_CORRECT);
        answer.setPoints(POINTS);
        return answer;
    }

    public Answers createAnswersObjectForUpdate() {
        Answers answer = new Answers();
        answer.setAnswerText(ANSWER_TEXT_UPDATE);
        answer.setQuestionId(QUESTION_ID_UPDATE);
        answer.setIsCorrect(IS_CORRECT_UPDATE);
        answer.setPoints(POINTS_UPDATE);
        return answer;
    }

    @Test
    public void testAddObject() {
        boolean controlSum;
        Answers answer = createAnswersObject();
        answer = ANSWERS_DAO.addObject(answer);
        controlSum = ANSWERS_DAO.getById(answer.getId()).isPresent();
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void testGetAnswersListByQuestionId() {
        Answers answer = createAnswersObject();
        answer = ANSWERS_DAO.addObject(answer);
        List<Answers> answersList = ANSWERS_DAO.getAnswersListByQuestionId(2);
        Assert.assertFalse(answersList.isEmpty());
    }

    @Test
    public void testGetAll() {
        Answers answer = createAnswersObject();
        answer = ANSWERS_DAO.addObject(answer);
        List<Answers> answersList = ANSWERS_DAO.getAll();
        Assert.assertFalse(answersList.isEmpty());
    }

    @Test
    public void testGetById() {
        boolean controlSum;
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
    public void testUpdateByObject() {
        boolean controlSum;
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
    public void testRemoveById() {
        boolean controlSum;
        Answers answer = createAnswersObject();
        answer = ANSWERS_DAO.addObject(answer);
        int id = answer.getId();
        ANSWERS_DAO.removeById(id);
        controlSum = ANSWERS_DAO.getById(id).isPresent();
        Assert.assertEquals(Boolean.FALSE, controlSum);
        System.out.println();
    }
}
