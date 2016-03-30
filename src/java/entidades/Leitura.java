/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ti
 */
@Entity
@Table(name = "LEITURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Leitura.findAll", query = "SELECT l FROM Leitura l"),
    @NamedQuery(name = "Leitura.findByIdLeitura", query = "SELECT l FROM Leitura l WHERE l.idLeitura = :idLeitura"),
    @NamedQuery(name = "Leitura.findByDataLeitura", query = "SELECT l FROM Leitura l WHERE l.dataLeitura = :dataLeitura"),
    @NamedQuery(name = "Leitura.findByQuantidadeSensores", query = "SELECT l FROM Leitura l WHERE l.quantidadeSensores = :quantidadeSensores"),
    @NamedQuery(name = "Leitura.findByIntervaloEntreAmostras", query = "SELECT l FROM Leitura l WHERE l.intervaloEntreAmostras = :intervaloEntreAmostras"),
    @NamedQuery(name = "Leitura.findByDescricao", query = "SELECT l FROM Leitura l WHERE l.descricao = :descricao"),
    @NamedQuery(name = "Leitura.findByIp", query = "SELECT l FROM Leitura l WHERE l.ip = :ip")})
public class Leitura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_leitura")
    private Integer idLeitura;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_leitura")
    private Date dataLeitura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade_sensores")
    private int quantidadeSensores;
    @Basic(optional = false)
    @NotNull
    @Column(name = "intervalo_entre_amostras")
    private float intervaloEntreAmostras;
    @Size(max = 255)
    private String descricao;
    @Size(max = 40)
    private String ip;

    public Leitura() {
    }

    public Leitura(Integer idLeitura) {
        this.idLeitura = idLeitura;
    }

    public Leitura(Integer idLeitura, int quantidadeSensores, float intervaloEntreAmostras) {
        this.idLeitura = idLeitura;
        this.quantidadeSensores = quantidadeSensores;
        this.intervaloEntreAmostras = intervaloEntreAmostras;
    }
    
    public Leitura(int quantidadeSensores, float intervaloEntreAmostras) {
        this.quantidadeSensores = quantidadeSensores;
        this.intervaloEntreAmostras = intervaloEntreAmostras;
    }

    public Integer getIdLeitura() {
        return idLeitura;
    }

    public void setIdLeitura(Integer idLeitura) {
        this.idLeitura = idLeitura;
    }

    public Date getDataLeitura() {
        return dataLeitura;
    }

    public void setDataLeitura(Date dataLeitura) {
        this.dataLeitura = dataLeitura;
    }

    public int getQuantidadeSensores() {
        return quantidadeSensores;
    }

    public void setQuantidadeSensores(int quantidadeSensores) {
        this.quantidadeSensores = quantidadeSensores;
    }

    public float getIntervaloEntreAmostras() {
        return intervaloEntreAmostras;
    }

    public void setIntervaloEntreAmostras(float intervaloEntreAmostras) {
        this.intervaloEntreAmostras = intervaloEntreAmostras;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLeitura != null ? idLeitura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Leitura)) {
            return false;
        }
        Leitura other = (Leitura) object;
        if ((this.idLeitura == null && other.idLeitura != null) || (this.idLeitura != null && !this.idLeitura.equals(other.idLeitura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Leitura[ idLeitura=" + idLeitura + " ]";
    }
    
}
