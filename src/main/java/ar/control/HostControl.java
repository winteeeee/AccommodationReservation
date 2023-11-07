package ar.control;

import ar.entity.Host;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class HostControl extends Control<Host, String>{

    @Override
    public List<Host> findAll() {
        ReturnTransaction<List<Host>> fun = () -> {
            TypedQuery<Host> query = em.createQuery("SELECT h FROM Host h", Host.class);
            return query.getResultList();
        };

        return transactionStart(fun);
    }

    @Override
    public Host findById(String id) {
        ReturnTransaction<List<Host>> fun = () -> {
            List<Host> result = new ArrayList<>();
            result.add(em.find(Host.class, id));
            return result;
        };

        return transactionStart(fun).get(0);
    }

    public Host login(String id, String password) {
        Host host = findById(id);
        return host == null || host.getPassword().equals(password) ? host : null;
    }
}
