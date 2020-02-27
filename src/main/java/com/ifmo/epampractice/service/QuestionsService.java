package com.ifmo.epampractice.service;

import com.ifmo.epampractice.dao.QuestionsDAO;
import com.ifmo.epampractice.entity.Questions;

import java.util.List;

public class QuestionsService {
    private static final QuestionsDAO QUESTIONS_DAO = new QuestionsDAO();
    private static final TestsService TESTS_SERVICE = new TestsService();
    private static final AnswersService ANSWERS_SERVICE = new AnswersService();

    public Questions addObject(final Questions question) {
        if (TESTS_SERVICE.ifTestObjectExist(question.getTestId())) {
            System.err.println("This test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        return QUESTIONS_DAO.addObject(question);
    }

    public List<Questions> getAll() {
        return QUESTIONS_DAO.getAll();
    }

    public List<Questions> getQuestionsListByTestId(final int testId) {
        List<Questions> questionsList;
        if (TESTS_SERVICE.ifTestObjectExist(testId)) {
            System.err.println("This test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        questionsList = QUESTIONS_DAO.getQuestionsListByTestId(testId);
        return questionsList;
    }

    public Questions getById(final int questionId) {
        return QUESTIONS_DAO.getById(questionId).orElseThrow(() ->
                new IllegalArgumentException("This object doesn't exist"));
    }

    public void updateByObject(final Questions question) {
        if (TESTS_SERVICE.ifTestObjectExist(question.getTestId())) {
            System.err.println("This test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        QUESTIONS_DAO.updateByObject(question);
    }

    public void removeById(final int questionId) {
        QUESTIONS_DAO.getById(questionId).orElseThrow(() ->
                new IllegalArgumentException("This object doesn't exist"));
        QUESTIONS_DAO.removeById(questionId);
    }

    public List<Questions> getQuestionsWithAnswersListByTestId(final int testId) {
        List<Questions> questionsList;
        Questions question = new Questions();
        if (TESTS_SERVICE.ifTestObjectExist(question.getTestId())) {
            System.err.println("This test doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        questionsList = QUESTIONS_DAO.getQuestionsListByTestId(testId);
        for (Questions quest : questionsList) {
            quest.setAnswersList(ANSWERS_SERVICE.getAnswersListByQuestionId(quest.getTestId()));
        }
        return questionsList;
    }

    public Boolean ifQuestionObjectExist(final int id) {
        return (QUESTIONS_DAO.getById(id).isPresent());
    }
}
