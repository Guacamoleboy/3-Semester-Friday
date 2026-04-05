package dk.project.controller.impl;

import dk.project.entity.Question;
import dk.project.mapper.response.QuestionResponseMapper;
import dk.project.service.internal.EntityManagerService;

public class QuestionController extends CRUDController<Question> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public QuestionController(EntityManagerService<Question> service) {
        super(service, Question.class, QuestionResponseMapper::toDTO);
    }

}
