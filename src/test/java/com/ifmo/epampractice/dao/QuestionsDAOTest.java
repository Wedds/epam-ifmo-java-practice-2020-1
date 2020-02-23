package com.ifmo.epampractice.dao;

import com.ifmo.epampractice.entity.Questions;

import com.ifmo.epampractice.enums.QuestionType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class QuestionsDAOTest {
    private static final QuestionsDAO QUESTIONS_DAO = new QuestionsDAO();

    public Questions createQuestionsObject() {
        Questions question = new Questions();
        question.setQuestionType(QuestionType.CHECKBOX);
        question.setTitle("The best question!!!");
        question.setQuestionText("Android or IOS?");
        question.setTestId(2);
        return question;
    }

    public Questions createQuestionsObjectForUpdate() {
        Questions question = new Questions();
        question.setQuestionType(QuestionType.RADIOBUTTON);
        question.setTitle("The worst question((()))");
        question.setQuestionText("Cats or dogs?");
        question.setTestId(1);
        return question;
    }

    @Test
    public void addObject() {
        Boolean controlSum;
        Questions question = createQuestionsObject();
        question = QUESTIONS_DAO.addObject(question);
        controlSum = QUESTIONS_DAO.getById(question.getId()).isPresent();
        Assert.assertEquals(Boolean.TRUE, controlSum);
    }

    @Test
    public void updateByObject() {
        Boolean controlSum;
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
    public void removeById() {
        Boolean controlSum;
        Questions question = createQuestionsObject();
        question = QUESTIONS_DAO.addObject(question);
        int id = question.getId();
        QUESTIONS_DAO.removeById(id);
        controlSum = QUESTIONS_DAO.getById(id).isPresent();
        Assert.assertEquals(Boolean.FALSE, controlSum);
    }

    @Test
    public void getById() {
        Boolean controlSum;
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
    public void getAll() {
        Questions question = createQuestionsObject();
        question = QUESTIONS_DAO.addObject(question);
        List<Questions> questionsList = QUESTIONS_DAO.getAll();
        Assert.assertFalse(questionsList.isEmpty());
    }
}
