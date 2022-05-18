package sri.s26462.f1supportsystem.receiver.receiverRepo;

import org.springframework.data.repository.CrudRepository;
import sri.s26462.f1supportsystem.receiver.receiverModel.EngineParameter;

import java.util.List;

public interface EngineParameterRepository extends CrudRepository<EngineParameter, Long> {
    List<EngineParameter> findAll();
}
