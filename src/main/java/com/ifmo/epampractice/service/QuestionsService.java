package com.ifmo.epampractice.service;

import com.ifmo.epampractice.dao.QuestionsDAO;
import com.ifmo.epampractice.entity.Questions;
import com.ifmo.epampractice.enums.QuestionType;
import sun.security.x509.FreshestCRLExtension;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionsService {
    private static final QuestionsDAO QUESTIONS_DAO = new QuestionsDAO();
    private static final TestsService TESTS_SERVICE = new TestsService();
    private static final AnswersService ANSWERS_SERVICE = new AnswersService();

    public Questions getQuestionFromRequest(final HttpServletRequest request) {
        Questions question = new Questions();
        final String nullableId = request.getParameter("id");
        final String nullableQuestionType = request.getParameter("questionType");
        final String nullableTitle = request.getParameter("title");
        final String nullableImage = request.getParameter("image");
        final String nullableQuestionText = request.getParameter("questionText");
        final String nullableTestId = request.getParameter("testId");

        if (nullableQuestionType == null || nullableTitle == null
                || nullableQuestionText == null || nullableTestId == null) {
            System.err.println("Question parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            question.setQuestionType(QuestionType.valueOf(nullableQuestionType.trim().toUpperCase()));
            question.setTitle(nullableTitle.trim());
            question.setImage(nullableImage.trim());
            question.setQuestionText(nullableQuestionText.trim());
            question.setTestId(Integer.parseInt(nullableTestId));

            if (TESTS_SERVICE.ifTestObjectExist(question.getTestId())) {
                System.err.println("This test doesn't exist");
                throw new IllegalArgumentException("This object doesn't exist");
            }

            if (nullableId != null) {
                question.setId(Integer.parseInt(nullableId));
            }
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of some parameters");
            throw new IllegalArgumentException("Incorrect format of parameters");
        }

        return new Questions();
    }

    public Questions addObject(final HttpServletRequest request) {
        Questions question = getQuestionFromRequest(request);
        return QUESTIONS_DAO.addObject(question);
    }

    public List<Questions> getAll() {
        return QUESTIONS_DAO.getAll();
    }

    public List<Questions> getQuestionsListByTestId(final HttpServletRequest request) {
        final String nullableTestId = request.getParameter("id");
        List<Questions> questionsList;
        if (nullableTestId == null) {
            System.err.println("Question parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            int testId = Integer.parseInt(nullableTestId);
            questionsList = QUESTIONS_DAO.getQuestionsListByTestId(testId);
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of question id");
            throw new IllegalArgumentException("Incorrect format of id");
        }
        return questionsList;
    }

    public Questions getById(final HttpServletRequest request) {
        final String nullableTestId = request.getParameter("id");
        Questions question;
        if (nullableTestId == null) {
            System.err.println("Question parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }

        try {
            int questionId = Integer.parseInt(nullableTestId);
            Optional<Questions> questionsOptional = QUESTIONS_DAO.getById(questionId);
            if (!questionsOptional.isPresent()) {
                System.err.println("This question doesn't exist");
                throw new IllegalArgumentException("This object doesn't exist");
            }
            question = questionsOptional.get();
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of question id");
            throw new IllegalArgumentException("Incorrect format of id");
        }
        return question;
    }

    public void updateByObject(final HttpServletRequest request) {
        Questions question = getQuestionFromRequest(request);
        QUESTIONS_DAO.updateByObject(question);
    }

    public void removeById(final HttpServletRequest request) {
        final String nullableTestId = request.getParameter("id");

        if (nullableTestId == null) {
            System.err.println("Question parameter is missing");
            throw new IllegalArgumentException("Parameter is missing");
        }
        try {
            int questionId = Integer.parseInt(nullableTestId);
            if (!QUESTIONS_DAO.getById(questionId).isPresent()) {
                System.err.println("This question doesn't exist");
                throw new IllegalArgumentException("This object doesn't exist");
            }
            QUESTIONS_DAO.removeById(questionId);
        } catch (NumberFormatException e) {
            System.err.println("Incorrect format of question id");
            throw new IllegalArgumentException("Incorrect format of id");
        }
    }

 //   public List<Questions> getQuestionsWithAnswersListByTestId(final int testId){
 //       List<Questions> questionsList = new ArrayList<>();
 //       Questions question = new Questions();
 //       try {
 //           if (!TESTS_SERVICE.ifTestObjectExist(testId)) {
 //               System.err.println("This test doesn't exist");
 //               throw new IllegalArgumentException("This object doesn't exist");
 //           }
 //           questionsList = QUESTIONS_DAO.getQuestionsListByTestId(testId);
 //           for (Questions quest:questionsList){
 //               question.setAnswersList(ANSWERS_SERVICE.getAnswersListByQuestionId(request));
 //           }
//
 //       } catch (NumberFormatException e) {
 //           System.err.println("Incorrect format of question id");
 //           throw new IllegalArgumentException("Incorrect format of id");
 //       }
 //       return questionsList;
 //   }

    public Boolean ifQuestionObjectExist(final int id) {
        if (QUESTIONS_DAO.getById(id).isPresent()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
