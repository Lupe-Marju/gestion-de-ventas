package com.example.gestionDeVentas.service;

import com.example.gestionDeVentas.dto.SucursalDto;
import com.example.gestionDeVentas.exception.SucursalNotFoundException;
import com.example.gestionDeVentas.model.Sucursal;
import com.example.gestionDeVentas.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService {
    @Autowired
    private SucursalRepository repository;

    public SucursalDto convertirSucursalaDto(Sucursal sucursal) {
        return new SucursalDto(sucursal.getId(), sucursal.getNombre(), sucursal.getDireccion());
    }

    public List<SucursalDto> listarSucursales() {
        return repository.findAll().stream()
                .map(this::convertirSucursalaDto).toList();
    }

    public void crearSucursal(SucursalDto sucursalDto) {
        if (sucursalDto.getNombreSucursal() == null ||
                sucursalDto.getNombreSucursal().isBlank() ||
                sucursalDto.getDireccionSucursal() == null ||
                sucursalDto.getDireccionSucursal().isBlank())
            throw new IllegalArgumentException("Los campos ingresados no son correctos");
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(sucursalDto.getNombreSucursal());
        sucursal.setDireccion(sucursalDto.getDireccionSucursal());
        repository.save(sucursal);
    }

    public void actualizarSucursal(SucursalDto sucursalDto, Long id) {
        if (sucursalDto.getNombreSucursal() == null ||
                sucursalDto.getNombreSucursal().isBlank() ||
                sucursalDto.getDireccionSucursal() == null ||
                sucursalDto.getDireccionSucursal().isBlank())
            throw new IllegalArgumentException("Los campos ingresados no son correctos");
        Sucursal sucursal = repository.findById(id)
                .orElseThrow(() -> new SucursalNotFoundException("La sucursal con id " + id + " no fue encontrada"));
        sucursal.setNombre(sucursalDto.getNombreSucursal());
        sucursal.setDireccion(sucursalDto.getDireccionSucursal());
        repository.save(sucursal);
    }

    public void eliminarsucursal(Long id) {
        if (!repository.existsById(id))
            throw new SucursalNotFoundException("La sucursal con id " + id + " no fue encontrada");
        repository.deleteById(id);
    }
}
