package za.co.mtax.tool.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.co.mtax.tool.domain.entities.BatchFileSpec;
import za.co.mtax.tool.domain.entities.BatchFileSpecCompositeId;

import java.util.ArrayList;

@Repository
public interface BatchFileSpecRepository extends CrudRepository<BatchFileSpec, BatchFileSpecCompositeId> {

    //TODO: tighter bind to form type
    @Query("select max(cast(b.batchFileSpecCompositeId.specVersion as int)) from BatchFileSpec b where b.batchFileSpecCompositeId.formType=:formType")
    Integer getMaxSpecVersion(@Param("formType") Integer formType);

    @Query("select b from BatchFileSpec b where b.batchFileSpecCompositeId.formType=:formType and " +
           "b.batchFileSpecCompositeId.specVersion=:specVersion ORDER BY b.batchFileSpecCompositeId.fileSpecSectionCode, b.orderId")
    ArrayList<BatchFileSpec> getFileSpecByFormType(@Param("formType") Integer formType, @Param("specVersion") String specVersion);

}