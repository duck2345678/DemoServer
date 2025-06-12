package org.example.AgentManagementBE.Repository;

import org.example.AgentManagementBE.Model.ImportReceipt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@EnableJpaRepositories
public interface ImportReceiptRepository extends CrudRepository<ImportReceipt, Integer>
{
    @Query("SELECT importReceipt FROM ImportReceipt importReceipt WHERE importReceipt.importReceiptID = :importReceiptID")
    ImportReceipt getImportReceiptById(@Param("importReceiptID") int importReceiptID);

    @Query("SELECT importReceipt FROM ImportReceipt importReceipt WHERE CAST(importReceipt.dateReceipt AS string) = :dateReceipt")
    ImportReceipt getAllImportReceiptByDateReceipt(@Param("dateReceipt") String dateReceipt);
}