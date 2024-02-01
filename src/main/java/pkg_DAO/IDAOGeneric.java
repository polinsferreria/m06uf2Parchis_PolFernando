package pkg_DAO;

import java.io.Serializable;
import java.util.List;

public interface IDAOGeneric <T, ID extends Serializable> {

	//en un DAO al menos estan los cuatro metodos CRUD basicos
	//Create
	//Read o Retrieve
	//Update
	//Delete
	//en este ponemos un list que devuelva todo porque es util
	
	// en Save o Update debes pasarle el mismo tipo que el Generico que has
	// especificado en el operador Diamante
	void saveOrUpdate(T entity);

	// get devuelve el generico especificado
	T get(ID id);

	//delete le pasas la id
	void delete(ID id);
	
	//delete le pasas la entidad
	void delete(T entity);

	//puedes especificar que devuelva una lista de genericos
	List<T> list();
}