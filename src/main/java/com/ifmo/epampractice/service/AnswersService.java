package com.ifmo.epampractice.service;

import com.ifmo.epampractice.dao.AnswersDAO;
import com.ifmo.epampractice.entity.Answers;

import java.util.List;
import java.util.Optional;

public class AnswersService {
    private static final AnswersDAO ANSWERS_DAO = new AnswersDAO();
    private static final QuestionsService QUESTIONS_SERVICE = new QuestionsService();

    public Answers addObject(final Answers answer) {
        if (!QUESTIONS_SERVICE.ifQuestionObjectExist(answer.getQuestionId())) {
            System.err.println("Question doesn't exist");
            throw new IllegalArgumentException("This question doesn't exist");
        }
        return ANSWERS_DAO.addObject(answer);
    }

    public List<Answers> getAll() {
        return ANSWERS_DAO.getAll();
    }

    public List<Answers> getAnswersListByQuestionId(final int questionId) {
        List<Answers> answersList;
        if (!QUESTIONS_SERVICE.ifQuestionObjectExist(questionId)) {
            System.err.println("Question doesn't exist");
            throw new IllegalArgumentException("This question doesn't exist");
        }
        answersList = ANSWERS_DAO.getAnswersListByQuestionId(questionId);
        return answersList;
    }

    public Answers getById(final int answerId) {
        Answers answer;
        Optional<Answers> answersOptional = ANSWERS_DAO.getById(answerId);
        if (!answersOptional.isPresent()) {
            System.err.println("Answer doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        answer = answersOptional.get();

        return answer;
    }

    public void updateByObject(final Answers answer) {
        if (!QUESTIONS_SERVICE.ifQuestionObjectExist(answer.getQuestionId())) {
            System.err.println("Question doesn't exist");
            throw new IllegalArgumentException("This question doesn't exist");
        }
        ANSWERS_DAO.updateByObject(answer);
    }

    public void removeById(final int answerId) {
        if (!ANSWERS_DAO.getById(answerId).isPresent()) {
            System.err.println("Answer doesn't exist");
            throw new IllegalArgumentException("This object doesn't exist");
        }
        ANSWERS_DAO.removeById(answerId);
    }

    public Boolean ifAnswerObjectExist(final int id) {
        if (ANSWERS_DAO.getById(id).isPresent()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
