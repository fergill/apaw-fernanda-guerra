package es.upm.miw.apaw_ep_fernanda_guerra.croqueta_data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CroquetaDao extends MongoRepository<Croqueta, String> {
}
