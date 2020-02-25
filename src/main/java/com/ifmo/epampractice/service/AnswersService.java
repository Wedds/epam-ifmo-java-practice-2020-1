package com.ifmo.epampractice.service;

import com.ifmo.epampractice.dao.AnswersDAO;
import com.ifmo.epampractice.entity.Answers;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class AnswersService {
    private static final AnswersDAO ANSWERS_DAO = new AnswersDAO();
    private static final QuestionsService QUESTIONS_SERVICE = new QuestionsService();

    public Answers getAnswerFromRequest(final HttpServletRequest request) {
        Answers answer = new Answers();
        final String nullableId = request.getParameter("id");
        final String nullableQuestionId = request.getParameter("questionId");
        final String nullableImage = request.getParameter("image");
        final String nullableAnswerText = request.getParameter("answerText");
        final String nullableIsCorrect = request.getParameter("isCorrect");
        final String nullablePoints = request.getParameter("points");

        if (nullableQuestionId == null || nullableAnswerText == null
                || nullableIsCorrect == null || nullablePoints == null) {
            System.err.println("Answer parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {

            if (!QUESTIONS_SERVICE.ifQuestionObjectExist(request)) {
                System.err.println("Question doesn't exist");
                throw new IllegalArgumentException("This question doesn't exist");
            }

            answer.setQuestionId(Integer.parseInt(nullableQuestionId));
            answer.setImage(nullableImage.trim());
            answer.setAnswerText(nullableAnswerText.trim());
            answer.setIsCorrect(Boolean.parseBoolean(nullableIsCorrect));
            answer.setPoints(Integer.parseInt(nullablePoints));

            if (nullableId != null) {
                answer.setId(Integer.parseInt(nullableId));
            }
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of some parameters");
            throw new IllegalArgumentException("Incorrect format of parameters");
        }

        return answer;
    }

    public Answers addObject(final HttpServletRequest request) {
        Answers answer = getAnswerFromRequest(request);
        return ANSWERS_DAO.addObject(answer);
    }

    public List<Answers> getAll() {
        return ANSWERS_DAO.getAll();
    }

    public List<Answers> getAnswersListByQuestionId(final HttpServletRequest request) {
        final String nullableQuestionId = request.getParameter("questionId");
        List<Answers> answersList;
        if (nullableQuestionId == null) {
            System.err.println("Answer parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            int questionId = Integer.parseInt(nullableQuestionId);
            answersList = ANSWERS_DAO.getAnswersListByQuestionId(questionId);
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of question id");
            throw new IllegalArgumentException("Incorrect format of id");
        }

        return answersList;
    }

    public Answers getById(final HttpServletRequest request) {
        final String nullableTestId = request.getParameter("id");
        Answers answer;
        if (nullableTestId == null) {
            System.err.println("Answer parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            int answerId = Integer.parseInt(nullableTestId);
            Optional<Answers> answersOptional = ANSWERS_DAO.getById(answerId);
            if (!answersOptional.isPresent()) {
                System.err.println("Answer doesn't exist");
                throw new IllegalArgumentException("This answer doesn't exist");
            }
            answer = answersOptional.get();
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of answer id");
            throw new IllegalArgumentException("Incorrect format of id");
        }
        return answer;
    }

    public void updateByObject(final HttpServletRequest request) {
        Answers answer = getAnswerFromRequest(request);
        ANSWERS_DAO.updateByObject(answer);
    }

    public void removeById(final HttpServletRequest request) {
        final String nullableTestId = request.getParameter("id");

        if (nullableTestId == null) {
            System.err.println("Answer parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }
        try {
            int answerId = Integer.parseInt(nullableTestId);
            if (!ANSWERS_DAO.getById(answerId).isPresent()) {
                System.err.println("Answer doesn't exist");
                throw new IllegalArgumentException("This answer doesn't exist");
            }
            ANSWERS_DAO.removeById(answerId);
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of answer id");
            throw new IllegalArgumentException("Incorrect format of id");
        }
    }

    public Boolean ifAnswerObjectExist(final HttpServletRequest request) {
        final String nullableId = request.getParameter("id");
        if (nullableId == null) {
            System.err.println("Answer parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            if (ANSWERS_DAO.getById(Integer.parseInt(nullableId)).isPresent()) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of answer id");
            throw new IllegalArgumentException("Incorrect format of id");
        }
    }

}
