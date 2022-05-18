package bosonit.formacion.appFinal.reservas.application.Services.implementations;

import bosonit.formacion.appFinal.reservas.application.Services.autobusService;
import bosonit.formacion.appFinal.reservas.domain.Autobus;
import bosonit.formacion.appFinal.reservas.infraestructure.repository.autobusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class autobusServiceImpl implements autobusService {

    @Autowired
    private autobusRepository repositorio;

    public List<Autobus> getAllBuses(){
        return repositorio.findAll();
    }

}
