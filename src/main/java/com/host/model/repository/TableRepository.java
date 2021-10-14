package com.host.model.repository;

import com.host.model.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
// UUID, а не Long
/*public interface TableRepository extends CrudRepository<TableEntity, UUID> {
}*/

public interface TableRepository extends JpaRepository<TableEntity, UUID> {

}
