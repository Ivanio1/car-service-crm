package carservicecrm.services;

import carservicecrm.models.Question;
import carservicecrm.models.User;
import carservicecrm.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public boolean save(Question question){
        try{
            questionRepository.save(question);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public List<Question> list() {
        return questionRepository.findAll();
    }

    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElse(null);
        if (question != null) {
            questionRepository.delete(question);
            log.info("Question with id = {} was deleted", id);
        } else {
            log.error("Question with id = {} is not found", id);
        }
    }
}
