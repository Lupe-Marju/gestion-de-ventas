package com.example.gestionDeVentas.service;

import com.example.gestionDeVentas.dto.ProductoDto;
import com.example.gestionDeVentas.exception.ProductoNotFoundException;
import com.example.gestionDeVentas.model.Producto;
import com.example.gestionDeVentas.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository repository;

    public ProductoDto convertirProductoADto(Producto producto){
        ProductoDto productoDto = new ProductoDto();
        productoDto.setIdProducto(producto.getId());
        productoDto.setNombreProducto(producto.getNombre());
        productoDto.setCategoriaProducto(producto.getCategoria());
        productoDto.setPrecioProducto(producto.getPrecio());
        return productoDto;
    }

    public Producto converitirDtoAProducto(ProductoDto productoDto){
        Producto producto = new Producto();
        producto.setPrecio(productoDto.getPrecioProducto());
        producto.setCategoria(productoDto.getCategoriaProducto());
        producto.setNombre(productoDto.getNombreProducto());
        return producto;
    }

    public List<ProductoDto> listar() {
       return repository.findAll()
               .stream()
               .map(p->convertirProductoADto(p))
               .toList();
    }

    public void agregarProducto(ProductoDto productoDto) {
        repository.save(converitirDtoAProducto(productoDto));
    }

    public void actualizarProducto(ProductoDto productoDto, Long id) {
        //Agregar excepcion
        Producto producto = repository.findById(id).orElseThrow(()->new ProductoNotFoundException("No se ha encontrado ese producto"));
        producto.setNombre(productoDto.getNombreProducto());
        producto.setPrecio(productoDto.getPrecioProducto());
        producto.setCategoria(productoDto.getCategoriaProducto());
        repository.save(producto);
    }

    public void eliminarProducto(Long id) {
        Producto producto = repository.findById(id).orElseThrow(()->new ProductoNotFoundException("No se ha encontrado ese producto"));
        repository.delete(producto);
    }
}
