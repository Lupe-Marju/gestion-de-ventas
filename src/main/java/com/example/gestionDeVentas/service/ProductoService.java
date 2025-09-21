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

    public ProductoDto convertirProductoADto(Producto producto) {
        ProductoDto productoDto = new ProductoDto();
        productoDto.setIdProducto(producto.getId());
        productoDto.setNombreProducto(producto.getNombre());
        productoDto.setCategoriaProducto(producto.getCategoria());
        productoDto.setPrecioProducto(producto.getPrecio());
        return productoDto;
    }

    public Producto convertirDtoAProducto(ProductoDto productoDto) {
        Producto producto = new Producto();
        producto.setPrecio(productoDto.getPrecioProducto());
        producto.setCategoria(productoDto.getCategoriaProducto());
        producto.setNombre(productoDto.getNombreProducto());
        return producto;
    }

    public List<ProductoDto> listar() {
        return repository.findAll()
                .stream()
                .filter(p->!p.isEliminada())
                .map(p -> convertirProductoADto(p))
                .toList();
    }

    public void agregarProducto(ProductoDto productoDto) {
        if (productoDto.getNombreProducto()==null||
                productoDto.getNombreProducto().isBlank()||
        productoDto.getCategoriaProducto()==null||
        productoDto.getCategoriaProducto().isBlank()||
        productoDto.getPrecioProducto()==null||
        productoDto.getPrecioProducto()<=0)
            throw new IllegalArgumentException("Los campos ingresados no son correctos");
        repository.save(convertirDtoAProducto(productoDto));
    }

    public void actualizarProducto(ProductoDto productoDto, Long id) {
        if (productoDto.getNombreProducto()==null||
                productoDto.getNombreProducto().isBlank()||
                productoDto.getCategoriaProducto()==null||
                productoDto.getCategoriaProducto().isBlank()||
                productoDto.getPrecioProducto()==null||
                productoDto.getPrecioProducto()<=0)
            throw new IllegalArgumentException("Los campos ingresados no son correctos");
        Producto producto = repository.findById(id).orElseThrow(() -> new ProductoNotFoundException("El producto con id " + id + " no fue encontrado"));
        producto.setNombre(productoDto.getNombreProducto());
        producto.setPrecio(productoDto.getPrecioProducto());
        producto.setCategoria(productoDto.getCategoriaProducto());
        repository.save(producto);
    }

    public void eliminarProducto(Long id) {
        Producto producto = repository.findById(id).orElseThrow(()->new ProductoNotFoundException("El producto con id " + id + " no fue encontrado"));
        producto.setEliminada(true);
        repository.save(producto);
    }
}
