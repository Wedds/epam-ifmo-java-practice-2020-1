package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Questions;

import com.ifmo.epampractice.enums.QuestionType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class QuestionsDAOTest {
    private static final QuestionsDAO QUESTIONS_DAO = new QuestionsDAO();
    private static final QuestionType QUESTION_TYPE = QuestionType.CHECKBOX;
    private static final String QUESTION_TITLE = "The best question!!!";
    private static final String QUESTION_TEXT = "Android or IOS?";
    private static final int TEST_ID = 2;
    private static final QuestionType QUESTION_TYPE_UPDATE = QuestionType.RADIOBUTTON;
    private static final String QUESTION_TITLE_UPDATE = "The worst question((()))";
    private static final String QUESTION_TEXT_UPDATE = "Cats or dogs?";
    private static final int TEST_ID_UPDATE = 1;

    public Questions createQuestionsObject() {
        Questions question = new Questions();
        question.setQuestionType(QUESTION_TYPE);
        question.setTitle(QUESTION_TITLE);
        question.setQuestionText(QUESTION_TEXT);
        question.setTestId(TEST_ID);
        return question;
    }

    public Questions createQuestionsObjectForUpdate() {
        Questions question = new Questions();
        question.setQuestionType(QUESTION_TYPE_UPDATE);
        question.setTitle(QUESTION_TITLE_UPDATE);
        question.setQuestionText(QUESTION_TEXT_UPDATE);
        question.setTestId(TEST_ID_UPDATE);
        return question;
    }

    @Test
    public void testAddObject() {
        boolean controlSum;
        Questions question = createQuestionsObject();
        question = QUESTIONS_DAO.addObject(question);
        controlSum = QUESTIONS_DAO.getById(question.getId()).isPresent();
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void testGetQuestionsListByTestId() {
        Questions question = createQuestionsObject();
        question = QUESTIONS_DAO.addObject(question);
        List<Questions> questionsList = QUESTIONS_DAO.getQuestionsListByTestId(2);
        Assert.assertFalse(questionsList.isEmpty());
    }

    @Test
    public void testGetAll() {
        Questions question = createQuestionsObject();
        question = QUESTIONS_DAO.addObject(question);
        List<Questions> questionsList = QUESTIONS_DAO.getAll();
        Assert.assertFalse(questionsList.isEmpty());
    }

    @Test
    public void testGetById() {
        boolean controlSum;
        Questions question = createQuestionsObject();
        question = QUESTIONS_DAO.addObject(question);
        Optional<Questions> questionOptional = QUESTIONS_DAO.getById(question.getId());
        Questions receivedQuestion = new Questions();
        if (questionOptional.isPresent()) {
            receivedQuestion = questionOptional.get();
        }
        if (receivedQuestion.equals(question)) {
            controlSum = Boolean.TRUE;
        } else {
            controlSum = Boolean.FALSE;
        }
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void testUpdateByObject() {
        boolean controlSum;
        Questions questionBeforeUpdate = createQuestionsObject();
        questionBeforeUpdate = QUESTIONS_DAO.addObject(questionBeforeUpdate);
        int id = questionBeforeUpdate.getId();
        Questions questionForUpdate = createQuestionsObjectForUpdate();
        questionForUpdate.setId(id);
        QUESTIONS_DAO.updateByObject(questionForUpdate);
        Optional<Questions> questionOptional = QUESTIONS_DAO.getById(id);
        Questions controlAttempt = new Questions();
        if (questionOptional.isPresent()) {
            controlAttempt = questionOptional.get();
        }
        if (controlAttempt.equals(questionForUpdate)) {
            controlSum = Boolean.TRUE;
        } else {
            controlSum = Boolean.FALSE;
        }
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void testRemoveById() {
        boolean controlSum;
        Questions question = createQuestionsObject();
        question = QUESTIONS_DAO.addObject(question);
        int id = question.getId();
        QUESTIONS_DAO.removeById(id);
        controlSum = QUESTIONS_DAO.getById(id).isPresent();
        Assert.assertEquals(Boolean.FALSE, controlSum);
    }
}
