package org.iesbelen.tienda_informatica.servicio;

import org.iesbelen.tienda_informatica.dao.FabricanteDAO;
import org.iesbelen.tienda_informatica.dao.ProductoDAO;
import org.iesbelen.tienda_informatica.dto.ProductoDTO;
import org.iesbelen.tienda_informatica.mapstruct.ProductoMapper;
import org.iesbelen.tienda_informatica.modelo.Fabricante;
import org.iesbelen.tienda_informatica.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

	@Autowired
	private ProductoDAO productoDAO;

	@Autowired
	private FabricanteDAO fabricanteDAO;

	@Autowired
	private ProductoMapper productoMapper;
	
	public List<Producto> listAll() {
		
		return productoDAO.getAll();
		
	}
	
	public Producto one(Integer id) {
		Optional<Producto> optPro = productoDAO.find(id);
		if (optPro.isPresent())
			return optPro.get();
		else 
			return null;
	}
	
	public void newProducto(Producto producto) {
		
		productoDAO.create(producto);
		
	}
	
	public void replaceProducto(Producto producto) {
		
		productoDAO.update(producto);
		
	}
	
	public void deleteProducto(int id) {
		
		productoDAO.delete(id);
		
	}

	public ProductoDTO productoDTO(int id) {

		Optional<Producto> optPro = productoDAO.find(id);
		Optional<Fabricante> optFabricante = fabricanteDAO.find(optPro.get().getIdFabricante());

		Producto producto = optPro.get();

		return productoMapper.toDto(producto, optFabricante.get().getNombre());


	}
	
}
