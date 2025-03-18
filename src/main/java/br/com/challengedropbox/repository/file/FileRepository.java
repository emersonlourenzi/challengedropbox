package br.com.challengedropbox.repository.file;

import br.com.challengedropbox.repository.file.entity.FileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends MongoRepository<FileEntity, String> {

    void deleteByNameFile(String nameFile);
    
}
