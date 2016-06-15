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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "dado")
@XmlRootElement
@NamedQueries({
    //@NamedQuery(name = "Dado.findAll", query="SELECT sub FROM(SELECT d FROM dado d ORDER BY d.tempo desc, d.sensor LIMIT 20) ORDER by tempo asc"),
    @NamedQuery(name = "Dado.findAll", query = "SELECT d FROM Dado d ORDER BY d.tempo desc, d.sensor"),
    //@NamedQuery(name = "Dado.findAll", query = "SELECT d FROM Dado d ORDER BY d.sensor"),
    @NamedQuery(name = "Dado.findByIdLeitura", query = "SELECT d FROM Dado d JOIN d.idLeitura l WHERE l.idLeitura = :idLeitura ORDER BY d.sensor"),
    @NamedQuery(name = "Dado.findByIdDado", query = "SELECT d FROM Dado d WHERE d.idDado = :idDado"),
    @NamedQuery(name = "Dado.findBySensor", query = "SELECT d FROM Dado d WHERE d.sensor = :sensor"),
    @NamedQuery(name = "Dado.findByValor", query = "SELECT d FROM Dado d WHERE d.valor = :valor"),
    @NamedQuery(name = "Dado.findByTempo", query = "SELECT d FROM Dado d WHERE d.tempo = :tempo")})

public class Dado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dado")
    private Integer idDado;
    @Column(name = "sensor")
    private Integer sensor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Float valor;
    @JoinColumn(name = "ID_LEITURA", referencedColumnName = "ID_LEITURA")
    @ManyToOne(optional = false)
    private Leitura idLeitura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tempo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tempo;
    @Size(max = 32)
    @NotNull
    private String hash;

    public Dado() {
    }
    
    public Dado(Integer sensor, Float valor, Leitura idLeitura, Date tempo, String hash) {
        this.sensor = sensor;
        this.valor = valor;
        this.idLeitura = idLeitura;
        this.tempo = tempo;
        this.hash = hash;
    }
    public Dado(Integer sensor, Float valor) {
        this.sensor = sensor;
        this.valor = valor;
    }

    public Dado(Integer idDado) {
        this.idDado = idDado;
    }

    public Dado(Integer idDado, Date tempo) {
        this.idDado = idDado;
        this.tempo = tempo;
    }

    public Integer getIdDado() {
        return idDado;
    }

    public void setIdDado(Integer idDado) {
        this.idDado = idDado;
    }

    public Integer getSensor() {
        return sensor;
    }

    public void setSensor(Integer sensor) {
        this.sensor = sensor;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Date getTempo() {
        return tempo;
    }

    public void setTempo(Date tempo) {
        this.tempo = tempo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDado != null ? idDado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dado)) {
            return false;
        }
        Dado other = (Dado) object;
        if ((this.idDado == null && other.idDado != null) || (this.idDado != null && !this.idDado.equals(other.idDado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Dado[ idDado=" + idDado + " ]";
    }

    public Leitura getIdLeitura() {
        return idLeitura;
    }

    public void setIdLeitura(Leitura idLeitura) {
        this.idLeitura = idLeitura;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
}
