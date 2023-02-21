/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krugercorporation.apiVaccine.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author corin
 */
public class ProveedorDto {


    private String nombreProveedor;
    
    private int estadoProveedor;

    public ProveedorDto() {
    }

    public ProveedorDto(String nombreProveedor, int estadoProveedor) {
        this.nombreProveedor = nombreProveedor;
        this.estadoProveedor = estadoProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public int getEstadoProveedor() {
        return estadoProveedor;
    }

    public void setEstadoProveedor(int estadoProveedor) {
        this.estadoProveedor = estadoProveedor;
    }



}
